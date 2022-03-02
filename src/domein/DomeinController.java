

package domein;

import java.util.ArrayList;
import java.util.List;
import exceptions.AantalSpelersException;
import exceptions.SpelerException;
import exceptions.SteenSequentieException;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerAantalBereikt;

public class DomeinController {

	private Spel spel;
	private final SpelerRepository spelerRepo;

	// Constructor
	public DomeinController() {
		spelerRepo = new SpelerRepository();
	}

	
	//Speler aanmelden methodes --------------------------------------------------------------------------------------------------
	
	public void aanmeldenSpeler(String gebruikersnaam, String wachtwoord)
			throws SpelerException, ReedsAangemeldException, SpelerAantalBereikt {
		
		Speler s = spelerRepo.geefSpeler(gebruikersnaam, wachtwoord);
		if (s != null) {
			spel.voegSpelerToe(s);
		}
	}

	public void setSpelerAantal(int spelerAantal) throws AantalSpelersException {
		spel = new Spel(spelerAantal);
	}
	
	public int geefAantalSpelers() {
		return spel.getAantalSpelers();
	}

	public int geefAantalAangemeldeSpelers() {
		return spel.geefAantalAangemeldeSpelers();
	}
	
	
	//Spel methodes  --------------------------------------------------------------------------------------------------
	
	public Spel getSpel() {
		return spel;
	} 

	public void startSpel() throws SpelerException {
		spel.startSpel();
	}
	
	public List<String> geefSpelersGebruikersnamen() {
		List<String> gebruikersnamenSpelers = new ArrayList<>();
		for (Speler speler : spel.getSpelers()) {
			gebruikersnamenSpelers.add(speler.getGebruikersnaam());
		}
		return gebruikersnamenSpelers;
	}
	
	public List<Speler> geefSpelersInSpel() {
		return spel.getSpelers();
	}
	
	public void geefSteenUitZak() {
		spel.geefSteenUitZak();

	}

	public void isSpelerHandLeeg() {
		spel.geefSpelerAanZet().maakSpelerhandLeeg();
	}
	
	public void maakSpelerhandLeeg() {
		spel.geefSpelerAanZet().maakSpelerhandLeeg();
	}

	public void eindeBeurt() {
		spel.eindeBeurt();
	}

	public void removeStenenUitSpelerHand(int id) {
		geefSpelerAanZetSpeler().removeStenenUitSpelerHand(id);
	}
	
	public boolean valideerSpelbord() {
		return spel.valideerspelbord();
	}

	public void resetBeurt() {
		spel.resetBeurt();
	}

	public List<SteenSequentie> geefSpelbord() {
		return spel.geefSpelbord();
	}
	
	public boolean isEIndeSpel() {
		return spel.isEindeSpel();
	}

	
	//Speleraanzet methodes --------------------------------------------------------------------------------------------------

	public Speler geefSpelerAanZetSpeler() {
		return spel.geefSpelerAanZet();
	}

	public String geefSpelerAanZet() {
		return spel.geefSpelerAanZet().getGebruikersnaam();
	}

	public int geefSpelerAanZetIndex() {
		return spel.getSpelerAanZetIndex();
	}
	
	//Sequentie & rijen methodes --------------------------------------------------------------------------------------------------
	
	public int geefLengteSequentieOpId(int id) {
		return spel.geefLengteSequentieOpId(id);
	}
	
	public void splitsRijen(int sequentieIndex, int steenIndex, Steen steen) {
		spel.splitsSequentie(sequentieIndex, steenIndex, steen);
	}

	public void voegSequentieToeSpelbordGui(SteenSequentie steenSequentie) {
		spel.voegSteenSequentieToe(steenSequentie);
	}
	
	public void maakNieuweSteenSequentie(List<Steen> stenen) throws SteenSequentieException {
		spel.maakNieuweSteenSequentie(stenen);
	}
	
	public void voegSteenToeAanSequentie(int sequentieIndex, int index, Steen steen) {
		spel.voegSteenToeAanSequentie(sequentieIndex, index, steen);
	}

	
	//Score methodes --------------------------------------------------------------------------------------------------

	public List<Integer> geefScoreSpel() {
		return spel.geefScoreSpel();
	}

	public List<List<Integer>> geefAlleScoreS() {
		return spel.geefAlleScores();
	}
	
	public int geefScoreSom() {
		return spel.geefSpelerAanZet().somScores();
	}

}