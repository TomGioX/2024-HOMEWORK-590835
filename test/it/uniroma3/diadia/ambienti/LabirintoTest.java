package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {
	private Labirinto labirinto;
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;

	@Before
	public void setUp() {
		labirinto = new Labirinto();
		stanzaIniziale = labirinto.getStanzaIniziale();
		stanzaFinale = labirinto.getStanzaFinale();

	}

	@Test
	public void testNonUguale() {
		Stanza atrio = null;
		assertFalse(stanzaIniziale == atrio);
	}
    
	@Test
	public void testUguale() {
		Stanza atrio = null;
		assertTrue(stanzaIniziale == atrio);
	}

}
