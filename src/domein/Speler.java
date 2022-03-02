

package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Speler {

	// Attributen
	private final String gebruikersnaam;
	private String wachtwoord;
	private SpelerHand spelerHand;
	private List<Integer> scoreLijst = new ArrayList<>();

	// Constructor
	public Speler(String gebruikersnaam, String wachtwoord) {
		spelerHand = new SpelerHand();
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
	}

	// Getters en Setters
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void resetWachtwoord() {
		this.wachtwoord = "";
	}

	public SpelerHand getSpelerHand() {
		return spelerHand;
	}

	public int somSpelerHand() {
		int som = 0;
		for (Steen steen : getSpelerHand().getSpelerHand()) {
			som += steen.getGetal();
		}
		return som;
	}

	public void neemSteenUitZak(Steen steen) {
		spelerHand.neemSteenUitZak(steen);
	}

	public boolean isSpelerGewonnen() {
		return spelerHand.isSpelerHandLeeg();
	}

	public int getScore() {
		if (scoreLijst != null && scoreLijst.size() > 0)
			return scoreLijst.get(scoreLijst.size() - 1);
		else {
			throw new IllegalArgumentException();
		}
	}

	public void voegScoreInLijst(int score) {
		scoreLijst.add(score);
	}

	public List<Integer> geefScoreLijst() {
		if(scoreLijst != null) {
			return this.scoreLijst;
		}
		return Arrays.asList(0);
	}

	public void maakSpelerhandLeeg() {
		this.spelerHand.maakSpelerhandLeeg();
	}

	public void removeStenenUitSpelerHand(int id) {
		spelerHand.removeStenenUitHand(id);
	}
	
	public int somScores() {
		int som = 0;
		for (int score : geefScoreLijst()) {
		som += score;	
		}
		return som;	
	} 
}
