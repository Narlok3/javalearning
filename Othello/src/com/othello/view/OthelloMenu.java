package com.othello.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.othello.controller.OthelloController;
import com.othello.view.actions.AboutActionListener;
import com.othello.view.actions.CancelMoveActionListener;
import com.othello.view.actions.ExitActionListener;
import com.othello.view.actions.NewActionListener;
import com.othello.view.actions.ShowMovesActionListener;

public class OthelloMenu extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private JMenu fichier;
    private JMenu options;
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
    	
    	options = new JMenu("Options");
    	JMenuItem menu_showMoves = new JMenuItem("Basculer l'affichage des coups légaux");
    	menu_showMoves.addActionListener(new ShowMovesActionListener(controller));
    	options.add(menu_showMoves);
    	options.addSeparator();
    	
    	JMenuItem menu_cancelmove = new JMenuItem("Annuler le coup");
    	menu_cancelmove.addActionListener(new CancelMoveActionListener(controller));
    	options.add(menu_cancelmove);
    			
    	about = new JMenu("A propos");
    	JMenuItem menu_about = new JMenuItem("A propos");
		menu_about.addActionListener(new AboutActionListener(controller));
		about.add(menu_about);

		this.add(fichier);
		this.add(options);
		this.add(about);
    }
}
