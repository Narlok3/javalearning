package com.tuto.xpath;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Main2 {

    public static void main(String[] args) {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	try {
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    File fileXML = new File("src/com/tuto/dom/test-xsd.xml");
	    Document xml = builder.parse(fileXML);
	    Element root = xml.getDocumentElement();
	    XPathFactory xpf = XPathFactory.newInstance();
	    XPath path = xpf.newXPath();

	    String expression = "/racine/tronc/branche[1]";
	    String str = path.evaluate(expression, root);
	    System.out.println(str);
	    System.out.println("-------------------------------------");

	    expression = "/racine/tronc/branche[2]";
	    str = path.evaluate(expression, root);
	    System.out.println(str);
	    System.out.println("-------------------------------------");

	    expression = "/racine/tronc/branche[1]/feuille[1]";
	    str = path.evaluate(expression, root);
	    System.out.println(str);
	    System.out.println("-------------------------------------");

	    expression = "/racine/tronc/branche[2]/feuille[2]";
	    str = path.evaluate(expression, root);
	    System.out.println(str);
	    System.out.println("-------------------------------------");

	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	} catch (SAXException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (XPathExpressionException e) {
	    e.printStackTrace();
	}
    }
}
