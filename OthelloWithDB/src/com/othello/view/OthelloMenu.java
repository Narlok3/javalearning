package com.othello.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.othello.controller.OthelloController;
import com.othello.view.actions.LoginActionListener;
import com.othello.view.actions.SaveActionListener;

public class OthelloMenu extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private JMenu fichier;
    private JMenu options;
    private JMenu about;

    public OthelloMenu(OthelloController controller) {

	fichier = new JMenu("Fichier");
	JMenuItem menu_new = new JMenuItem("Nouvelle partie");
	menu_new.addActionListener((event -> controller.newGame()));
	fichier.add(menu_new);
	fichier.addSeparator();
	
	JMenuItem menu_login = new JMenuItem("User login");
	menu_login.addActionListener(new LoginActionListener(controller));
	fichier.add(menu_login);
	fichier.addSeparator();
	
	JMenuItem menu_save = new JMenuItem("Sauvegarder la partie");
	menu_save.addActionListener(new SaveActionListener(controller));
	fichier.add(menu_save);
	fichier.addSeparator();
	
	JMenuItem menu_load = new JMenuItem("Charger la derniere partie");
	menu_load.addActionListener(event -> controller.loadLastGame());
	fichier.add(menu_load);
	fichier.addSeparator();
	
	JMenuItem menu_exit = new JMenuItem("Quitter");
	menu_exit.addActionListener(event -> controller.quit());
	fichier.add(menu_exit);

	options = new JMenu("Options");
	JMenuItem menu_showMoves = new JMenuItem(
		"Basculer l'affichage des coups légaux");
	menu_showMoves.addActionListener(event -> controller.toggleShowMoves());
	options.add(menu_showMoves);
	options.addSeparator();

	JMenuItem menu_cancelmove = new JMenuItem("Annuler le coup");
	menu_cancelmove.addActionListener(event -> controller.cancelMove());
	options.add(menu_cancelmove);

	about = new JMenu("A propos");
	JMenuItem menu_about = new JMenuItem("A propos");
	menu_about.addActionListener(event -> controller.showAbout());
	about.add(menu_about);

	this.add(fichier);
	this.add(options);
	this.add(about);
    }
}
