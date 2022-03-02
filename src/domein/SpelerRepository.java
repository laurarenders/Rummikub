package domein;

import exceptions.SpelerException;
import persistentie.SpelerMapper;


public class SpelerRepository {
	// Attributen
	private final SpelerMapper mp;

	// Constructor
	public SpelerRepository() {
		this.mp = new SpelerMapper();
	}

	// Methodes
	
	/**
	 * @author EllyY
	 *Als speler s leeg is of wachtwoord of gebruikersnaam is niet correct
	 * gooit het een exception.
	 * Als het niet leeg is wordt wachtwoord gereset omdat de applicatie wachtwoord niet 
	 * moet bijhouden.
	 * @param gebruikersnaam voor speler gebruikersnaam
	 * @param wachtwoord voor speler wachtwoord
	 * @return s (speler)
	 * @throws SpelerException
	 */
	public Speler geefSpeler(String gebruikersnaam,  String wachtwoord) throws SpelerException {
		Speler s = mp.geefSpeler(gebruikersnaam); 
		
		if (s == null) {  
			throw new SpelerException();
		}
		if (!s.getWachtwoord().equals(wachtwoord)) { 
			throw new SpelerException();
		} 
		s.resetWachtwoord();
		return s;
		
	}
}