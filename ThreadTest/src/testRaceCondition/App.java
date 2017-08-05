package testRaceCondition;

public class App
{
    private int count = 0;

    private int synchronisedCount = 0;

    public synchronized void increment()
    {
        synchronisedCount++;
    }

    public static void main(String[] args)
    {
        App app = new App();

        app.doWork();
    }

    public void doWork()
    {
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    count++;
                    increment();
                    // equivalent to count = count + 1;
                    // ie : read count, increment it and store it back again.
                    // those are 3 different steps.
                }
            }

        });

        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    count++;
                    increment();
                    // equivalent to count = count + 1;
                    // ie : read count, increment it and store it back again.
                    // those are 3 different steps.
                }
            }

        });

        t1.start();
        t2.start();

        try
        {
            // The join method will exit only when the thread has finished running
            // so we guarantee we continue only after that.
            t1.join();
            t2.join();
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Count is : " + count);
        System.out.println("Synchronised count is : " + synchronisedCount);
    }
}

// explanation of what happens :
// count is 100
// thread 1 reads count and get 100
// thread 2 reads count and get 100
// thread 1 increments count and get the result of 101
// thread 2 increments count and get the result of 101
// We expect 102, though.
