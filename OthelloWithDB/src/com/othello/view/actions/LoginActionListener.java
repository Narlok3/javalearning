package com.othello.view.actions;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.othello.controller.OthelloController;
import com.othello.util.OthelloConstants;

public class LoginActionListener implements ActionListener {

	private OthelloController controller;

	public LoginActionListener(OthelloController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// retrieve the window.
		// we could just add a method in the view, though.
		JMenuItem parent = (JMenuItem) e.getSource();
		JPopupMenu popupMenu = (JPopupMenu) parent.getParent();
		Component invoker = popupMenu.getInvoker();
		JComponent invokerAsJComponent = (JComponent) invoker;
		Container topLevel = invokerAsJComponent.getTopLevelAncestor();

		//this part should probably go somewhere else.
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		
		label.add(new JLabel("Username", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField username = new JTextField();
		controls.add(username);
		JPasswordField password = new JPasswordField();
		controls.add(password);
		panel.add(controls, BorderLayout.CENTER);

		switch(JOptionPane.showConfirmDialog(topLevel, panel, "login", JOptionPane.OK_CANCEL_OPTION)){
		case 0 :
			System.out.println("login en cours");
			//password should be char[] and not string
			//because of garbage collection, String is not
			//destroyed immediately
			controller.login(username.getText(),new String(password.getPassword()));
			break;
		case 1 :
			break;
		}
	}
}
