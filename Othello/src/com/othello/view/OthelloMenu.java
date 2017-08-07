package com.othello.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.othello.controller.OthelloController;
import com.othello.view.actions.AboutActionListener;
import com.othello.view.actions.ExitActionListener;
import com.othello.view.actions.NewActionListener;

public class OthelloMenu extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private JMenu fichier;
    private JMenu about;

    public OthelloMenu(OthelloController controller) {
	fichier = new JMenu("Fichier");
	JMenuItem menu_new = new JMenuItem("Nouvelle partie");
	menu_new.addActionListener(new NewActionListener(controller));
	fichier.add(menu_new);
	fichier.addSeparator();

	JMenuItem menu_exit = new JMenuItem("Quitter");
	menu_exit.addActionListener(new ExitActionListener(controller));
	fichier.add(menu_exit);
	about = new JMenu("A propos");
	JMenuItem menu_about = new JMenuItem("A propos");
	menu_about.addActionListener(new AboutActionListener(controller));
	about.add(menu_about);

	this.add(fichier);
	this.add(about);
    }

}
