package demo.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DemoConcurrentLinkedQueue {

    public static void main(String[] args) {
	// ces listes servent uniquement à répertorier les ajouts et
	// suppressions
	List<Integer> listAjout = new ArrayList<Integer>();
	List<Integer> listRetire = new ArrayList<Integer>();
	List<Integer> listAjoutThread = new ArrayList<Integer>();

	// On crée notre collection
	ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

	// Un thread qui va se charger de retirer et d'ajouter des éléments
	MyThread t = new MyThread(queue, listRetire, listAjoutThread);
	t.start();

	// Et nous ajoutons des éléments depuis notre thread principal
	for (int i = 0; i < 10; i++) {
	    Random rand = new Random();
	    Integer integer = rand.nextInt();
	    queue.offer(integer);
	    listAjout.add(integer);
	    try {
		Thread.currentThread().sleep(1000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	System.out.println(listAjout);
	System.out.println(listAjoutThread);
	System.out.println(listRetire);
    }
}

class MyThread extends Thread {
    private ConcurrentLinkedQueue<Integer> queue;
    private List<Integer> list, listAjout;

    public MyThread(ConcurrentLinkedQueue<Integer> q, List<Integer> pList,
	    List<Integer> pList2) {
	queue = q;
	list = pList;
	listAjout = pList2;
    }

    @Override
    public void run() {
	int nb = 0;
	Random rand = new Random();
	while (true) {
	    try {
		this.sleep(1000);
		// On ajoute un élément dans la collection
		Integer ajout = rand.nextInt(10);
		queue.offer(ajout);

		// On retire un élément
		Integer integer = queue.poll();

		// On stocke les deux actions dans nos listes
		list.add(integer);
		listAjout.add(ajout);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    nb++;
	    if (nb > 10) {
		break;
	    }
	}
    }

    public List<Integer> getList() {
	return list;
    }

    public List<Integer> getListAjout() {
	return listAjout;
    }
}