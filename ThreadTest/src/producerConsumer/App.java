package producerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    // BlockingQueue is a thread-safe type
    // => no worry about thread synchronization
    // Type that is going to hold data items of the specified type
    // FIFO
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
	    10);

    public static void main(String[] args) throws InterruptedException {

	Thread t1 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    producer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});

	Thread t2 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    consumer();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	t1.start();
	t2.start();

	t1.join();
	t2.join();

    }

    private static void producer() throws InterruptedException {
	Random random = new Random();

	while (true) {
	    // no need for synchronized because thread safe.
	    queue.put(random.nextInt(100));
	}
    }

    private static void consumer() throws InterruptedException {
	Random random = new Random();

	while (true) {
	    Thread.sleep(100);

	    if (random.nextInt(10) == 0) {
		Integer value = queue.take();
		System.out.println("Taken value " + value + "; queue size is "
			+ queue.size());
	    }
	}
    }
}

// It is best to avoid using low level synchronization when you can
// by using thread safe types like BlockingQueue