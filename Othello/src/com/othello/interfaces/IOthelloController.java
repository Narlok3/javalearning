package com.othello.interfaces;

//C'est une interface
//Pas de dependances

public interface IOthelloController {

    public void setView(IOthelloView view);

    public void showAbout();

    public void quit();

    public void newGame();

    public void endGame();

    public void start();

    public void playTurn(int i, int j);
}
