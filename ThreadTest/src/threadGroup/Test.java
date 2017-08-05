package threadGroup;

class Test
    implements Runnable
{
    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println(Thread.currentThread().getName() + " - " + ++(TestProcessusThread.entier));
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                // Ici, lorsque nous invoquons l'interruption de thread
                // la méthode sleep lève une exception
                // nous terminons donc notre boucle à ce moment
                break;
            }
        }
    }
}
