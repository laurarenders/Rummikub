

package domein;

import java.util.*;
import exceptions.AantalSpelersException;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerAantalBereikt;
import exceptions.SpelerException;
import exceptions.SteenSequentieException;

public class Spel {

	//Attributen
	private final int MAX_AANTAL_SPELERS = 4;
	private final int MIN_AANTAL_SPELERS = 2;
	private int aantalSpelers;
	private final List<Speler> spelers = new ArrayList<>();
	private Zak zak;
	private int spelerAanZetIndex;
	private Speler spelerAanZet;
	private SpelBord sb;

	
	//Constructor
	public Spel(int aantalSpelers) throws AantalSpelersException {
		setAantalSpelers(aantalSpelers);
	}

	//Getters en setters
	
	public List<Speler> getSpelers() {
		return spelers;
	}

	public int getSpelerAanZetIndex() {
		return spelerAanZetIndex;
	}

	public int getAantalSpelers() {
		return aantalSpelers;
	}
	
	public void setAantalSpelers(int aantalSpelers) throws AantalSpelersException {
		if (aantalSpelers > MAX_AANTAL_SPELERS || aantalSpelers < MIN_AANTAL_SPELERS) {
			throw new AantalSpelersException();
		}
		this.aantalSpelers = aantalSpelers;
	}
	
	public int getAantalStenenInZak() {
		return zak.getAantalStenenInZak();
	}
	
	//Andere methodes: spelers aanmelden
	
	public boolean alleSpelersAangemeld() {
		return aantalSpelers == spelers.size();
	}

	public int geefAantalAangemeldeSpelers() {
		return spelers.size();
	}

	public void voegSpelerToe(Speler s) throws SpelerAantalBereikt, ReedsAangemeldException {
		if (alleSpelersAangemeld())
			throw new SpelerAantalBereikt();
		for (Speler speler : spelers) {
			if (speler.getGebruikersnaam().equals(s.getGebruikersnaam())) {
				throw new ReedsAangemeldException();
			}
		}
		spelers.add(s);
	}

	//Andere methodes: spel --> spelerAanZet
	public Speler geefSpelerAanZet() {
		return spelerAanZet;
	}
	
	private void bepaalSpelerAanZet() {
		spelerAanZetIndex = (spelerAanZetIndex + 1) % spelers.size();
		spelerAanZet = spelers.get(spelerAanZetIndex);
	}
	
	//Andere methodes: sequenties
	
	public void splitsSequentie(int sequentieIndex, int steenIndex, Steen steen) {
		this.sb.splitsRijen(sequentieIndex, steenIndex, steen);
	}

	public void voegSteenSequentieToe(SteenSequentie sequentie) {
		sb.voegSequentieToe(sequentie);
	}

	
	//Methode voor in begin van het spel, maar 1 keer gebruiken
	
	public void startSpel() throws SpelerException {
		if (!alleSpelersAangemeld()) {
			throw new SpelerException();
		}
		sb = new SpelBord();
		zak = new Zak();
		Collections.shuffle(spelers);
		spelerAanZetIndex = 0;
		spelerAanZet = spelers.get(spelerAanZetIndex);
		for (Speler speler : spelers) {
			for (int j = 0; j < 14; j++) {
				speler.neemSteenUitZak(zak.neemSteenUitZak());
			}
		}
	}
	
	//Andere methodes: spel 
	public void geefSteenUitZak() {
		spelerAanZet.getSpelerHand().neemSteenUitZak(zak.neemSteenUitZak());
	}

	public void eindeBeurt() {
		sb.maakBackup();
		spelerAanZet.getSpelerHand().maakBackup();
		bepaalSpelerAanZet();
	}

	public boolean isEindeSpel() {
		for (Speler speler : spelers) {
			int stenenInHand = speler.somSpelerHand();
			if (stenenInHand == 0) {
				berekenScore(stenenInHand);
				for (Speler speler2 : spelers) {
					speler2.maakSpelerhandLeeg();
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean valideerspelbord() {
		return sb.valideerSpelbord();	
	}

	public void resetBeurt() {
		sb.reset();
		spelerAanZet.getSpelerHand().reset();
	}

	public List<SteenSequentie> geefSpelbord() {
		return sb.geefSpelbord();
	}
	
	//Andere methodes: score
	public void berekenScore(int vorigeScore) {
		int som = 0;

		for (Speler speler : spelers) {
			som += speler.somSpelerHand();
		}
		for (Speler speler : spelers) {
			int stenenInHand = speler.somSpelerHand();
			if (stenenInHand == 0) {
				speler.voegScoreInLijst(som);
			} else {
				speler.voegScoreInLijst(-stenenInHand);
			}
		}
	}

	// UC 3
	public List<Integer> geefScoreSpel() {
		List<Integer> scores = new ArrayList<>();
		for (Speler speler : spelers) {
			scores.add(speler.getScore());
		} 
		return scores;
	}

	public List<List<Integer>> geefAlleScores() {
		List<List<Integer>> scores = new ArrayList<>();
		for (Speler speler : spelers) {
			scores.add(speler.geefScoreLijst());
		}
		return scores;
	}
	

	//Andere methodes: sequenties

	public void maakNieuweSteenSequentie(List<Steen> stenen) throws SteenSequentieException {
		sb.maakNieuweSteenSequentie(stenen);
	}

	public int geefLengteSequentieOpId(int id) {
		return sb.geefLengteSequentieOpId(id);
	}
	
	public void voegSteenToeAanSequentie(int sequentieIndex, int index, Steen steen) {
		sb.voegSteenToeAanSequentie(sequentieIndex, index, steen);
	}

}