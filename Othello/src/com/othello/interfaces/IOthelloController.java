package com.othello.interfaces;

//C'est une interface
//Pas de dependances

public interface IOthelloController {

    public void setEcran(IView view);

    public void showAbout();

    public void quit();

    public void newGame();

    public void start();
}
