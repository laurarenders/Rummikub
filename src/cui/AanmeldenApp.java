package cui;

import java.text.MessageFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.AantalSpelersException;
import exceptions.KeuzeException;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerAantalBereikt;
import exceptions.SpelerException;
import taal.Taal;

public class AanmeldenApp {

	private DomeinController dc = new DomeinController();
	private Taal taal = Taal.getInstance();

	public AanmeldenApp(DomeinController dc) {
		this.dc = dc;
	}

	private void biedtKeuzemogelijkhedenAan() {
		Scanner input = new Scanner(System.in);
		boolean herhaalLoop = true;
		MessageFormat messageForm = new MessageFormat("");
		Object[] messageArguments = { 1, 2 };

		while (herhaalLoop) {
			try {
				System.out.printf("%s", taal.geefTekst("vraagNaarKeuze"));
				int keuze = input.nextInt();
				herhaalLoop = false;
				if (keuze < 1 || keuze > 2) {
					throw new KeuzeException();
				} else {
					switch (keuze) {
					case 1 -> {
						SpeelSpelApp ssa = new SpeelSpelApp(dc);
						ssa.spelMainLoop();
					}
					case 2 -> {
						OverzichtApp oa = new OverzichtApp(dc);
						oa.toonOverzicht();
					}
					}
				}
			} catch (KeuzeException ke) {
				messageForm.applyPattern(taal.geefTekst("vereisteCijferKeuze"));
				System.out.printf(messageForm.format(messageArguments));
			} catch (InputMismatchException e) {
				input.nextLine();
				messageForm.applyPattern(taal.geefTekst("vereisteCijferKeuze"));
				System.out.printf(messageForm.format(messageArguments));
			}
		}
	}

	private void geefAantalSpelers() {
		final Scanner input = new Scanner(System.in);
		int aantalSpelers;
		boolean herhaalLoop = true;
		MessageFormat messageForm = new MessageFormat("");
		Object[] messageArguments = { 2, 3, 4 };

		while (herhaalLoop) {
			try {
				System.out.printf("%s%n", taal.geefTekst("vraagHoeveelGebruikers"));
				aantalSpelers = input.nextInt();
				dc.setSpelerAantal(aantalSpelers);
				herhaalLoop = false;
			} catch (AantalSpelersException e) {
				messageForm.applyPattern(taal.geefTekst("vereisteSpelersAantal"));
				System.out.println(messageForm.format(messageArguments));
			} catch (InputMismatchException ie) {
				input.nextLine();
				messageForm.applyPattern(taal.geefTekst("vereisteSpelersAantal"));
				System.out.println(messageForm.format(messageArguments));
			}
		}
	}

	private void meldSpelersAan() throws SpelerAantalBereikt {
		Scanner input = new Scanner(System.in);
		String gebruikersnaam;
		String wachtwoord;

		for (int i = 0; i < dc.geefAantalSpelers(); i++) {
			boolean herhaalLoop = true;
			while (herhaalLoop) {
				try {
					System.out.printf("%s", taal.geefTekst("vraagNaarGebruikersnaam"));
					gebruikersnaam = input.next();
					System.out.printf("%s", taal.geefTekst("vraagNaarWachtwoord"));
					wachtwoord = input.next();
					dc.aanmeldenSpeler(gebruikersnaam, wachtwoord);
					System.out.printf("%s %s%n", gebruikersnaam, taal.geefTekst("aangemeld"));
					herhaalLoop = false;
				} catch (SpelerException e) {
					input.nextLine();
					System.out.printf("%s%n", taal.geefTekst("gegevensNietCorrect"));
				} catch (ReedsAangemeldException e) {
					System.out.printf("%s%n", taal.geefTekst("spelerAlAangemeld"));
				}
			}
		}
	}

	public void startApp() throws SpelerAantalBereikt {
		geefAantalSpelers();
		meldSpelersAan();
		biedtKeuzemogelijkhedenAan();

	}
}