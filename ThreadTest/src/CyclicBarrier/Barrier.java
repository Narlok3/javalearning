package CyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Barrier
{

    public static void main(String[] args)
    {

        ExecutorService execute = Executors.newFixedThreadPool(4);
        CyclicBarrier barrier = new CyclicBarrier(4);

        CBExemple cbe1, cbe2, cbe3, cbe4;
        cbe1 = new CBExemple(0, 100, barrier, "Thread-0-100");
        cbe2 = new CBExemple(1_000, 5_000, barrier, "Thread-1000-5000");
        cbe3 = new CBExemple(5_000, 15_000, barrier, "Thread-5000-15000");
        cbe4 = new CBExemple(10_000, 50_000, barrier, "Thread-10000-50000");

        Future<Integer> ft1 = execute.submit(cbe1);
        Future<Integer> ft2 = execute.submit(cbe2);
        Future<Integer> ft3 = execute.submit(cbe3);
        Future<Integer> ft4 = execute.submit(cbe4);

        try
        {
            System.out.println("Total = " + (ft1.get() + ft2.get() + ft3.get() + ft4.get()));
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        execute.shutdown();
    }
}