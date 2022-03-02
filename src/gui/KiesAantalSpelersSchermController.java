package gui;

import java.io.IOException;
import java.util.ResourceBundle;

import domein.DomeinController;
import exceptions.AantalSpelersException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import taal.Taal;

public class KiesAantalSpelersSchermController extends StackPane {

	@FXML
	private ImageView img2;

	@FXML
	private ImageView img3;

	@FXML
	private ImageView img4;

	private final DomeinController dc = new DomeinController();
	private Taal taal;

	public KiesAantalSpelersSchermController(Taal taal) {
		super();
		this.taal = taal;
		try {
			taal = Taal.getInstance();
			ResourceBundle mybundle = taal.getResources();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KiesAantalSpelersScherm.fxml"), mybundle);
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void naarVolgendScherm(int aantal) {
		LogInSchermController logIn = new LogInSchermController(dc, aantal);
		Scene scene = new Scene(logIn, 805, 600);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setResizable(true);
	}

	@FXML
	void img2Clicked(MouseEvent event) {
		try {
			dc.setSpelerAantal(2);
			naarVolgendScherm(2);
		} catch (AantalSpelersException e) { // Aantal spelers kan nooit fout zijn omdat je maar op 3 knoppen kan
												// klikken die juist zijn
			e.printStackTrace();
		}
	}

	@FXML
	void img3Clicked(MouseEvent event) {
		try {
			dc.setSpelerAantal(3);
			naarVolgendScherm(3);
		} catch (AantalSpelersException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void img4Clicked(MouseEvent event) {
		try {
			dc.setSpelerAantal(4);
			naarVolgendScherm(4);
		} catch (AantalSpelersException e) {
			e.printStackTrace();
		}
	}

}
