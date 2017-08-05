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

    }
}
