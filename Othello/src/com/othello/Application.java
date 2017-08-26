package com.othello;

import javax.swing.SwingUtilities;

import com.othello.controller.OthelloController;
import com.othello.model.Model;
import com.othello.view.OthelloView;

public class Application {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		runApp();
	    }
	});
	// we could still code here to continue in the main thread.
    }

    public static void runApp() {
	Model model = new Model();
	OthelloController controller = new OthelloController(model);
	OthelloView view = new OthelloView(controller);
	controller.setView(view);
	controller.start();
    }
}