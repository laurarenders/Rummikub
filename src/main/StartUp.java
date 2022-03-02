package main;

import cui.KiesTaalApp;
import domein.DomeinController;
import exceptions.AantalSpelersException;
import exceptions.SpelerAantalBereikt;
import exceptions.SpelerException;

public class StartUp {

	public static void main(String[] args) throws SpelerException, SpelerAantalBereikt, AantalSpelersException {
		DomeinController dc = new DomeinController();
		KiesTaalApp taal = new KiesTaalApp(dc);
		taal.start();
		
	}
}


