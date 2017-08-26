package com.random;

import java.security.CodeSigner;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;

public class TestClass {
    private CodeSigner testCodeSigner;
    private static SecureRandom testSecureRandom;

    public static void main(String[] args) {
	testSecureRandom = new SecureRandom();
	DoubleStream testDoubleStream = testSecureRandom.doubles(0, 100);
	List<Double> liste = new ArrayList<Double>();
	for (int i = 0; i < 100; i++) {
	    liste.add(testSecureRandom.nextDouble() * 1000);
	}
	Consumer<Double> testConsumer = (e) -> System.out.println(e);
	liste.forEach(testConsumer);

	Test t = new Test() {

	    @Override
	    public void method(int i) {
		System.out.println(i);
	    }
	};

	t.method(1);

	TestFunc tf = System.out::print;
	TestFunc tf2 = u -> System.out.println(u); // idem

	int[][] a = new int[3][3];

	Arrays.fill(a[0], 0);
	Arrays.fill(a[1], 1);
	Arrays.fill(a[2], 2);

	System.out.println("a");
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		tf.method(a[i][j]);
	    }
	    System.out.println();
	}

	int[][] b = Arrays.stream(a).map(int[]::clone).toArray(int[][]::new);
	// a : tableau, Arrays.stream(a) => transforme le tableau en stream
	// .map() : retourne un stream x -> f(x) après application de la
	// fonction
	// définie dans map
	// int[]::clone => les éléments en entrées sont des tableaux, on les
	// clone
	// .toArray(int[][]::new) : on crée un tableau 2D avec l'entrée
	int[][] c = Arrays.stream(a).map((int[] i) -> i.clone())
		.toArray(int[][]::new);

	int[][] d = a.clone();
	// clone() va créer une nouvelle référence avec une copie de a.
	// Cependant, a est constitué de 3 tableaux qui vont être copiés
	// Ceux ci vont être copiés par référence et non par valeur.
	for (int i = 0; i < a.length; i++) {
	    d[i] = a[i].clone();
	}
	// ici, on va cloner les sous-tableaux également, qui contient bien des
	// valeurs.

	d[0][0] = 6;
	System.out.println("d");
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		tf.method(d[i][j]);
	    }
	    System.out.println();
	}

	b[0][0] = 6;

	System.out.println("b");
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		tf.method(b[i][j]);
	    }
	    System.out.println();
	}

    }

    private static interface Test {
	public void method(int i);
    }

    @FunctionalInterface
    private static interface TestFunc {
	public void method(int i);
    }
}
