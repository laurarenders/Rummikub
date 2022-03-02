package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SteenSequentie {

	private List<Steen> stenen;
	private List<Steen> stenenBackup = null;
	private int id;

	public SteenSequentie(int id, Steen... stenen) {
		List<Steen> stenenrij = new ArrayList<>();
		stenenrij.addAll(Arrays.asList(stenen));
		this.stenen = stenenrij;
		maakStenenbackup();
		this.id = id;
	}

	public SteenSequentie(int id, List<Steen> stenen) {
		this.stenen = stenen;
		maakStenenbackup();
		this.id = id;
	}

	public boolean isValid() {
		SteenSequentieValidator validator = new SteenSequentieValidator(stenen);
		if (validator.isSequentieGeldig()) {
			return true;
		}
		stenen = stenenBackup;
		return false;
	}

	public int getId() {
		return id;
	}

	public boolean voegSteenToe(int index, Steen steen) {
		if (steen != null && (index <= stenen.size() && index >= 0)) {
			stenen.add(index, steen);
			return true;
		}
		return false;
	}

	public Steen verwijderSteen(int index) {
		Steen steen = null;
		if (index < stenen.size() && index >= 0) {
			steen = stenen.get(index);
		}
		return steen;
	}

	public SteenSequentie splitsSequentie(int index) {
		SteenSequentie nieuweSequentie = null;
		if (index < stenen.size() && index >= 1) {
			nieuweSequentie = new SteenSequentie(this.id + 100, new ArrayList<>(stenen.subList(index, stenen.size())));
			stenen = new ArrayList<>(stenen.subList(0, index));
		}
		return nieuweSequentie;
	}

	public int geefAantalStenenInSequentie() {
		return stenen.size();
	}

	private void maakStenenbackup() {
		this.stenenBackup = stenen;
	}

	public List<Steen> getStenen() {
		return this.stenen;
	}
}