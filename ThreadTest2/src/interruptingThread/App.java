package interruptingThread;

import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {

	System.out.println("Thread starting...");
	Thread t1 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		Random random = new Random();
		for (int i = 0; i < 1E8; i++) {
		    // Thread.currentThread() to make sure we have the current
		    // running thread
		    if (Thread.currentThread().isInterrupted()) {
			// .Interrupted() also sets back the flag to false
			System.out.println("Interrupted!");
			break;
		    }

		    /*
		     * try { Thread.sleep(1); } catch (InterruptedException e) {
		     * // TODO Auto-generated catch block e.printStackTrace(); }
		     */
		    Math.sin(random.nextDouble());

		}

	    }

	});

	t1.start();

	t1.interrupt();
	// interrupt() doesn't really interrupt the thread.
	// instead, it sets its interrupted flag to true.
	// if the thread is sleeping, interrupt() will make it throw
	// an interruptedexception
	t1.join();

	System.out.println("Finished");
    }
}
