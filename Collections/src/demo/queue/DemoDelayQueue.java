package demo.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DemoDelayQueue {
    public static void main(String[] args) {
	MyObject obj1 = new MyObject("Obj1", 500);
	MyObject obj2 = new MyObject("Obj2", 1000);
	MyObject obj3 = new MyObject("Obj3", 1500);
	MyObject obj4 = new MyObject("Obj4", 2000);

	DelayQueue<MyObject> dq = new DelayQueue<MyObject>();
	dq.put(obj1);
	dq.put(obj2);
	dq.put(obj3);
	dq.put(obj4);

	// Les éléments sont bien dans la collection mais non utilisables
	System.out.println(dq);
	System.out.println(dq.poll());

	for (int i = 0; i < 10; i++) {
	    // On attend 300 ms
	    try {
		Thread.sleep(300);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    MyObject my = dq.poll();
	    System.out.println(my);
	}
    }
}

class MyObject implements Delayed {
    private String nom;
    private long origine, delay;

    public MyObject(String name, long d) {
	nom = name;
	origine = System.currentTimeMillis();
	delay = d;
    }

    @Override
    public long getDelay(TimeUnit unit) {
	return unit.convert(delay - (System.currentTimeMillis() - origine),
		TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
	if (delayed == this) {
	    return 0;
	}

	long d = (getDelay(TimeUnit.MILLISECONDS) - delayed
		.getDelay(TimeUnit.MILLISECONDS));
	return ((d == 0) ? 0 : ((d < 0) ? -1 : 1));
    }

    @Override
    public String toString() {
	return "MyObject [nom=" + nom + "]";
    }
}
