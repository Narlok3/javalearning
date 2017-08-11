package demo.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
	// nous créons une collection basique
	ArrayList<String> list = new ArrayList<String>();
	list.add("1");
	list.add("2");
	list.add("3");
	list.add("4");
	list.add("5");
	list.add("6");
	list.add("7");

	// Un petit compteur pour récupérer les tours de boucle
	int nbTourDeBoucle = 0;
	int nbTourDeBoucle2 = 0;

	// Nous récupérons notre itérateur
	Iterator it = list.iterator();

	// tant qu'il y a des éléments à parcourir
	while (it.hasNext()) {

	    nbTourDeBoucle++;
	    // nous récupérons l’élément courant
	    String str = (String) it.next();
	    // si nous sommes sur l'élément 4, nous le retirons de la collection
	    if (str.equals("4")) {
		it.remove();
	    }
	}

	// nous reparcourons un nouvel itérateur
	// pour nous assurer que tout a fonctionné
	it = list.iterator();

	while (it.hasNext()) {

	    nbTourDeBoucle2++;
	    System.out.println(it.next());
	    System.out.println(it.next());
	    System.out.println(it.next());
	}

	System.out.println("Nombre de tours de boucle N°1 : " + nbTourDeBoucle);
	System.out
		.println("Nombre de tours de boucle N°2 : " + nbTourDeBoucle2);

    }
}
