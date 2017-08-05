package threadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor
    implements Runnable
{
    private int id;

    public Processor(int id)
    {
        this.id = id;
    }

    @Override
    public void run()
    {
        System.out.println("Starting " + id);
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Completed " + id);

    }

}

public class App
{
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) // we'll loop on starting new threads while the max thread is not reached
        {
            executor.submit(new Processor(i));
        }
        executor.shutdown(); // will wait for all the thread to complete what they are doing
        // then they are terminated
        System.out.println("All tasks submitted");

        try
        {
            executor.awaitTermination(1, TimeUnit.DAYS);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("All tasks completed");
    }
}
