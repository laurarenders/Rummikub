package domein;

/**
 * @author EllyY
 *
 */

public class Steen {

	public enum Kleur {
		BLAUW, GEEL, ROOD, ZWART, JOKER
	}

	public static final int JOKER_VALUE = 25;
	public static final int MAX_VALUE = 13;
	public static final int MIN_VALUE = 1;

	private final Kleur kleur;
	private int getal;
	private int id;

	/**
	 * Aanmaak steen, als de kleur de joker is, wordt het getal waarde 25.
	 *
	 */
	public Steen(Kleur kleur, int getal, int id) {
		setId(id);
		this.kleur = kleur;
		if (kleur.equals(Kleur.JOKER)) {
			this.getal = JOKER_VALUE;
		} else {
			setGetal(getal);
		}
	}

	public Steen(Kleur kleur, int id) {
		setId(id);
		this.kleur = kleur;
	}

	public Steen(int id) {
		kleur = Kleur.JOKER;
		getal = JOKER_VALUE;
		setId(id);
	}

	public Kleur getKleur() {
		return kleur;
	}

	public int getGetal() {
		return getal;
	}

	public void setGetal(int getal) {
		this.getal = getal;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
