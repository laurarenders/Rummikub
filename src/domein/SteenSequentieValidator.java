package domein;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import domein.Steen.Kleur;

public class SteenSequentieValidator {

	private List<Steen> steenLijst;
	private Kleur sequentieKleur;
	private int jokerTeller;
	private int huidigeWaarde;

	public SteenSequentieValidator(List<Steen> steenLijst) {
		this.steenLijst = steenLijst;
	}

	public boolean isSequentieGeldig() {
		if (steenLijst.size() < 3) {
			return false;
		}
		return valideerZelfdeWaarde() || valideerStijgendeWaarde() || valideerDalendeWaarde();
	}

	private boolean isSteenJoker(Steen steen) {
		return steen.getKleur() == Kleur.JOKER;
	}

	private boolean isSteenInVolgorde(Steen steen) {
		if (isSteenJoker(steen)) {
			jokerTeller++;
			if (aantalJokersOverschreden() || sequentieReedsOpEinde()) {
				return false;
			}
			huidigeWaarde++;
			return true;
		}
		if (!isNietJokerSteenInVolgorde(steen)) {
			return false;
		}
		return true;
	}

	private boolean aantalJokersOverschreden() {
		return jokerTeller > 2;
	}

	private boolean sequentieReedsOpEinde() {
		return huidigeWaarde == 13;
	}

	
	/**
	 * @author EllyY
	 *Als steen eerste is in sequentie dan huidige waarde en kleur van de steen in sequentie
	 *initialiseren.
	 *Als eerste steen joker is, mag dit niet. Voorbeeld: "J, 1, 2"
	 *@param steen Dit is een steen met een kleur en een getal
	 */
	private boolean isNietJokerSteenInVolgorde(Steen steen) {

		if (eersteSteen()) {
			setHuidigeWaardeEnSequentieKleur(steen);
			return true;

		}
		if (jokerVoorDeStartVanSequentie() || !dezelfdeKleur(steen) || !inVolgorde(steen)) {
			return false;
		}
		huidigeWaarde++;
		return true;
	}

	private boolean dezelfdeKleur(Steen steen) {
		return steen.getKleur() == sequentieKleur;
	}

	private boolean inVolgorde(Steen steen) {
		return (steen.getGetal() == (huidigeWaarde + 1));
	}

	private boolean jokerVoorDeStartVanSequentie() {
		return huidigeWaarde == 1 && jokerTeller > 0;
	}

	private boolean eersteSteen() {
		return huidigeWaarde == -1;
	}

	private void setHuidigeWaardeEnSequentieKleur(Steen steen) {
		huidigeWaarde = steen.getGetal();
		sequentieKleur = steen.getKleur();
	}

	private boolean isSteenZelfdeWaardeEnNietGebruikteKleur(List<Kleur> nietGebruikteKleur, Steen steen) {
		return steen.getGetal() == huidigeWaarde && nietGebruikteKleur.contains(steen.getKleur());
	}

	private boolean valideerZelfdeWaarde() {
		setEersteSteen();
		List<Kleur> nietGebruikteKleuren = Arrays.stream(Kleur.values()).collect(Collectors.toList());

		nietGebruikteKleuren.remove(Kleur.JOKER);
		setJokerTellerOpNul();

		for (Steen steen : steenLijst) {
			if (isSteenJoker(steen)) {
				jokerTeller++;
				if (aantalJokersOverschreden()) {
					return false;
				}
				;

			} else if (eersteSteen()) {
				setHuidigeWaardeEnSequentieKleur(steen);
				nietGebruikteKleuren.remove(steen.getKleur());
			} else if (!isSteenZelfdeWaardeEnNietGebruikteKleur(nietGebruikteKleuren, steen)) {
				return false;
			} else {
				nietGebruikteKleuren.remove(steen.getKleur());
			}
		}

		return nietGebruikteKleuren.size() >= jokerTeller;
	}

	private void setEersteSteen() {
		huidigeWaarde = -1;
	}

	private void setJokerTellerOpNul() {
		jokerTeller = 0;
	}

	/**
	 * @author EllyY
	 *Methode die samen met valideerStijgendeWaarde gebruikt wordt om te kijken of 
	 *er ofwel van
	 *links naar rechts of van rechts naar links een oplopende volgorde is
	 *bv 1,2,3 is valideerStijgendeWaarde of 3,2,1 is valideerDalendeWaarde
	 *@return boolean of het stijgende waarde is
	 */
	private boolean valideerStijgendeWaarde() {
		setEersteSteen();
		setJokerTellerOpNul();

		for (Steen steen : steenLijst) {
			if (!isSteenInVolgorde(steen)) {
				return false;
			}
		}
		return true;
	}

	private boolean valideerDalendeWaarde() {
		setEersteSteen();
		setJokerTellerOpNul();

		ListIterator<Steen> iterator = steenLijst.listIterator(steenLijst.size());
		while (iterator.hasPrevious()) {
			Steen steentje = iterator.previous();
			if (!isSteenInVolgorde(steentje)) {
				return false;
			}
		}
		return true;
	}

}
