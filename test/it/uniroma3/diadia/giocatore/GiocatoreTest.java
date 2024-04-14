package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {
    private Giocatore giocatore;

    @Before
    public void setUp() {
        giocatore = new Giocatore();
    }

    @Test
    public void testCfuIniziali() {
        assertEquals(20, giocatore.getCfu());
    }

    @Test
    public void testCfuSet() {
        giocatore.setCfu(15);
        assertEquals(15, giocatore.getCfu());
    }

    @Test
    public void testBorsaInizialeVuota() {
        Borsa borsa = giocatore.getBorsa();
        assertTrue(borsa.isEmpty());
    }

    @Test
    public void testAggiuntaAttrezzoInBorsa() {
        Attrezzo attrezzo = new Attrezzo("Martello", 5);
        giocatore.getBorsa().addAttrezzo(attrezzo);
        assertTrue(giocatore.getBorsa().hasAttrezzo("Martello"));
    }

    @Test
    public void testRimozioneAttrezzoDallaBorsa() {
        Attrezzo attrezzo = new Attrezzo("Martello", 5);
        giocatore.getBorsa().addAttrezzo(attrezzo);
        assertTrue(giocatore.getBorsa().hasAttrezzo("Martello"));
        giocatore.getBorsa().removeAttrezzo(attrezzo);
        assertFalse(giocatore.getBorsa().hasAttrezzo("Martello"));
    }
}
