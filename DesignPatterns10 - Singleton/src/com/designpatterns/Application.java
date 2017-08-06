package com.designpatterns;

import javax.swing.SwingUtilities;

import com.designpatterns.controller.Controller;
import com.designpatterns.model.Model;
import com.designpatterns.view.View;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				runApp();
			}
			
		});
	}
	
	public static void runApp() {
		Model model = new Model();
		View view = new View(model);

		Controller controller = new Controller(view, model);
		
		view.setLoginListener(controller);
	}

}
