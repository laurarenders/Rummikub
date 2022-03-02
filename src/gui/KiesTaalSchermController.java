package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import taal.Taal;

public class KiesTaalSchermController extends StackPane {

    @FXML
    private ImageView imgNL;

    @FXML
    private ImageView imgEngels;

    @FXML
    private ImageView imgFrans;

    private Taal taal = Taal.getInstance();
    
    public KiesTaalSchermController() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KiesTaalScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch(IOException ex) {
			System.out.print(ex.getMessage());
		}	
	}
    
    private void setLogInSchermController() {
    	KiesAantalSpelersSchermController aantalSpelers = new KiesAantalSpelersSchermController(taal);
		Scene scene = new Scene(aantalSpelers,805,600);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setResizable(true);
    }
    
    @FXML
    void imgENGClicked(MouseEvent event) {
    	taal.taalInstellen("en_US");
    	setLogInSchermController();
    }

    @FXML
    void imgFRClicked(MouseEvent event) {
    	taal.taalInstellen("fr_FR");
    	setLogInSchermController();
    }

    @FXML
    void imgNLClicked(MouseEvent event) {
    	taal.taalInstellen("nl_BE");
    	setLogInSchermController();
    }

}

