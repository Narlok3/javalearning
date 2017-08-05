package threadGroup;

public class TestProcessusThread
{
    public static Integer entier = 0;

    public static void main(String[] args)
    {

        // On crée notre groupe en lui donnant un nom
        ThreadGroup tg = new ThreadGroup("Mon groupe");

        // Le constructeur de l'objet Thread peut prendre ce paramètre
        Thread t1 = new Thread(tg, new Test());
        Thread t2 = new Thread(tg, new Test());
        Thread t3 = new Thread(tg, new Test());
        Thread t4 = new Thread(tg, new Test());

        // On lance nos threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Et après une petite pause
        try
        {
            Thread.currentThread().sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        // On demande à notre groupe d'interrompre le traitement
        // de tous les threads du groupe, c'est ce qui lève l'exception
        // dans la classe Test
        tg.interrupt();
    }
}
