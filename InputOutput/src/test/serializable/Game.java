package test.serializable;

//Package à importer
import java.io.Serializable;

public class Game implements Serializable {
    private String nom, style;
    private double prix;

    public Game(String nom, String style, double prix) {
	this.nom = nom;
	this.style = style;
	this.prix = prix;
    }

    @Override
    public String toString() {
	return "Nom du jeu : " + nom + "\n Style de jeu : " + style
		+ "\n Prix du jeu : " + prix + "\n";
    }
}