package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Speler;

public class SpelerMapper {
	
	private static final String GEEFGEBRUIKER = "SELECT * FROM ID343683_g7.Speler WHERE gebruikersnaam =BINARY ?";
	
	public Speler geefSpeler(String gebruikersnaam) {
		Speler speler = null;
		
		try(Connection dbC = DriverManager.getConnection(Connectie.JBDC_URL)) {
			PreparedStatement query = dbC.prepareStatement(GEEFGEBRUIKER);
			query.setString(1, gebruikersnaam); //Toont speler met deze gebruikersnaam
			
			ResultSet result = query.executeQuery();
			if(result.next()) {
				String wachtwoord = result.getString("wachtwoord");
				gebruikersnaam = result.getString("gebruikersnaam");
				speler = new Speler(gebruikersnaam, wachtwoord);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return speler;
	}
}
