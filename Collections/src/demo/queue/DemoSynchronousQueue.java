package demo.queue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class DemoSynchronousQueue {
    public static void main(String[] args) {
	SynchronousQueue<String> sq = new SynchronousQueue<String>();

	Producteur p = new Producteur(sq);
	Consommateur c = new Consommateur(sq);
	p.start();
	c.start();

    }
}

/**
 * Cette classe se charge de mettre des données dans la collection
 */
class Producteur extends Thread {
    private SynchronousQueue<String> queue;

    public Producteur(SynchronousQueue<String> q) {
	queue = q;
    }

    @Override
    public void run() {
	Random rand = new Random();

	for (int i = 0; i < 5; i++) {
	    String str = "N° " + rand.nextInt(100);
	    try {
		queue.put(str);
		System.out.println("Ajout de l'élément " + str
			+ " dans la collection OK");
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}

/**
 * Cette classe se charge de récupérer la donnée
 */
class Consommateur extends Thread {
    private SynchronousQueue<String> queue;

    public Consommateur(SynchronousQueue<String> q) {
	queue = q;
    }

    @Override
    public void run() {
	for (int i = 0; i < 5; i++) {
	    // On met une temporisation pour éviter les tours de boucle à vide
	    try {
		this.sleep(1000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    String str = queue.poll();
	    System.out.println("Elément " + str + " retiré de la collection");
	}
    }
}