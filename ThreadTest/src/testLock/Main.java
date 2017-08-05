package testLock;

public class Main
{

    public static void main(String[] args)
    {
        CompteEnBanque ceb = new CompteEnBanque();

        // On crée deux threads de retrait
        Thread t1 = new ThreadRetrait(ceb);
        t1.start();

        Thread t2 = new ThreadRetrait(ceb);
        t2.start();

        // et un thread de dépôt
        Thread t3 = new ThreadDepot(ceb);
        t3.start();
    }
}
