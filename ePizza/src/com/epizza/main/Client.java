package com.epizza.main;

public class Client {

    private static int nClient = 0;
    private int clientID = 0;

    public Client() {
	clientID = nClient;
	nClient++;
    }
}
