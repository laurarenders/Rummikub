package domein;

import java.util.ArrayList;
import java.util.List;


public class SpelerHand {

	private List<Steen> spelerHand = new ArrayList<>();
	private List<Steen> backupSpelerHand = new ArrayList<>();

	public SpelerHand() {
	}

	public List<Steen> getSpelerHand() {
		return spelerHand;
	}

	/**
	 * @author EllyY
	 *Stenen verwijderen uit spelerhand na 
	 *het plaatsen van een goede sequentie op het spelbord.
	 *@param id van de steen om te verwijderen uit spelerhand 
	 */
	public void removeStenenUitHand(int id) {
		for (Steen steen : spelerHand) {
			if (steen.getId() == id) {
				spelerHand.remove(spelerHand.indexOf(steen));
				break;
			}
		}
	}

	public void neemSteenUitZak(Steen steen) {
		spelerHand.add(steen);
		maakBackup();
	}

	public boolean isSpelerHandLeeg() {
		return spelerHand.size() == 0;
	}

	// Methode: som stenen teruggeven
	public int geefStenenSom() {
		int somStenen = 0;
		for (Steen steen : spelerHand) {
			somStenen += steen.getGetal();
		}
		return somStenen;
	}

	public void maakSpelerhandLeeg() {
		spelerHand.clear();
	}

	public void maakBackup() {
		backupSpelerHand.clear();
		for (Steen steen : spelerHand) {
			backupSpelerHand.add(steen);
		}
	}

	public void reset() {
		spelerHand.clear();
		for (Steen steen : backupSpelerHand) {
			spelerHand.add(steen);
		}
	}
}
