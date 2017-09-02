package com.othello.interfaces;

import java.util.List;
import java.util.Map;

public interface IOthelloView {

    public void print();

    public void printNewGame();

    public void printHistory(List<Map<String, String>> gamesHistory);

    public void close();

    public void printAbout();

    public void printEndGame();

    public void refreshView();

}
