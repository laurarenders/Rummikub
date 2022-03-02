
package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Speler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import taal.Taal;

public class OverzichtSchermUC4Controller extends StackPane {

	@FXML
	private Label lblSpeler1;

	@FXML
	private Label lblSpeler2;

	@FXML
	private Label lblSpeler3;

	@FXML
	private Label lblSpeler4;

	@FXML
	private Label lblSpeler1Som;

	@FXML
	private Label lblSpeler2Som;

	@FXML
	private Label lblSpeler3Som;

	@FXML
	private Label lblSpeler4Som;

	@FXML
	private Button btnMenuSpel;

	private Taal taal;
	private DomeinController dc;

	public OverzichtSchermUC4Controller(DomeinController dc) {
		super();
		try {
			this.dc = dc;
			taal = Taal.getInstance();
			ResourceBundle mybundle = taal.getResources();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtSchermUC4.fxml"), mybundle);
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
		List<Integer> lblTotaalScoreList = new ArrayList<Integer>();
		int teller = 0;
		try {
				
			for (Speler speler : dc.geefSpelersInSpel()) {
				int totaalscore = 0;
				String lblTekst = String.format("%s " + "scores: ", speler.getGebruikersnaam());
				for (int spelScore : dc.geefAlleScoreS().get(teller)) {
					lblTekst += (spelScore + ", ");
					totaalscore += spelScore;
				}
				teller++;
				lblTekstList.add(lblTekst);
				lblTotaalScoreList.add(totaalscore);
			}

			switch (dc.geefAantalSpelers()) {
				case 2 -> {
					lblSpeler1.setText(lblTekstList.get(0));
					lblSpeler1.setVisible(true);
					lblSpeler2.setText(lblTekstList.get(1));
					lblSpeler2.setVisible(true);
					lblSpeler1Som.setText(lblTotaalScoreList.get(0).toString());
					lblSpeler1Som.setVisible(true);
					lblSpeler2Som.setText(lblTotaalScoreList.get(1).toString());
					lblSpeler2Som.setVisible(true);
				}
				case 3 -> {
					lblSpeler1.setText(lblTekstList.get(0));
					lblSpeler1.setVisible(true);
					lblSpeler2.setText(lblTekstList.get(1));
					lblSpeler2.setVisible(true);
					lblSpeler3.setText(lblTekstList.get(2));
					lblSpeler3.setVisible(true);
					lblSpeler1Som.setText(lblTotaalScoreList.get(0).toString());
					lblSpeler1Som.setVisible(true);
					lblSpeler2Som.setText(lblTotaalScoreList.get(1).toString());
					lblSpeler2Som.setVisible(true);
					lblSpeler3Som.setText(lblTotaalScoreList.get(2).toString());
					lblSpeler3Som.setVisible(true);
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
					lblSpeler1Som.setText(lblTotaalScoreList.get(0).toString());
					lblSpeler1Som.setVisible(true);
					lblSpeler2Som.setText(lblTotaalScoreList.get(1).toString());
					lblSpeler2Som.setVisible(true);
					lblSpeler3Som.setText(lblTotaalScoreList.get(2).toString());
					lblSpeler3Som.setVisible(true);
					lblSpeler4Som.setText(lblTotaalScoreList.get(3).toString());
					lblSpeler4Som.setVisible(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
