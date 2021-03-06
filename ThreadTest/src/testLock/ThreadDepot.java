package testLock;

import java.util.Random;

public class ThreadDepot
    extends Thread
{

    private CompteEnBanque ceb;

    private Random rand = new Random();

    public ThreadDepot(CompteEnBanque c)
    {
        ceb = c;
        setName("Dépôt");
    }

    @Override
    public void run()
    {
        while (true)
        {

            int nb = rand.nextInt(100);
            long montant = Integer.valueOf(nb).longValue();
            ceb.depot(montant);
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}
