package testSynchro1;

class Test
    implements Runnable
{
    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            TestProcessusThread.entier.incremente();
            System.out.println(Thread.currentThread().getName() + " - " + TestProcessusThread.entier.get());
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}
