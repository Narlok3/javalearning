package test.decorator;

public class CoucheBiscuit extends Couche {
    public CoucheBiscuit(Patisserie p) {
	super(p);
	nom = "\t- Une couche de biscuit.\n";
    }
}