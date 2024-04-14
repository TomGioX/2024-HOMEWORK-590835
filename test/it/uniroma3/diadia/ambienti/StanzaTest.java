package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

public class StanzaTest {

    private Stanza stanza;
    private Stanza stanzaAdiacente;

    @Before
    public void setUp() {
        stanza = new Stanza("Stanza di prova");
        stanzaAdiacente = new Stanza("Stanza adiacente");
    }

    @Test
    public void testGetStanzaAdiacenteEsistente() {
        stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
        assertEquals(stanzaAdiacente, stanza.getStanzaAdiacente("nord"));
    }

    @Test
    public void testGetStanzaAdiacenteNonEsistente() {
        assertNull(stanza.getStanzaAdiacente("sud"));
    }

    @Test
    public void testGetStanzaAdiacenteConStanzaVuota() {
        Stanza stanzaSenzaAdiacenti = new Stanza("Stanza senza adiacenti");
        assertNull(stanzaSenzaAdiacenti.getStanzaAdiacente("nord"));
    }

    @Test
    public void testImpostaStanzaAdiacenteAggiorna() {
        Stanza stanzaEsistente = new Stanza("Stanza esistente");
        stanza.impostaStanzaAdiacente("nord", stanzaEsistente);
        stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
        assertEquals(stanzaAdiacente, stanza.getStanzaAdiacente("nord"));
    }

    @Test
    public void testImpostaStanzaAdiacenteNuovaDirezione() {
        Stanza stanzaEsistente = new Stanza("Stanza esistente");
        stanza.impostaStanzaAdiacente("nord", stanzaEsistente);
        assertEquals(stanzaEsistente, stanza.getStanzaAdiacente("nord"));
    }

  
}
