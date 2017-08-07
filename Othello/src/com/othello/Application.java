package com.othello;

import javax.swing.SwingUtilities;

import com.othello.controller.OthelloController;
import com.othello.model.Model;
import com.othello.view.View;

public class Application {
    public static void main(String[] args) {

	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		runApp();
	    }
	});
    }

    // the model is completely independent of the other two packages
    // the model should never import anything from the view package or from the
    // controller
    public static void runApp() {

	Model model = new Model();
	// The view represents the data from the model.
	// it will have to import classes from the model packages
	// we'll have to pass a reference of the model to the view
	View view = new View(model);
	// the controller is sending commands to the model and to the view
	OthelloController controller = new OthelloController(model, view);

	// observer pattern:
	// we want the controller to be the listener of the view
	// the view is going to be the subject

    }
}