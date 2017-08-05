package callableFutureTest;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    public static void main(String[] args) {
	ExecutorService executor = Executors.newCachedThreadPool();
	// Callable is a parametrized class
	// the parameter is of the type you want to return
	// The method call will be of that type
	// Future<?> =.... Callable<Void> can be used if we don't want a return
	// result => return null;
	Future<Integer> future = executor.submit(new Callable<Integer>() {
	    // When we submit the executor, it returns a future
	    @Override
	    public Integer call() throws Exception {
		Random random = new Random();
		int duration = random.nextInt(4000);

		if (duration > 2000) {
		    throw new IOException("Sleeping for too long");
		}
		System.out.println("Starting");
		try {
		    Thread.sleep(duration);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.println("Finished");

		return duration;
	    }
	});

	executor.shutdown();

	// executor.awaitTermination(1, TimeUnit.DAYS));

	try {
	    System.out.println("Result is : " + future.get());
	    // get will throw and executionexception
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ExecutionException e) {
	    // TODO Auto-generated catch block
	    // e.printStackTrace();
	    System.out.println(e);
	}
    }
}
