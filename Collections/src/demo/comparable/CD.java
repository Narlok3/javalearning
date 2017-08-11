package demo.comparable;

public class CD implements Comparable {
    private String auteur, titre;
    private double prix;

    public CD(String auteur, String titre, double prix) {
	this.auteur = auteur;
	this.titre = titre;
	this.prix = prix;
    }

    @Override
    public String toString() {
	return "CD [auteur=" + auteur + ", titre=" + titre + ", prix=" + prix
		+ "]";
    }

    public String getAuteur() {
	return auteur;
    }

    public String getTitre() {
	return titre;
    }

    public double getPrix() {
	return prix;
    }

    @Override
    public int compareTo(Object o) {
	if (o.getClass().equals(CD.class)) {
	    // Nous allons trier sur le nom d'artiste
	    CD cd = (CD) o;
	    // Si les deux CD ont le même auteur, on trie sur le nom de l'album
	    if (auteur.compareTo(cd.getAuteur()) == 0) {
		return titre.compareTo(cd.getTitre());
	    }
	    return auteur.compareTo(cd.getAuteur());
	}
	return -1;
    }
}
