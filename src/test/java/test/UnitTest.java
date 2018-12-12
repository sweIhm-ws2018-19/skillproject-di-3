package test;

import static org.junit.Assert.*;

import org.junit.Test;

import verkocht.model.Unit;

public class UnitTest {

	@Test
	public void testNames() {
		assertEquals("Brise", Unit.BRISE.getUnit());
		assertEquals("Essloeffel", Unit.ESSLOEFFEL.getUnit());
		assertEquals("Gramm", Unit.GRAMM.getUnit());
		assertEquals("Milliliter", Unit.MILLILITER.getUnit());
		assertEquals("Schuss", Unit.SCHUSS.getUnit());
		assertEquals("Stueck", Unit.STUECK.getUnit());
		assertEquals("Tasse", Unit.TASSE.getUnit());
		assertEquals("Teeloeffel", Unit.TEELOEFFEL.getUnit());
	}
}