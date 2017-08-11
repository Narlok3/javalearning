package com.designpatterns.decorator;

//abstract decorator class - note that it implements Window
abstract class WindowDecorator implements Window {
    protected Window windowToBeDecorated; // the Window being decorated

    public WindowDecorator(Window windowToBeDecorated) {
	this.windowToBeDecorated = windowToBeDecorated;
    }

    @Override
    public void draw() {
	windowToBeDecorated.draw(); // Delegation
    }

    @Override
    public String getDescription() {
	return windowToBeDecorated.getDescription(); // Delegation
    }
}
