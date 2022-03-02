package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Speler;
import domein.Steen;
import domein.SteenSequentie;
import exceptions.SteenSequentieException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import taal.Taal;

public class SpeelSpelSchermController extends StackPane {

	// Private hulpconstanten:

	private final int JOKER_VALUE = 25;

	@FXML
	private Label lblSpeler1;

	@FXML
	private Label lblSpeler2;

	@FXML
	private Label lblSpeler3;
	
	@FXML
    private Label lblHuidigeSpeler;

	@FXML
	private FlowPane bordPane = new FlowPane();

	@FXML
	private FlowPane plankjePane;

	@FXML
	private Label steenSequentieNietGeldig;

	@FXML
	private ImageView btnEindeBeurt;

	@FXML
	private ImageView btnPLUS;
	
	@FXML
    private ImageView btnReset;

	private Taal taal;
	private DomeinController dc;
	Optional<Steen> geklikteSteen = null;
	List<Optional<Steen>> stenenVoorNieuweSequentie = new ArrayList<>();
	List<Optional<Steen>> stenenVoorNieuweSequentieCopy = new ArrayList<>();
	private String persoonlijkPath = "C:\\Users\\daems\\Documents\\GitHub\\Rummikub-g07\\src\\imagesBlokjes\\";
	private String persoonlijkPathSpacer = "C:\\Users\\daems\\Documents\\GitHub\\Rummikub-g07\\src\\images\\spacer.png";

	boolean meerdereStenenGeklikt = false;
	int aantalSequenties = -100;

	TextInputDialog tid;

	public SpeelSpelSchermController(DomeinController dc) {
		super();
		try {
			this.dc = dc;
			taal = Taal.getInstance();
			ResourceBundle mybundle = taal.getResources();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SpeelSpelScherm.fxml"), mybundle);
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			setSpelerLabels();
			setImages();

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void setSpelerLabels() {
		for (Speler speler : dc.geefSpelersInSpel()) {

			if (dc.geefSpelerAanZetIndex() == dc.geefSpelersInSpel().indexOf(speler)) {
				setLabelText(
						dc.geefSpelersInSpel().get((dc.geefSpelerAanZetIndex() + 1) % dc.geefSpelersInSpel().size())
								.getGebruikersnaam(),
						dc.geefSpelersInSpel().get((dc.geefSpelerAanZetIndex() + 2) % dc.geefSpelersInSpel().size())
								.getGebruikersnaam(),
						dc.geefSpelersInSpel().get((dc.geefSpelerAanZetIndex() + 3) % dc.geefSpelersInSpel().size())
								.getGebruikersnaam(),
						dc.geefSpelersInSpel().get((dc.geefSpelerAanZetIndex()) % dc.geefSpelersInSpel().size())
								.getGebruikersnaam());
			}
		}
	}

	private void setLabelText(String een, String twee, String drie, String huidige) {
		lblSpeler1.setText(een);
		lblSpeler2.setText(twee);
		lblSpeler3.setText(drie);
		lblHuidigeSpeler.setText(huidige);
		
		switch (dc.geefAantalSpelers()) {
			case 2 -> {
				lblSpeler1.setVisible(true);
			}
			case 3 -> {
				lblSpeler1.setVisible(true);
				lblSpeler2.setVisible(true);
			}
			case 4 -> {
				lblSpeler1.setVisible(true);
				lblSpeler2.setVisible(true);
				lblSpeler3.setVisible(true);
			}
		}
	}

	public void setImages() {
		maakPlankjeLeeg();
		List<Steen> spelerhand = dc.geefSpelerAanZetSpeler().getSpelerHand().getSpelerHand();
		try {
			if (!(spelerhand == null)) {
				for (Steen steen : spelerhand) {
					ImageView bom = maakImageviewVanSteen(steen);
					bom.setId(Integer.toString(steen.getId()));
					bom.setCursor(Cursor.HAND);
					bom.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							Glow glow = new Glow();
							glow.setLevel(0.9);
							bom.setEffect(glow);
							int id = Integer.parseInt(bom.getId());
							Optional<Steen> steen = spelerhand.stream().filter(st -> st.getId() == id).findFirst();

							if (geklikteSteen == null) {
								if (!meerdereStenenGeklikt) {
									geklikteSteen = steen;
									meerdereStenenGeklikt = true;
								}
								stenenVoorNieuweSequentie.add(steen);
							} else {
								geklikteSteen = null;
								stenenVoorNieuweSequentie.add(steen);
							}
						}
					});
					plankjePane.getChildren().add(bom);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}


	// FXML Codes
	// ----------------------------------------------------------------------------------------------------------

	@FXML
	void flowpaneClicked(MouseEvent event) {
		steenSequentieNietGeldig.setVisible(false);
		try {
			if(stenenVoorNieuweSequentie.size() > 0) {
				
				List<Steen> stenenLijst = new ArrayList<>();
				stenenVoorNieuweSequentie.forEach(steen -> stenenLijst.add(steen.get()));
				dc.maakNieuweSteenSequentie(stenenLijst);
				maakSequentieHbox(aantalSequenties -=1, stenenLijst);
				stenenVoorNieuweSequentie = new ArrayList<>();
				meerdereStenenGeklikt = false;
				btnEindeBeurt.setVisible(true);
				btnPLUS.setVisible(false);
				
			}
			valideerSpelbord();
			updateScherm();
			

		} catch (SteenSequentieException e) {
			steenSequentieNietGeldig.setText(taal.geefTekst("steenSequentieNietMogelijk"));
			steenSequentieNietGeldig.setVisible(true);
			stenenVoorNieuweSequentie = new ArrayList<>();
			meerdereStenenGeklikt = false;
		}
	}

	@FXML
	void btnEindeBeurtClicked(MouseEvent event) {
		dc.eindeBeurt();
		maakPlankjeLeeg();
		if (dc.isEIndeSpel()) {
			toonOverzichtScherm();
		}
		steenSequentieNietGeldig.setVisible(false);
		btnEindeBeurt.setVisible(false);
		btnReset.setVisible(false);
		btnPLUS.setVisible(true);
		updateScherm();
	}

	@FXML
	void btnWinClicked(MouseEvent event) {
		dc.maakSpelerhandLeeg();
		setImages();
		if (dc.isEIndeSpel()) {
			toonOverzichtScherm();
		}
	}

	@FXML
	void imgPLUSClicked(MouseEvent event) {
		dc.resetBeurt();
		dc.geefSteenUitZak();
		dc.eindeBeurt();
		steenSequentieNietGeldig.setVisible(false);
		btnReset.setVisible(false);
		updateScherm();
	}
	
	@FXML
	void btnResetClicked(MouseEvent event) {
		dc.resetBeurt();
		steenSequentieNietGeldig.setVisible(false);
		btnReset.setVisible(false);
		updateScherm();
    }

	// Hulpmethodes
	// ------------------------------------------------------------------------------------------------------------------

	private void maakSequentieHbox(int id, List<Steen> stenenLijst) {
		HBox ss = new HBox();
		ss.setId(Integer.toString(id));
		ss.setCursor(Cursor.HAND);
		ss.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int seqid = Integer.parseInt(ss.getId());
				int sequentieIndex = Integer.parseInt(maakPopupIndex());
				int lengteSequentie = dc.geefLengteSequentieOpId(seqid);
				Steen gekliktesteentje = geklikteSteen.get();

				if ((sequentieIndex == 0 || sequentieIndex == lengteSequentie) && gekliktesteentje != null) {
					dc.voegSteenToeAanSequentie(seqid, sequentieIndex, gekliktesteentje);
				} else {
					dc.splitsRijen(seqid, sequentieIndex, gekliktesteentje);
				}
				setImages();
			}
			
		});
		for (Steen steen : stenenLijst) {
			dc.removeStenenUitSpelerHand(steen.getId());
			ImageView img = maakImageviewVanSteen(steen);
			ss.getChildren().add(img);
		}
		File filer = new File(persoonlijkPathSpacer);
		Image image = new Image(filer.toURI().toString());
		ImageView img = new ImageView(image);
		ss.getChildren().add(img);
		bordPane.getChildren().add(ss);
	}

	private void valideerSpelbord() {
		if(!(dc.valideerSpelbord())) {
			btnEindeBeurt.setVisible(false);
			btnPLUS.setVisible(true);
			steenSequentieNietGeldig.setText(taal.geefTekst("steenSequentieNietMogelijk"));
			steenSequentieNietGeldig.setVisible(true);
		}
		else {
			steenSequentieNietGeldig.setVisible(false);
		}
		btnReset.setVisible(true);
	}
	
	private String maakPopupIndex() {
		tid = new TextInputDialog("Plaats steen");
		tid.setHeaderText("Enter index");
		Optional<String> result = tid.showAndWait();
		String returnedName = "";
		if (result.isPresent()) {
			returnedName = tid.getEditor().getText();
		}
		return returnedName;
	}

	private ImageView maakImageviewVanSteen(Steen steen) {
		String pad;
		ImageView bom;
		if (steen.getGetal() == JOKER_VALUE) {
			pad = String.format("%sJOKER.jpg", persoonlijkPath);
		} else {
			pad = String.format("%s%s%02d.jpg", persoonlijkPath, steen.getKleur(), steen.getGetal());
		}
		File file = new File(pad);
		Image image = new Image(file.toURI().toString());
		return bom = new ImageView(image);
	}

	private void maakPlankjeLeeg() {
		plankjePane.getChildren().clear();
	}
	
	private void toonOverzichtScherm() {
		OverzichtSchermUC3Controller keuze = new OverzichtSchermUC3Controller(dc);
		Scene scene = new Scene(keuze, 805, 600);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setResizable(true);
	}
	
	private void updateScherm() {
		bordPane.getChildren().clear();
		for (SteenSequentie steenSequentie : dc.geefSpelbord()) {
			maakSequentieHbox(steenSequentie.getId(), (steenSequentie).getStenen());
		}
		setSpelerLabels();
		setImages();
	}

}