package demo.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DemoBlockingQueues {

    public static void main(String[] args) {

	// Nous allons tester avec trois implémentations pour commencer

	// test de l'implémentation LinkedBlockingQueue
	LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<Integer>(10);
	System.out.println("Exemple avec l'objet LinkedBlockingDeque<T> : ");
	System.out.println("----------------------------------------------");
	doIt(lbq);

	System.out.println("----------------------------------------------");

	// test de l'implémentation ArrayBlockingQueue
	ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(10);
	System.out.println("Exemple avec l'objet ArrayBlockingQueue<T> : ");
	System.out.println("----------------------------------------------");
	doIt(abq);

	System.out.println("----------------------------------------------");

	// test de l'implémentation PriorityBlockingQueue
	PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<Integer>(
		10);
	System.out.println("Exemple avec l'objet PriorityBlockingQueue<T> : ");
	System.out.println("----------------------------------------------");
	doIt(pbq);

    }

    static void doIt(BlockingQueue<Integer> bq) {
	// Création d'une collection n'acceptant que 10 entrées
	BlockingQueue<Integer> lbq = bq;
	// Nous allons tester la concurrence sur cette collection
	SampleThread t1 = new SampleThread(null, 0, lbq);
	SampleThread t2 = new SampleThread(TimeUnit.MILLISECONDS, 10L, lbq);

	// Nous lançons les threads
	// t1 ne nous rendra pas la main car nous tentons d'ajouter des éléments
	// Alors que la collection sera considérée comme pleine
	t1.start();
	// alors que ce Thread terminera normalement car nous ne bloquons le
	// thread
	// que pendant un certain temps
	t2.start();

	// On attend quelques secondes et on lance une suppression des éléments
	try {
	    System.out.println("Pause de 10 secondes");
	    Thread.sleep(10000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	System.out.println("Suppression de quelques éléments de la collection");
	for (int i = 0; i < 10; i++) {
	    lbq.poll();
	    // après cette série de suppressions, le premier thread arrive à
	    // terminer son travail
	}

	// Nous attendons quelques secondes
	try {
	    System.out.println("Pause de 3 secondes");
	    Thread.sleep(3000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	LinkedBlockingQueue<Integer> lbq2 = new LinkedBlockingQueue<Integer>(3);
	// nous tentons maintenant de mettre le contenu notre première
	// collection
	// dans la seconde, mais ceci lèvera une exception car le contenu de la
	// première est trop volumineux
	// pour la seconde !
	System.out.println("Capacité restante : " + lbq.remainingCapacity());
	System.out.println("Capacité restante : " + lbq2.remainingCapacity());
	try {
	    lbq.drainTo(lbq2);
	} catch (IllegalStateException e) {
	    System.out.println("La queue est pleine !! ");
	}

	System.out.println(lbq2);
    }
}

class SampleThread extends Thread {
    private TimeUnit tu;
    private long timeout;
    private BlockingQueue<Integer> lbd;

    public <T> SampleThread(TimeUnit tu, long timeout,
	    BlockingQueue<Integer> lbd) {
	this.tu = tu;
	this.timeout = timeout;
	this.lbd = lbd;
    }

    @Override
    public void run() {
	Random rand = new Random();
	for (int i = 0; i < 10; i++) {

	    Integer integer = rand.nextInt(20);

	    // Si pas de critère de durée
	    // On utilise la méthode qui bloque le thread indéfiniment
	    if (tu == null) {
		try {
		    if ((i % 2) == 0) {
			lbd.put(integer);

		    } else {
			lbd.put(integer);
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	    // Sinon, on ne bloque que quelques secondes
	    else {
		try {
		    if ((i % 2) == 0) {
			lbd.offer(integer, timeout, tu);
		    } else {
			lbd.offer(integer, timeout, tu);
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	    try {
		this.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	// On affiche le résultat lors de la fin du traitement
	System.out.println("À la fin du thread " + getName() + " : " + lbd);
    }
}