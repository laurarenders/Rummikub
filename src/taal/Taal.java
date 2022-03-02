package taal;

import java.util.Locale;
import java.util.ResourceBundle;


public class Taal {

	private static Taal taal = new Taal();
	private ResourceBundle resources;
	
	public Taal() {
		
	}
	
	public static Taal getInstance() { 
		if(taal == null) {
	         taal = new Taal();
	      }
	      return taal;
	}
	
	public void taalInstellen(String keuze) {
		Locale currentLocale;
		//ResourceBundle resources;
		currentLocale = new Locale(keuze); //validatie if()
		resources = ResourceBundle.getBundle("resources.TranslationBundle", currentLocale);
	}
	
	public String geefTekst(String boodschap) {
		return resources.getString(boodschap);
	}
	
	public ResourceBundle getResources() {
		return this.resources;
	}
}
