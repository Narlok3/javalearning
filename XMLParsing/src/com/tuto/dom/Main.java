package com.tuto.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
	// Nous récupérons une instance de factory qui se chargera de nous
	// fournir
	// un parseur
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	try {
	    // Création de notre parseur via la factory
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    File fileXML = new File("src/com/tuto/dom/test-xsd.xml");

	    // parsing de notre fichier via un objet File et récupération d'un
	    // objet Document
	    // Ce dernier représente la hiérarchie d'objet créée pendant le
	    // parsing
	    Document xml = builder.parse(fileXML);

	    // Via notre objet Document, nous pouvons récupérer un objet Element
	    // Ce dernier représente un élément XML mais, avec la méthode
	    // ci-dessous,
	    // cet élément sera la racine du document
	    Element root = xml.getDocumentElement();
	    System.out.println(root.getNodeName());

	    // test

	    // Parsing d'un XML via une URL
	    /*
	     * String uri = "http://www.w3schools.com/xml/note.xml"; xml =
	     * builder.parse(uri); root = xml.getDocumentElement();
	     * System.out.println(root.getNodeName());
	     */

	    // Parsing d'un XML via un flux
	    InputStream is = new FileInputStream(fileXML);
	    xml = builder.parse(is);
	    root = xml.getDocumentElement();
	    System.out.println(root.getNodeName());

	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	} catch (SAXException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}