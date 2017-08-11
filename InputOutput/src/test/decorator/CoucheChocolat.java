package test.decorator;

public class CoucheChocolat extends Couche {
    public CoucheChocolat(Patisserie p) {
	super(p);
	nom = "\t- Une couche de chocolat.\n";
    }
}
