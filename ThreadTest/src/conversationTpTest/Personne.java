package conversationTpTest;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Personne
    extends Thread
{
    private String nom;

    private Lock verrou;

    private Condition question, reponse;

    private Scanner sc = new Scanner(System.in);

    public Personne(String pNom, Lock pVerrou, Condition pQuestion, Condition pReponse)
    {
        nom = pNom;
        verrou = pVerrou;
        question = pQuestion;
        reponse = pReponse;
    }

    @Override
    public void run()
    {
        while (true)
        {
            reponse();
        }
    }

    private void reponse()
    {
        verrou.lock();
        try
        {
            System.out.println(nom + "Posez votre question : ");
            sc.nextLine();
            question.signalAll(); // libere la condition "question"
            reponse.await(); // attends la libération de "reponse" pour continuer
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        finally
        {
            verrou.unlock();
        }

    }
}
