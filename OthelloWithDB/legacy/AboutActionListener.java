package com.othello.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.othello.interfaces.IOthelloController;

public class AboutActionListener implements ActionListener {

    private IOthelloController controller;

    public AboutActionListener(IOthelloController controller) {
    	this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	controller.showAbout();
    }
}
