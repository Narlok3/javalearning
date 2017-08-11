package test.decorator;

public class CoucheCaramel extends Couche {
    public CoucheCaramel(Patisserie p) {
	super(p);
	nom = "\t- Une couche de caramel.\n";
    }
}