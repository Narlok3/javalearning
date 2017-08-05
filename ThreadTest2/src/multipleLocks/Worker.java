package multipleLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker extends Thread {
    private Random random = new Random();

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    // use synchronized(lock1) & synchronized(lock2) to lock the two methods
    // instead.

    private List<Integer> list1 = new ArrayList<Integer>();

    private List<Integer> list2 = new ArrayList<Integer>();

    // public synchronized void stageOne()
    public void stageOne() {
	synchronized (lock1) {
	    try {
		Thread.sleep(1);
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	    list1.add(random.nextInt(100));
	}

    }

    // public synchronized void stageTwo()
    public void stageTwo() {
	synchronized (lock2) {
	    try {
		Thread.sleep(1);
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	    list2.add(random.nextInt(100));
	}
    }

    public void process() {
	for (int i = 0; i < 1000; i++) {
	    stageOne();
	    stageTwo();
	}

    }

    public void main() {
	System.out.println("Starting...");

	long start = System.currentTimeMillis();
	Thread t1 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		process();
	    }

	});
	Thread t2 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		process();
	    }

	});
	t1.start();
	t2.start();

	try {
	    t1.join();
	    t2.join();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	long end = System.currentTimeMillis();
	System.out.println("Time taken : " + (end - start));
	System.out.println("List1 : " + list1.size() + "; List2 : "
		+ list2.size());
    }
}

// There is only one intrinsec lock per object.
// So, when a thread is running synchronized stageOne
// The whole object is locked and so, the other thread can't run synchronized
// stageTwo
// because they both need the same lock.
// But, those methods are independent...so we don't really want this.
// we can do that by using the lock class...
