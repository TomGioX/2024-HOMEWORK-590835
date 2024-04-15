package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;

import static org.junit.Assert.*;

public class PartitaTest {
Partita p=new Partita();
Stanza s=new Stanza("Stanza");


public void testGetStanzaVincente() {
	assertEquals("Biblioteca",p.getLabirinto().getStanzaFinale().getNome());
}
	
public void testIsFinita() {
	assertFalse(p.isFinita());
}
}
