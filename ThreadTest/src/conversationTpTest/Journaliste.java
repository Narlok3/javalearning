package conversationTpTest;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Journaliste
    extends Thread
{
    private String nom;

    private Lock verrou;

    private Condition question, reponse;

    private Scanner sc = new Scanner(System.in);

    public Journaliste(String pNom, Lock pVerrou, Condition pQuestion, Condition pReponse)
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
            question();
        }
    }

    private void question()
    {
        verrou.lock();
        try
        {
            System.out.println(nom + "Posez votre question : ");
            sc.nextLine();
            reponse.signalAll(); // libere la condition "reponse"
            question.await(); // attends la libération de "question" pour continuer
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
