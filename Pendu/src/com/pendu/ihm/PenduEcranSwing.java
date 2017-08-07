package com.pendu.ihm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.pendu.interfaces.PenduController;
import com.pendu.interfaces.PenduEcran;

//C'est une impl�mentation
//Ne dois d�pendre que d'interfaces
//Ou de classes dans le m�me package ou sous package

public class PenduEcranSwing implements PenduEcran{
	
	private JFrame ecran;
	private PenduPanel penduPanel;
	
	private final static String TITRE_ECRAN = "Pendu";
	private final static int FRAME_WIDTH = 1000;
	private final static int FRAME_HEIGHT = 1000;
    private final static String ABOUT = "C'est le jeu du pendu.";
    private final static String WIN = "Gagn�.";
    private final static String LOSE = "Perdu.";
	
	public PenduEcranSwing(PenduController controller){
		ecran = new JFrame();
		ecran.setTitle(TITRE_ECRAN);
		ecran.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		ecran.setJMenuBar(new PenduMenu(controller));
		penduPanel = new PenduPanel(controller);
		ecran.setContentPane(penduPanel);
		ecran.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void print(){
		ecran.setVisible(true);
	}

	public void close() {
		ecran.dispose();
	}

	public void printAbout() {
		JOptionPane.showMessageDialog(ecran, ABOUT);
	}

	public void printNewGame() {
		penduPanel.initialise();
		ecran.repaint();
		ecran.setVisible(true);
	}

	public void updateMot(String mot) {
		penduPanel.setMot(mot);
		ecran.repaint();
		ecran.setVisible(true);
	}

	public void printWin() {
		JOptionPane.showMessageDialog(ecran, WIN);
		penduPanel.setEnabled(false);
		ecran.repaint();
		ecran.setVisible(true);
	}
	
	public void printLose() {
		JOptionPane.showMessageDialog(ecran, LOSE);
		penduPanel.setEnabled(false);
		ecran.repaint();
		ecran.setVisible(true);
	}

}
