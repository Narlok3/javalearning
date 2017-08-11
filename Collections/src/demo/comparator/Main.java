package demo.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import demo.comparable.CD;

public class Main {

    public static void main(String[] args) {
	List<CD> list = new ArrayList<CD>();
	list.add(new CD("Les arcandiers", "7€", 7d));
	list.add(new CD("Frank Zappa", "Tinseltown rebellion", 10.25d));
	list.add(new CD("Frank Zappa", "Bongo Fury", 10.25d));
	list.add(new CD("King Crimson", "red", 15.30d));
	list.add(new CD("Joe Zawinul", "World tour", 12.15d));

	System.out.println("Avant le tri : ");
	Iterator<CD> it = list.iterator();
	while (it.hasNext()) {
	    System.out.println(it.next());
	}

	Collections.sort(list);

	System.out.println("Après le tri : ");
	it = list.iterator();
	while (it.hasNext()) {
	    System.out.println(it.next());
	}

	System.out.println("Après le tri avec notre comparateur");
	// nous créons une classe anonyme ici, mais rien ne vous empêche d'en
	// créer une dans un fichier séparé
	Collections.sort(list, new Comparator<CD>() {
	    public int compare(CD cd1, CD cd2) {
		Double prix1 = (Double) cd1.getPrix();
		Double prix2 = (Double) cd2.getPrix();
		int result = prix1.compareTo(prix2);
		// dans le cas ou 2 CD auraient le même prix...
		if (result == 0) {
		    return cd1.compareTo(cd2);
		}
		return result;
	    }
	});

	it = list.iterator();
	while (it.hasNext()) {
	    System.out.println(it.next());
	}
    }
}
