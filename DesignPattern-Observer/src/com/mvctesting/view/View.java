package com.mvctesting.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.mvctesting.model.Model;

public class View extends JFrame implements ActionListener {

    private Model model;
    private JButton helloButton;
    private JButton goodByeButton;

    // object that implemenents the loginListener interface
    private LoginListener loginListener;

    public View(Model model) {
	super("MVC Demo");

	this.model = model;

	helloButton = new JButton("Hello!");
	goodByeButton = new JButton("Good Bye");

	// layout manager set to GridBagLayout
	// it tells the window which layout manager to use

	setLayout(new GridBagLayout());

	GridBagConstraints gc = new GridBagConstraints();
	gc.anchor = GridBagConstraints.CENTER;
	gc.gridx = 1;
	gc.gridy = 1;
	gc.gridheight = 0;
	gc.gridwidth = 0;
	gc.weightx = 1;
	gc.weighty = 1;
	gc.fill = GridBagConstraints.NONE;

	add(helloButton, gc);

	gc.anchor = GridBagConstraints.BASELINE;
	gc.gridx = 1;
	gc.gridy = 2;
	gc.gridheight = 0;
	gc.gridwidth = 0;
	gc.weightx = 1;
	gc.weighty = 1;
	gc.fill = GridBagConstraints.NONE;

	add(goodByeButton, gc);
	// the button is our subject
	// addActionListener takes an object of type ActionListener
	// ie an object that implements the ActionListener interface
	helloButton.addActionListener(this);
	goodByeButton.addActionListener(this);
	// anonymous class template
	goodByeButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.out.println("Sorry to see you go");
	    }

	});

	setSize(600, 500);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
    }

    // Observer pattern
    @Override
    public void actionPerformed(ActionEvent e) {

	JButton source = (JButton) e.getSource();
	if (source == helloButton) {
	    System.out.println("Hello there");
	} else {
	    System.out.println("Some other button");
	}

	// String password = new String(passField.getPassword());
	// String name = nameField.getText();
	String password = "hello";
	String name = "bis";

	fireLoginEvent(new LoginFormEvent(name, password));
    }

    // fires the event
    public void setLoginListener(LoginListener loginListener) {
	/*
	 * we're passing the object and storing a reference to it // in a
	 * private field // we're able to pass a reference of the Controller to
	 * the view // in a way where the view doesn't have to know what the
	 * controller is // all it knows is that it implements the loginListener
	 * interface
	 */
	this.loginListener = loginListener;

    }

    public void fireLoginEvent(LoginFormEvent event) {
	if (loginListener != null) {
	    loginListener.loginPerformed(event);
	}
    }
}
