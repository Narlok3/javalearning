package com.othello.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.othello.controller.OthelloController;

public class ExitActionListener implements ActionListener {

    private OthelloController controller;

    public ExitActionListener(OthelloController controller) {
    	this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	controller.quit();
    }

}
