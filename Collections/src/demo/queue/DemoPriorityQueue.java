package demo.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class DemoPriorityQueue {
    public static void main(String[] args) {
	Queue<Integer> q = new PriorityQueue<Integer>();
	// On tente de retirer un élément inexistant
	Integer nb = q.poll();
	// ce qui nous retourne "null"
	System.out.println(nb);

	Random r = new Random();

	// Nous allons ajouter des entiers de façon aléatoire
	for (int i = 0; i < 10; i++) {
	    q.offer(r.nextInt(20));
	}
	System.out.println("Affichage du contenu de la queue");
	System.out.println(q);

	// Nous allons parcourir notre collection
	// Et mettre son ordre dans une liste
	List<Integer> list = new ArrayList<Integer>();
	Iterator<Integer> it = q.iterator();
	while (it.hasNext()) {
	    list.add(it.next());
	}

	// Nous affichons la liste
	System.out.println("Affichage de l'ordre de la queue");
	System.out.println(list);

	// On supprime tout le contenu de notre liste
	list.removeAll(q);
	// nous allons maintenant voir comment les objets
	// sont dépilés de notre collection
	for (int i = 0; i < 10; i++) {
	    list.add(q.remove());
	}

	System.out.println("Affichage de l'ordre de dépilage");
	System.out.println(list);
    }
}
