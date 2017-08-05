package testLock;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CompteEnBanque
{
    private AtomicLong solde = new AtomicLong(1_000L);

    private final long decouvert = -130L;

    // cette variable va nous servir à savoir
    // le nombre de tentatives de retrait successives
    private AtomicLong tentativeDeRetrait = new AtomicLong(0);

    // notre verrou
    private Lock verrou = new ReentrantLock();

    // notre objet condition
    private Condition condition = verrou.newCondition();

    /**
     * C'est sur cette méthode que nous allons devoir travailler dans nos threads et vérifier le solde avant de retirer
     * de l'argent
     */
    public void retrait(long montant)
    {
        verrou.lock();
        String threadName = Thread.currentThread().getName();
        try
        {
            // On met en attente les threads tant que la condition n'est pas remplie
            // Le thread étant mis en attente si cette condition est remplie
            // on aurait pu utiliser un simple "if" mais on ne sait jamais
            while ((solde.get() - montant) < decouvert)
            {

                // dans ce cas, le thread qui tente de retirer ce montant
                // mettra notre solde en deçà du découvert autorisé
                System.err.println(threadName + " tente de retirer " + montant);

                // on stock le cumul des tentatives de retrait car
                // lorsque le verrou sera levé, tous les threads en attente
                // seront autorisés à faire leur retrait, il faut donc contrôler le cumul
                // de toutes les tentatives de retrait
                tentativeDeRetrait.addAndGet(montant);

                // on pose un verrou via la condition
                // cette instruction rend le thread inéligible
                // à travailler
                condition.await();
            }

            // Si nous sommes ici, c'est que le montant du retrait
            // est autorisé ou que la condition a libéré le verrou sur le thread
            solde.set((solde.get() - montant));
            // On refait une lecture de solde car si on stocke la premiere valeur dans uen variable
            // on aura pas la dernière valeur du solde quand on aura repris l'exécution
            solde();
        }
        // L'ajout d'un verrou via une condition peut lever ce genre d'exception
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // On oublie pas le libéré le verrou général
            verrou.unlock();
        }
    }

    // Puisqu’on utilise un objet AtomicLong
    // Inutile de synchroniser, mais on utilisera tout de même un verrou. ^^
    // C'est dans cette méthode que la condition sera libérée
    public void depot(long montant)
    {

        // On utilise le même verrou que celui qui a engendré la condition
        // sans cela, la condition créée à partir de ce verrou
        // lèvera une exception si nous tentons de la libérer
        verrou.lock();

        try
        {

            // Nous faisons notre traitement
            long result = solde.addAndGet(montant);
            solde();

            // Nous vérifions si le solde après les tentatives de retraits
            // sera toujours au dessus de l'autorisation de découvert
            long soldeApresRetrait = getSolde() - tentativeDeRetrait.get();

            // Si tel est le cas, libération du verrou
            if (soldeApresRetrait > decouvert)
            {
                // on réinitialise notre variable de contrôle à 0
                tentativeDeRetrait.set(0);
                // on libère le verrou posé par la condition
                // cette instruction va libérer tous les threads mis en attente
                condition.signalAll();
                System.err.println("\n Montant après retrait (" + soldeApresRetrait + ") < découvert \n");
            }

        }
        finally
        {
            // on n’oublie pas de libérer le verrou général
            verrou.unlock();
        }
    }

    public void solde()
    {
        System.out.println("Solde actuel, dans " + Thread.currentThread().getName() + " : " + solde.longValue());
    }

    public long getSolde()
    {
        return solde.longValue();
    }

    public long getDecouvert()
    {
        return decouvert;
    }
}

/*
Je vous propose maintenant de voir comment ça fonctionne et ce qu'il se passe. Dans ce code, nous avons deux types de threads : un premier qui effectue des retraits et un second qui effectue des dépôts. Le principe ici est donc de ne pas autoriser un retrait qui engendrerait un dépassement de notre autorisation de découvert mais, et c'est la différence avec un bête rejet de la demande, nous l'autorisons dès lors que le solde redevient suffisant pour accepter toutes les demandes de retraits en attente (c'est à ça que sert notre variable tentativeDeRetrait, à stocker le montant total des tentatives).
J'ai donc mis une boucle pour contrôler le solde après retrait et, si le montant dépasse le découvert, on invoque la méthode await() de notre objet de condition, ce qui a pour effet de mettre le thread demandeur en attente.

Il existe d'autres méthodes qui permettent de mettre un thread en attente. Certaines acceptent des paramètres supplémentaires comme un délai, certaines autorisent l'interruption etc. Je vous laisse le soin d'y jeter un œil.
Maintenant, nous devons débloquer les threads en attente lorsque le solde redevient suffisant pour accepter toutes les demandes de retraits. C'est ici qu'intervient la méthode depot(), utilisée par un autre thread que ceux qui sont bloqués et ceci est un point capital !

Pourquoi ?
Tout simplement parce qu'un thread mis en attente avec un objet de condition ne peut être remis en travail que si l'objet condition est déverrouillé depuis un autre thread que celui qui est en attente et uniquement si celui-ci utilise le même verrou !
Il faut donc que notre méthode utilise le même verrou que celui utilisé pour bloquer les retraits et que cette méthode soit utilisée par un autre thread que ceux qui sont bloqués. Heureusement, c'est le cas ici.

Pour simplifier, si nous avons trois threads (t1, t2 et t3), que t1 est bloqué via une condition, seul les threads t2 et t3 peuvent débloquer t1.

J'attire maintenant votre attention sur les risques d'erreurs et là, il faut être très vigilant !
Je n'utilise pas de variable pour stocker le montant du solde dans la méthode retrait() et ceci pour une bonne raison : un thread mis en sommeil avec un condition reprendra exactement là où il s'en est arrêté lorsque le verrou sera libéré. Imaginons que nous stockons un solde à un instant T, que le thread est mis en sommeil, qu'un autre thread modifie le solde et libère le verrou. Le thread maintenant libéré va reprendre son traitement en utilisant la variable qu'il avait utilisée pour stocker le solde à un moment antérieur. Hors, cette variable contient un solde erroné et le traitement sera faussé.

*/