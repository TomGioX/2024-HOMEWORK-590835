package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
    private Borsa borsa;

    @Before
    public void setUp() {
        borsa = new Borsa();
    }

    @Test
    public void testAddAttrezzo() {
        Attrezzo attrezzo1 = new Attrezzo("Martello", 5);
        assertTrue(borsa.addAttrezzo(attrezzo1));
        assertTrue(borsa.hasAttrezzo("Martello"));
        assertEquals(attrezzo1, borsa.getAttrezzo("Martello"));
    }

    @Test
    public void testRemoveAttrezzo() {
        Attrezzo attrezzo1 = new Attrezzo("Martello", 5);
        borsa.addAttrezzo(attrezzo1);
        assertTrue(borsa.removeAttrezzo(attrezzo1));
        assertFalse(borsa.hasAttrezzo("Martello"));
        assertNull(borsa.getAttrezzo("Martello"));
    }

    @Test
    public void testAddAttrezzoConLimitePeso() {
        Attrezzo attrezzo1 = new Attrezzo("Martello", 7);
        Attrezzo attrezzo2 = new Attrezzo("Chiave", 4);
        borsa.addAttrezzo(attrezzo1);
        assertFalse(borsa.addAttrezzo(attrezzo2)); // La borsa ha un peso massimo di 10, quindi attrezzo2 non dovrebbe poter essere aggiunto
        assertFalse(borsa.hasAttrezzo("Chiave"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(borsa.isEmpty());
        Attrezzo attrezzo1 = new Attrezzo("Martello", 5);
        borsa.addAttrezzo(attrezzo1);
        assertFalse(borsa.isEmpty());
    }

    @Test
    public void testGetPeso() {
        Attrezzo attrezzo1 = new Attrezzo("Martello", 5);
        Attrezzo attrezzo2 = new Attrezzo("Chiave", 3);
        borsa.addAttrezzo(attrezzo1);
        borsa.addAttrezzo(attrezzo2);
        assertEquals(8, borsa.getPeso());
    }
}
