package gui;

import java.io.IOException;
import java.util.ResourceBundle;

import domein.DomeinController;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerAantalBereikt;
import exceptions.SpelerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import taal.Taal;

public class LogInSchermController extends StackPane {

	@FXML
	private TextField txfUserName;

	@FXML
	private PasswordField pwdPasswoord;

	@FXML
	private Button btnLogIn;

	@FXML
	private Label lblMessage;

	private final DomeinController dc;
	private Taal taal;
	String huidigeSpelerNaam = "";
	int aantalSpelers;

	public LogInSchermController(DomeinController dc, int aantalSpelers) {
		super();
		this.dc = dc;
		this.aantalSpelers = aantalSpelers;
		try {
			taal = Taal.getInstance();
			ResourceBundle mybundle = taal.getResources();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInScherm.fxml"), mybundle);
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void btnLoginAfhandeling(ActionEvent event) {
		try {
			huidigeSpelerNaam = txfUserName.getText();
			dc.aanmeldenSpeler(huidigeSpelerNaam, pwdPasswoord.getText());
			lblMessage.setText(huidigeSpelerNaam + " " + taal.geefTekst("succesvolAangemeld"));
			lblMessage.setTextFill(Color.web("#00FF00"));
			setLblLeeg();
		} catch (SpelerException ex) {
			setTekstErrorInRood("gegevensNietCorrect");
			setLblLeeg();
		} catch (ReedsAangemeldException e) {
			setTekstErrorInRood("spelerAlAangemeld");
			setLblLeeg();
		} catch (SpelerAantalBereikt e) {
			setTekstErrorInRood("spelersAantalBereikt");
			setLblLeeg();
		}
	}

	@FXML
	void btnDoorgaanClicked(MouseEvent event) {
		if (dc.geefAantalAangemeldeSpelers() != aantalSpelers) {
			setTekstErrorInRood("nietAlleSpelersAangemeld");
			setLblLeeg();
		} else {
			KeuzeSchermController keuze = new KeuzeSchermController(dc);
			Scene scene = new Scene(keuze, 805, 600);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.setResizable(true);
		}
	}

	private void setTekstErrorInRood(String geefTekst) {
		lblMessage.setText(taal.geefTekst(geefTekst));
		lblMessage.setTextFill(Color.web("red"));
	}

	private void setLblLeeg() {
		txfUserName.setText("");
		pwdPasswoord.setText("");
	}
}
