package com.othello.interfaces;
//mauvais design: a corriger
import com.othello.view.OthelloPanel;

public interface IOthelloView {

    public void print();

    public void printNewGame();

    public void close();

    public void printAbout();

    public void printEndGame();

	public void refreshView();

}
