package com.pendu.ihm;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.pendu.ihm.actions.AboutActionListener;
import com.pendu.ihm.actions.ExitActionListener;
import com.pendu.ihm.actions.NewActionListener;
import com.pendu.interfaces.PenduController;

//C'est une impl�mentation
//Ne dois d�pendre que d'interfaces
//Ou de classes dans le m�me package ou sous package

public class PenduMenu extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	
	private JMenu fichier;
	private JMenu about;
	
	public PenduMenu(PenduController controller){
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
