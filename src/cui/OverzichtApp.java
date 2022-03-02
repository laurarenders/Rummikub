package cui;

import java.util.ArrayList;
import java.util.List;


import domein.DomeinController;
import domein.Speler;
import taal.Taal;

public class OverzichtApp {

	private DomeinController dc;
	private Taal taal = Taal.getInstance();

	public OverzichtApp(DomeinController dc) {
		this.dc = dc;
	}

	public void toonOverzicht() {

		List<Speler> sp = new ArrayList<>();
		int teller = 0;
		try {
			for (Speler speler : dc.geefSpelersInSpel()) {
				System.out.printf("%s " + (teller + 1) + ": %s%n" + "Score: ", taal.geefTekst("speler"),
						speler.getGebruikersnaam());
				System.out.println(dc.geefScoreSpel().get(teller) + "	");
				teller++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		SpeelSpelApp ssa = new SpeelSpelApp(dc);
		ssa.spelMainLoop();

	}
}
