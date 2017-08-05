package testReentrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    Lock lock = new ReentrantLock();
    Condition cond = lock.newCondition(); // allows to use await method

    // ReentrantLock implements Lock interface

    private void increment() {
	for (int i = 0; i < 10000; i++) {
	    count++;
	}
    }

    public void firstThread() throws InterruptedException {
	lock.lock();

	System.out.println("Waiting...");
	cond.await();
	// This will wake up when we'll call cond.signal();
	// When this wakes up, it is not enough to continue
	// the lock needs to be unlocked first

	try {
	    increment();
	} finally {
	    lock.unlock();
	}
    }

    public void secondThread() throws InterruptedException {

	Thread.sleep(2000);
	lock.lock();

	System.out.println("Press the return key");
	new Scanner(System.in).nextLine();
	System.out.println("Got the return key");

	cond.signal();
	try {
	    increment();
	} finally {
	    lock.unlock();
	}
    }

    public void finished() {
	System.out.println("Count is : " + count);
    }
}
