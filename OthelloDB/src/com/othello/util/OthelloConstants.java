package com.othello.util;

import java.awt.Color;

public class OthelloConstants {
    public final static int WIDTH = 8;
    public final static int HEIGHT = 8;
    public final static String ABOUT = "C'est le jeu de l'Othello.";
    public final static Color backGround = new Color(255, 212, 128);

    public enum Turn {
	BLACK, WHITE;
    }

    public enum CellStatus {
	BLACK, WHITE, EMPTY, OFFBOARD
    }
}
