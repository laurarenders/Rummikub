package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.SteenSequentieException;

public class SpelBord {
	private List<SteenSequentie> spelbord;
	private List<SteenSequentie> backupSpelbord;
	private int sequentieIdTeller = 0;

	public SpelBord() {
		spelbord = new ArrayList<>();
		backupSpelbord = new ArrayList<>();
	}

	public void reset() {
		spelbord.clear();
		for (SteenSequentie steenSequentie : backupSpelbord) {
			spelbord.add(steenSequentie);
		}
	}
	public void voegSequentieToe(SteenSequentie sequentie) {
		if (sequentie != null) {
			spelbord.add(sequentie);
		}
	}

	public void voegSteenToeAanSequentie(int sequentieIndex, int index, Steen steen) {
		geefSequentie(sequentieIndex).voegSteenToe(index, steen);
	}

	private SteenSequentie geefSequentie(int sequentieIndex) {
		SteenSequentie res = null;
		if (bestaatSequentieOpSpelbord(sequentieIndex))
			res = geefSequentie(sequentieIndex);
		return res;
	}

	public List<SteenSequentie> geefSpelbord() {
		return spelbord;
	}

	public boolean splitsRijen(int sequentieIndex, int steenIndex, Steen steen) {
		SteenSequentie sequentie = null;
		for(SteenSequentie seq : spelbord) {
			//als sequentieIndex wordt de sequentie id meegegeven
			if(seq.getId() == sequentieIndex) {
				sequentie = seq;		
			}
		}
		
		if (!bestaatSteenOpSpelbord(spelbord.indexOf(sequentie), steenIndex)) {
			return false;
		}
		
		SteenSequentie nieuweSequentie = sequentie.splitsSequentie(steenIndex);
		if (nieuweSequentie == null) {
			return false;
		}
		sequentie.voegSteenToe(steenIndex, steen);
		spelbord.add(nieuweSequentie);
		return true;
	}

	public boolean bestaatSteenOpSpelbord(int sequentieIndex, int steenIndex) {
		if (isSpelbordLeeg()) {
			return false;
		}
		if (bestaatSequentieOpSpelbord(sequentieIndex) && bestaatSteenOpSteenSequentie(sequentieIndex, steenIndex)) {
			return true;
		}
		return false;
	}

	public Steen verwijderSteen(int sequentieIndex, int steenSequentie) {
		SteenSequentie sequentie;
		Steen steen = null;
		if (isBewegingspositieGeldig(sequentieIndex, steenSequentie)) {
			sequentie = geefSteenSequentie(sequentieIndex);
			steen = sequentie.verwijderSteen(steenSequentie);
			if (sequentie.geefAantalStenenInSequentie() == 0) {
				spelbord.remove(sequentieIndex);
			}
		}

		return steen;
	}

	private boolean isBewegingspositieGeldig(int sequentieIndex, int steenIndex) {
		if (!bestaatSequentieOpSpelbord(sequentieIndex)) {
			return false;
		}
		return (steenIndex == 0) || (steenIndex == geefSequentie(sequentieIndex).geefAantalStenenInSequentie() - 1);
	}

	private boolean isSpelbordLeeg() {
		return spelbord.size() == 0;
	}

	private boolean bestaatSequentieOpSpelbord(int index) {
		return (index >= 0 && index < spelbord.size());
	}

	private SteenSequentie geefSteenSequentie(int index) {
		return spelbord.get(index);
	}

	private boolean bestaatSteenOpSteenSequentie(int sequentieIndex, int steenIndex) {
		return (steenIndex >= 0 && steenIndex < geefSequentie(sequentieIndex).geefAantalStenenInSequentie());
	}

	public boolean valideerSpelbord() {
		boolean geldigSpelbord = true;
		for (SteenSequentie steenSequentie : spelbord) {
			geldigSpelbord = steenSequentie.isValid();
		}
		return geldigSpelbord;
	}

	public void maakNieuweSteenSequentie(List<Steen> stenen) throws SteenSequentieException {
		SteenSequentie steenSequentie = new SteenSequentie(sequentieIdTeller, stenen);
		sequentieIdTeller++;
		spelbord.add(steenSequentie);
	}

	public int geefLengteSequentieOpId(int id) {
		for(SteenSequentie seq: spelbord) {
			if(seq.getId() == id) {
				return seq.getStenen().size();
			}
		}
		return -1;
	}
	public void maakBackup() {
		backupSpelbord.clear();
		for (SteenSequentie steenSequentie : spelbord) {
			backupSpelbord.add(steenSequentie);
		}
	}
}