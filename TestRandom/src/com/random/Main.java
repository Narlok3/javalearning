package com.random;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static class ArrayUtils {

	public static <T> void printArray(T[] i) {
	    Arrays.stream(i).forEach(System.out::print);
	    System.out.println();
	}

	public static <T> void fillArray(T[] i) {
	    Arrays.fill(i, 0);
	}

    }

    private static class Array2DUtils {

	public static <T> void printArray2D(T[][] i) {
	    Arrays.stream(i).forEach(ArrayUtils::printArray);
	}

	public static <T> void fillArray2D(T[][] i) {
	    // Arrays.stream(i).forEach(ArrayUtils::fillArray);
	    Arrays.stream(i).forEach(e -> ArrayUtils.fillArray(e));
	}

    }

    private static class MaListe extends ArrayList<Integer> {

	public void forEach(Test t) {

	    MaListe list = new MaListe();
	    for (Integer x : this) {
		list.add(t.compute(x, size()));
	    }

	    clear();

	    list.forEach(this::add);
	}
    }

    public static void main(String args[]) {

	Integer[][] tab = new Integer[3][3];
	// Array2DUtils.fillArray2D(tab);
	// Array2DUtils.printArray2D(tab);

	Test t = (a, b) -> a * b;
	MaListe l = new MaListe();
	l.add(1);
	l.add(3);
	l.add(2);
	l.forEach(t);

	t = (a, b) -> a + b;
	l.forEach(t);
	l.forEach(System.out::println);

    }

    @FunctionalInterface
    private static interface Test {
	public int compute(Integer a, Integer b);
    }

}