package conversationTpTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main
{
    public static void main(String[] args)
    {
        Lock lock = new ReentrantLock();
        Condition question = lock.newCondition();
        Condition reponse = lock.newCondition();

        Journaliste j = new Journaliste("PPDA", lock, question, reponse);
        Personne p = new Personne("cysboy", lock, question, reponse);

        j.start();
        p.start();
    }
}
