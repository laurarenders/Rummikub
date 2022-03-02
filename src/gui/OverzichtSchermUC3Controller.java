package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Speler;
import exceptions.SpelerException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import taal.Taal;

public class OverzichtSchermUC3Controller extends StackPane {

	@FXML
	private Label lblSpeler1;

	@FXML
	private Label lblSpeler2;

	@FXML
	private Label lblSpeler3;

	@FXML
	private Label lblSpeler4;

	@FXML
	private Button btnMenu;

	private Taal taal;
	private DomeinController dc;

	public OverzichtSchermUC3Controller(DomeinController dc) {
		super();
		try {
			this.dc = dc;
			taal = Taal.getInstance();
			ResourceBundle mybundle = taal.getResources();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtSchermUC3.fxml"), mybundle);
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			setlabelScores();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void setlabelScores() {
		List<String> lblTekstList = new ArrayList<String>();
		int teller = 0;
		try {
			for (Speler speler : dc.geefSpelersInSpel()) {
				String lblTekst = String.format("%s " + "score: %d ", speler.getGebruikersnaam(), dc.geefScoreSpel().get(teller));
					lblTekstList.add(lblTekst);
					teller++;
			}
			switch (dc.geefAantalSpelers()) {
			case 2 -> {
				lblSpeler1.setText(lblTekstList.get(0));
				lblSpeler1.setVisible(true);
				lblSpeler2.setText(lblTekstList.get(1));
				lblSpeler2.setVisible(true);
			}
			case 3 -> {
				lblSpeler1.setText(lblTekstList.get(0));
				lblSpeler1.setVisible(true);
				lblSpeler2.setText(lblTekstList.get(1));
				lblSpeler2.setVisible(true);
				lblSpeler3.setText(lblTekstList.get(2));
				lblSpeler3.setVisible(true);
			}
			case 4 -> {
				lblSpeler1.setText(lblTekstList.get(0));
				lblSpeler1.setVisible(true);
				lblSpeler2.setText(lblTekstList.get(1));
				lblSpeler2.setVisible(true);
				lblSpeler3.setText(lblTekstList.get(2));
				lblSpeler3.setVisible(true);
				lblSpeler4.setText(lblTekstList.get(3));
				lblSpeler4.setVisible(true);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnSpeelSpelClicked(MouseEvent event) throws SpelerException {
		dc.startSpel();
		SpeelSpelSchermController skeuze = new SpeelSpelSchermController(dc);
		final Scene scene = new Scene(skeuze);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setResizable(true);
	}

	@FXML
	void btnMenuClicked(MouseEvent event) {
		KeuzeSchermController skeuze = new KeuzeSchermController(dc);
		Scene scene = new Scene(skeuze, 805, 600);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setResizable(true);
	}

}
