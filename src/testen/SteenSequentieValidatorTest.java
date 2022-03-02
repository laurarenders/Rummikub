package testen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domein.Steen;
import domein.Steen.Kleur;
import domein.SteenSequentieValidator;

public class SteenSequentieValidatorTest {

	private static Stream<Arguments> correcteSteenSequenties() {
		// Stenen van zelfde waarde en verschillende kleuren
		List<Steen> steenLijst0 = new ArrayList<>();
		steenLijst0.add(new Steen(Kleur.BLAUW, 1));
		steenLijst0.add(new Steen(Kleur.GEEL, 1));
		steenLijst0.add(new Steen(Kleur.ROOD, 1));
		List<Steen> steenLijst1 = new ArrayList<>();
		steenLijst1.add(new Steen(Kleur.BLAUW, 1));
		steenLijst1.add(new Steen(Kleur.GEEL, 1));
		steenLijst1.add(new Steen(Kleur.ROOD, 1));
		steenLijst1.add(new Steen(Kleur.ZWART, 1));
		// 3 zelfde values + 1 joker
		List<Steen> steenLijst2 = new ArrayList<>();
		steenLijst2.add(new Steen(Kleur.BLAUW, 1));
		steenLijst2.add(new Steen(Kleur.JOKER, 105));
		steenLijst2.add(new Steen(Kleur.ROOD, 1));
		steenLijst2.add(new Steen(Kleur.ZWART, 1));
		// 2zelfde values+ joker
		List<Steen> steenLijst3 = new ArrayList<>();
		steenLijst3.add(new Steen(Kleur.ZWART, 1));
		steenLijst3.add(new Steen(Kleur.JOKER, 105));
		steenLijst3.add(new Steen(Kleur.ROOD, 1));

		List<Steen> steenLijst4 = new ArrayList<>();
		steenLijst4.add(new Steen(Kleur.BLAUW, 1));
		steenLijst4.add(new Steen(Kleur.BLAUW, 2));
		steenLijst4.add(new Steen(Kleur.BLAUW, 3));
		steenLijst4.add(new Steen(Kleur.BLAUW, 4));
		steenLijst4.add(new Steen(Kleur.BLAUW, 5));
		steenLijst4.add(new Steen(Kleur.BLAUW, 6));

		List<Steen> steenLijst5 = new ArrayList<>();
		steenLijst5.add(new Steen(Kleur.BLAUW, 1));
		steenLijst5.add(new Steen(Kleur.BLAUW, 2));
		steenLijst5.add(new Steen(Kleur.BLAUW, 3));
		steenLijst5.add(new Steen(Kleur.BLAUW, 4));
		steenLijst5.add(new Steen(Kleur.BLAUW, 5));
		steenLijst5.add(new Steen(Kleur.BLAUW, 6));
		steenLijst5.add(new Steen(Kleur.BLAUW, 7));
		steenLijst5.add(new Steen(Kleur.BLAUW, 8));
		steenLijst5.add(new Steen(Kleur.BLAUW, 9));
		steenLijst5.add(new Steen(Kleur.BLAUW, 10));
		steenLijst5.add(new Steen(Kleur.BLAUW, 11));
		steenLijst5.add(new Steen(Kleur.BLAUW, 12));
		steenLijst5.add(new Steen(Kleur.BLAUW, 13));

		List<Steen> steenLijst6 = new ArrayList<>();
		steenLijst6.add(new Steen(Kleur.BLAUW, 1));
		steenLijst6.add(new Steen(Kleur.BLAUW, 2));
		steenLijst6.add(new Steen(Kleur.BLAUW, 3));
		steenLijst6.add(new Steen(Kleur.JOKER, 105));
		steenLijst6.add(new Steen(Kleur.BLAUW, 5));
		steenLijst6.add(new Steen(Kleur.BLAUW, 6));
		steenLijst6.add(new Steen(Kleur.BLAUW, 7));
		steenLijst6.add(new Steen(Kleur.BLAUW, 8));
		steenLijst6.add(new Steen(Kleur.BLAUW, 9));
		steenLijst6.add(new Steen(Kleur.BLAUW, 10));
		steenLijst6.add(new Steen(Kleur.JOKER, 105));
		steenLijst6.add(new Steen(Kleur.BLAUW, 12));
		steenLijst6.add(new Steen(Kleur.BLAUW, 13));

		return Stream.of(
				Arguments.of(steenLijst0), 
				Arguments.of(steenLijst1),
				Arguments.of(steenLijst2), 
				Arguments.of(steenLijst3), 
				Arguments.of(steenLijst4),
				Arguments.of(steenLijst5), 
				Arguments.of(steenLijst6)
			);
	}

	private static Stream<Arguments> foutieveSteenSequenties() {
		// Stenen van zelfde waarde en dezelfde kleuren
		List<Steen> steenLijst0 = new ArrayList<>();
		steenLijst0.add(new Steen(Kleur.BLAUW, 1));
		steenLijst0.add(new Steen(Kleur.BLAUW, 1));
		steenLijst0.add(new Steen(Kleur.BLAUW, 1));
		// joker voor kleinste waarde(1)
		List<Steen> steenLijst1 = new ArrayList<>();
		steenLijst1.add(new Steen(Kleur.JOKER, 105));
		steenLijst1.add(new Steen(Kleur.GEEL, 1));
		steenLijst1.add(new Steen(Kleur.GEEL, 2));
		steenLijst1.add(new Steen(Kleur.GEEL, 3));
		// joker na de 13(hoogste waarde)
		List<Steen> steenLijst2 = new ArrayList<>();
		steenLijst2.add(new Steen(Kleur.BLAUW, 9));
		steenLijst2.add(new Steen(Kleur.BLAUW, 10));
		steenLijst2.add(new Steen(Kleur.BLAUW, 11));
		steenLijst2.add(new Steen(Kleur.BLAUW, 12));
		steenLijst2.add(new Steen(Kleur.BLAUW, 13));
		steenLijst2.add(new Steen(Kleur.JOKER, 105));
		// 2 dezelfde kleuren bij zelfde waardes
		List<Steen> steenLijst3 = new ArrayList<>();
		steenLijst3.add(new Steen(Kleur.BLAUW, 1));
		steenLijst3.add(new Steen(Kleur.GEEL, 1));
		steenLijst3.add(new Steen(Kleur.ROOD, 1));
		steenLijst3.add(new Steen(Kleur.ZWART, 2));
		// teveel Jokers
		List<Steen> steenLijst6 = new ArrayList<>();
		steenLijst6.add(new Steen(Kleur.BLAUW, 1));
		steenLijst6.add(new Steen(Kleur.BLAUW, 2));
		steenLijst6.add(new Steen(Kleur.BLAUW, 3));
		steenLijst6.add(new Steen(Kleur.JOKER, 105));
		steenLijst6.add(new Steen(Kleur.BLAUW, 5));
		steenLijst6.add(new Steen(Kleur.BLAUW, 6));
		steenLijst6.add(new Steen(Kleur.BLAUW, 7));
		steenLijst6.add(new Steen(Kleur.BLAUW, 8));
		steenLijst6.add(new Steen(Kleur.JOKER, 105));
		steenLijst6.add(new Steen(Kleur.BLAUW, 10));
		steenLijst6.add(new Steen(Kleur.JOKER, 105));
		steenLijst6.add(new Steen(Kleur.BLAUW, 12));
		steenLijst6.add(new Steen(Kleur.BLAUW, 13));

		return Stream.of(
				Arguments.of(steenLijst0), 
				Arguments.of(steenLijst1),
				Arguments.of(steenLijst2), 
				Arguments.of(steenLijst3), 
				Arguments.of(steenLijst6)
			);
	}

	@ParameterizedTest
	@MethodSource("correcteSteenSequenties")
	public void correcteSteenSequentie_returnsTrue(List<Steen> steenLijst) {
		SteenSequentieValidator ssv = new SteenSequentieValidator(steenLijst);
		Assertions.assertEquals(true, ssv.isSequentieGeldig());
	}

	@ParameterizedTest
	@MethodSource("foutieveSteenSequenties")
	public void foutieveSteenSequentie_returnsFalse(List<Steen> steenLijst) {
		SteenSequentieValidator ssv = new SteenSequentieValidator(steenLijst);
		Assertions.assertEquals(false, ssv.isSequentieGeldig());
	}
}
