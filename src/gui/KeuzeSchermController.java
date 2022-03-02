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

public class KeuzeSchermController extends StackPane {

	@FXML
	private Label lblSpeler1;

	@FXML
	private Label lblSpeler2;

	@FXML
	private Label lblSpeler3;

	@FXML
	private Label lblSpeler4;

	@FXML
	private Button btnSpeelSpel;

	@FXML
	private Button btnOverzicht;

	@FXML
	private Button btnUitloggen;

	private Taal taal;
	private DomeinController dc;
	private List<String> spelerNamen = new ArrayList<String>();

	public KeuzeSchermController(DomeinController dc) {
		super();
		try {
			this.dc = dc;
			for (Speler speler : dc.geefSpelersInSpel()) {
				spelerNamen.add(speler.getGebruikersnaam());
			}
			taal = Taal.getInstance();
			ResourceBundle mybundle = taal.getResources();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KeuzeScherm.fxml"), mybundle);
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			setSpelerLabels();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	void btnOverzichtClicked(MouseEvent event) {
		OverzichtSchermUC4Controller keuze = new OverzichtSchermUC4Controller(dc);
		Scene scene = new Scene(keuze, 805, 600);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setResizable(true);
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

	private void setSpelerLabels() {
		switch (spelerNamen.size()) {
			case 2 -> {
				setLabelText("", spelerNamen.get(0), spelerNamen.get(1), "");
			}
			case 3 -> {
				setLabelText(spelerNamen.get(0), spelerNamen.get(1), spelerNamen.get(2), "");
			}
			case 4 -> {
				setLabelText(spelerNamen.get(0), spelerNamen.get(1), spelerNamen.get(2), spelerNamen.get(3));
			}
		}
	}

	private void setLabelText(String een, String twee, String drie, String vier) {
		lblSpeler1.setText(een);
		lblSpeler2.setText(twee);
		lblSpeler3.setText(drie);
		lblSpeler4.setText(vier);
	}

}
