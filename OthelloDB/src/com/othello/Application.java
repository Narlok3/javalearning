package com.othello;

import javax.swing.SwingUtilities;

import com.othello.controller.OthelloController;
import com.othello.model.Model;
import com.othello.model.database.Database;
import com.othello.view.OthelloView;

public class Application {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		runApp();
	    }
	});
	// we could still code here to continue in the main thread
    }

    public static void runApp() {
	Model model = new Model();
	OthelloController controller = new OthelloController(model);
	OthelloView view = new OthelloView(controller);
	controller.setView(view);
	try {
		Database.getInstance().connect();
	} catch (Exception e) {
		System.out.println("Unable to connect to the database");
		e.printStackTrace();
	}
	controller.start();
    }
}