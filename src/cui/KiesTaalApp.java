package cui;

import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.SpelerAantalBereikt;
import exceptions.TaalException;
import taal.Taal;

public class KiesTaalApp {

	private DomeinController dc = new DomeinController();
	private Taal taal = Taal.getInstance();

	public KiesTaalApp(DomeinController dc) {
		this.dc = dc;
	}

	public void start() throws SpelerAantalBereikt {
		Scanner input = new Scanner(System.in);
		boolean hulp = true;

		while (hulp) {
			try {
				System.out.println("Choose your preferred language - Kies jouw taal - Choisis votre language\n"
						+ "1. English\n" + "2. Nederlands\n" + "3. Fran�ais");
				int keuze = input.nextInt();
				String keuzeTaal = "";
				if (keuze < 1 || keuze > 3) {
					throw new TaalException();
				} else {
					switch (keuze) {
					case 1 -> keuzeTaal = "en_US";
					case 2 -> keuzeTaal = "nl_BE";
					case 3 -> keuzeTaal = "fr_FR";
					}
				}
				taal.taalInstellen(keuzeTaal);
				hulp = false;
			} catch (InputMismatchException ime) {
				input.nextLine();
				System.out.println(
						"You have to enter an integer! - U moet een getal invoeren! - Vous devez enregistrer un chiffre");
			} catch (TaalException te) {
				System.out.println(
						"Your only options are 1, 2 or 3 - Uw enige opties zijn 1, 2 of 3 - Vos seules options sont 1, 2 ou 3");
			}
		}

		AanmeldenApp aa = new AanmeldenApp(dc);
		aa.startApp();

	}

}
