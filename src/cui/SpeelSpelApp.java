package cui;

import domein.DomeinController;
import taal.Taal;

public class SpeelSpelApp {

	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	
	public SpeelSpelApp(DomeinController dc) {
		this.dc = dc;
	}
	
	public void spelMainLoop() {
		
	}
	
}
