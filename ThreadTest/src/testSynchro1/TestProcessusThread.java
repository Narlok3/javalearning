package testSynchro1;

public class TestProcessusThread
{

    public static Increment entier = new Increment();

    public static void main(String[] args)
    {

        Thread t1 = new Thread(new Test());
        Thread t2 = new Thread(new Test());
        Thread t3 = new Thread(new Test());
        Thread t4 = new Thread(new Test());

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try
        {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Valeur finale : " + entier.get());
    }
}
