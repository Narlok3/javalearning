package com.othello.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.othello.controller.OthelloController;

public class PlayActionListener implements ActionListener{
	
	private OthelloController controller;
	
	public PlayActionListener(OthelloController controller){
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = ((JButton)e.getSource());
		System.out.println(b.getActionCommand());
		controller.playTurn(Integer.parseInt(b.getActionCommand().substring(0,1)), Integer.parseInt(b.getActionCommand().substring(1,2)));
		System.out.println(Integer.parseInt(b.getActionCommand().substring(0,1)) + " et le " + Integer.parseInt(b.getActionCommand().substring(1,2)));
	}

}
