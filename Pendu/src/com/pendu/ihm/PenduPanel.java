package com.pendu.ihm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pendu.ihm.actions.LetterActionListener;
import com.pendu.interfaces.PenduController;

//C'est une impl�mentation
//Ne dois d�pendre que d'interfaces
//Ou de classes dans le m�me package ou sous package

public class PenduPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private PenduController controller;
	
	private JLabel mot;

	public PenduPanel(PenduController controller){
		this.controller = controller;
		mot = new JLabel();
		Font font = new Font("Arial", Font.PLAIN, 100);
		mot.setFont(font);
		mot.setHorizontalAlignment(JLabel.CENTER);
		mot.setVerticalAlignment(JLabel.CENTER);
	}
	
	public void initialise(){
		this.removeAll();
		setEnabled(true);
		BorderLayout layout = new BorderLayout();
		JPanel pendu = new JPanel();
		pendu.setSize(200, 500);
		JPanel clavier = new JPanel();
		GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(7);
        gridLayout.setRows(4);
		for(Character c : new char[]{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y','z' }){
			JButton b = new JButton(c.toString());
			b.addActionListener(new LetterActionListener(controller));
            clavier.add(b);
			gridLayout.addLayoutComponent(c.toString(), b);
		}
		clavier.setLayout(gridLayout);
		layout.addLayoutComponent(mot, BorderLayout.CENTER);
		layout.addLayoutComponent(pendu, BorderLayout.EAST);
		layout.addLayoutComponent(clavier, BorderLayout.SOUTH);
		add(mot);
		add(pendu);
		add(clavier);
		this.setLayout(layout);
		repaint();
	}

	public void setMot(String mot) {
		String text = "";
		for(Character c : mot.toCharArray()){
             text += ' ' + c.toString();
		}
		this.mot.setText(text);
	}

}
