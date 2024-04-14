package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "prendi", "posa", "aiuto", "fine" };

	private Partita partita;
    private IOConsole console;
	public DiaDia(IOConsole scan) {
		this.console=scan;
		this.partita = new Partita();
	}
    
	@SuppressWarnings("resource")
	public void gioca() {
		String istruzione;

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(this.partita.getStanzaCorrente().getAttrezzo(comandoDaEseguire.getParametro()));
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(this.partita.getGiocatore().getBorsa().getAttrezzo(comandoDaEseguire.getParametro()));
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.isFinita()) {
			if (this.partita.vinta())
				this.console.mostraMessaggio("Hai vinto!");
			else
				this.console.mostraMessaggio("Hai perso!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:
	private void prendi(Attrezzo attrezzo) {
		if (attrezzo == null)
			this.console.mostraMessaggio("che attrezzo vuoi prendere?");
		else {
			if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				this.console.mostraMessaggio("Aggiunto!");
			} else
				this.console.mostraMessaggio("borsa piena!");
		}
		this.console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());

	}

	private void posa(Attrezzo attrezzo) {
		if (attrezzo == null)
			this.console.mostraMessaggio("che attrezzo vuoi posare?");
		else {
			if (this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
				this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
				this.console.mostraMessaggio("posato!");
			} else
				this.console.mostraMessaggio("stanza piena!");
		}
		this.console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());

	}

	// private void posa(Attrezzo atr)
	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			System.out.print(elencoComandi[i] + " ");
		this.console.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {

		if (direzione == null)
			this.console.mostraMessaggio("Dove vuoi andare ?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.console.mostraMessaggio("Direzione inesistente");
			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu();
				this.partita.getGiocatore().setCfu(--cfu);
			}
		}

		this.console.mostraMessaggio(
				partita.getStanzaCorrente().getDescrizione() + "numero cfu" + this.partita.getGiocatore().getCfu());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");

		// si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console=new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
		
	}
}