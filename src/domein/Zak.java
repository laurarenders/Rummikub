package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domein.Steen.Kleur;

public class Zak {

	private final int MAX_AANTAL_STENEN = 106;
	private List<Steen> stenenInZak = new ArrayList<>(MAX_AANTAL_STENEN);

	public Zak() {
		genereerStenen();
	}

	int teller1 = 0;
	int teller2 = 52;

	private void genereerStenen() {
		for (Kleur kleur : Kleur.values()) {
			if (kleur != Kleur.JOKER) {
				for (int i = Steen.MIN_VALUE; i <= Steen.MAX_VALUE; i++) {
					stenenInZak.add(new Steen(kleur, i, teller1));
					stenenInZak.add(new Steen(kleur, i, teller2));
					teller1++;
					teller2++;
				}
			}
		}
		stenenInZak.add(new Steen(105));
		stenenInZak.add(new Steen(106));
		Collections.shuffle(stenenInZak);
	}

	public Steen neemSteenUitZak() {
		if (!stenenInZak.isEmpty()) {
			return stenenInZak.remove(0);
		}
		return null;
	}

	public int getAantalStenenInZak() {
		return stenenInZak.size();
	}

}
