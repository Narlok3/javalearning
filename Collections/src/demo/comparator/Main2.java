package demo.comparator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import demo.comparable.CD;

public class Main2 {
    public static void main(String[] args) {
	Set<CD> set1 = new TreeSet<CD>();
	set1.add(new CD("Les arcandiers", "7€", 7d));
	set1.add(new CD("Frank Zappa", "Tinseltown rebellion", 10.25d));
	set1.add(new CD("Frank Zappa", "Bongo Fury", 10.25d));
	set1.add(new CD("King Crimson", "red", 15.30d));
	set1.add(new CD("Joe Zawinul", "World tour", 12.15d));

	Iterator<CD> it = set1.iterator();
	while (it.hasNext()) {
	    System.out.println(it.next());
	}

	// On crée directement un nouvel objet
	// en lui spécifiant sa façon de ranger les objets
	Set<CD> set2 = new TreeSet<CD>(new Comparator<CD>() {
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

	// On ajoute le contenu de la première collection
	// dans la deuxième
	set2.addAll(set1);
	System.out.println("-------------------------------");
	it = set2.iterator();
	while (it.hasNext()) {
	    System.out.println(it.next());
	}
    }
}
