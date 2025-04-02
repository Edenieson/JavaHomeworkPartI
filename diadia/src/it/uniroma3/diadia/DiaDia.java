package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private Borsa borsa;

	public DiaDia() {
		this.partita = new Partita();
		this.borsa = new Borsa();
	}

	public void gioca(IOConsole console) {
		String istruzione; 
		//System.out.println(MESSAGGIO_BENVENUTO);
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		//scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione, console));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione, IOConsole console) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(console); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro(), console);
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto(console);
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro(), console);
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro(), console);
		else
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto(IOConsole console) {
		String msg;
		for(int i=0; i< elencoComandi.length; i++) {
			msg = elencoComandi[i]+" ";
			console.mostraMessaggio(msg);
		}
		msg = " ";
		console.mostraMessaggio(msg);
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione, IOConsole console) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	
	//Comando "Prendi"
	private void prendi(String nomeAttrezzo, IOConsole console) { //Da testare 
		if(nomeAttrezzo == null) {
			console.mostraMessaggio("Quale attrezzo vuoi prendere?"); //Tutto questo pezzo serve a ricordare all'utente gli attrezzi che ci sono nella stanza
			//Faccio uno String Builder per ricordare all'utente 
			//Gli attrezzi nella stanza
			Stanza stanzaCorrente = partita.getStanzaCorrente();
			
			StringBuilder attrezziInStanza = new StringBuilder();
			attrezziInStanza.append(stanzaCorrente.getNome());
			attrezziInStanza.append("\nAttrezzi nella stanza: ");		
	    	for (Attrezzo attrezzo : stanzaCorrente.getAttrezzi()) 
	    		if(attrezzo!=null)
	    			attrezziInStanza.append(stanzaCorrente.getAttrezzo(nomeAttrezzo).toString()+" ");
	    	return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			borsa.addAttrezzo(stanzaCorrente.getAttrezzo(nomeAttrezzo));
			stanzaCorrente.removeAttrezzo(stanzaCorrente.getAttrezzo(nomeAttrezzo));
			console.mostraMessaggio("Attrezzo preso!");
		}
		console.mostraMessaggio("Attrezzo non trovato!");
		return;
	}
	
	//Comando "Posa"
	private void posa(String nomeAttrezzo, IOConsole console) {
		if(nomeAttrezzo == null) {
			console.mostraMessaggio("Quale attrezzo vuoi posare?");
			borsa.toString();
			return;
		}
		if(borsa.isEmpty()) {
			console.mostraMessaggio("Borsa vuota!");
			return;
		}
		if(!borsa.hasAttrezzo(nomeAttrezzo)) {
			console.mostraMessaggio("Attrezzo non trovato!");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		stanzaCorrente.addAttrezzo(borsa.getAttrezzo(nomeAttrezzo));
		borsa.removeAttrezzo(borsa.getAttrezzo(nomeAttrezzo));
		console.mostraMessaggio("Attrezzo posato!");
		return;
	}

	/**
	 * Comando "Fine".
	 */
	private void fine(IOConsole console) {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		IOConsole console = new IOConsole();
		gioco.gioca(console);
	}
}