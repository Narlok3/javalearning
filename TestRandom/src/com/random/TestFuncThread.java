package com.random;

public class TestFuncThread {
    public static void main(String[] args) {
	Thread t1 = new Thread(TestFuncThread::threadRun);
	t1.start();
	for (int i = 0; i < 100; i++) {
	    Thread th = new Thread(TestFuncThread::threadRun);
	    th.start();
	}

	Thread oldThread = new Thread(new Runnable() {
	    @Override
	    public void run() {
		threadRun();
	    }
	});
	oldThread.start();
    }

    static void threadRun() {
	System.out.println("Hello world, I'm " + Thread.currentThread());
    }
}
