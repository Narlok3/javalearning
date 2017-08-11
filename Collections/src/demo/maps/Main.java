package demo.maps;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Map<Integer, String> map = new HashMap<Integer, String>();
	map.put(1, "toto");
	map.put(2, "titi");
	map.put(3, "tutu");
	map.put(4, "tete");
	map.put(5, "tata");

	// Nous récupérons un Set contenant des entiers
	Set<Integer> setInt = map.keySet();
	// Utilisation d'un itérateur générique
	Iterator<Integer> it = setInt.iterator();
	System.out.println("Parcours d'une Map avec keySet : ");
	// ensuite vous savez faire
	while (it.hasNext()) {
	    int key = it.next();
	    System.out.println("Valeur pour la clé " + key + " = "
		    + map.get(key));
	}
	System.out.println("-----------------------------------");

	Set<Entry<Integer, String>> setEntry = map.entrySet();
	// Utilisation d'un iterateur générique
	Iterator<Map.Entry<Integer, String>> itEntry = setEntry.iterator();
	System.out.println("Parcours d'une Map avec setEntry : ");
	// ensuite vous savez faire
	while (itEntry.hasNext()) {
	    Map.Entry<Integer, String> entry = itEntry.next();
	    System.out.println("Valeur pour la clé " + entry.getKey() + " = "
		    + entry.getValue());
	}
	System.out.println("-----------------------------------");

	Collection<String> col = map.values();
	Iterator<String> itString = col.iterator();
	System.out
		.println("Parcours de la liste des valeurs d'une Map avec values : ");
	// ensuite vous savez faire
	while (itString.hasNext()) {
	    String value = itString.next();
	    System.out.println("Valeur : " + value);
	}
	System.out.println("-----------------------------------");

    }
}
