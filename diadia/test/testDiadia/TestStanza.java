package testDiadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import diadia.Stanza;

class TestStanza {
	
	

	@Test
	void testImpostaStanzaAdiacenteSemplice() {
		Stanza cucina = new Stanza("Cucina");
		Stanza soggiorno = new Stanza("Soggiorno");
		
		soggiorno.impostaStanzaAdiacente("sud", cucina);
		cucina.impostaStanzaAdiacente("nord", soggiorno);
		
		assertEquals(soggiorno.getStanzaAdiacente("sud"), cucina);		
		
	}
	
	@Test
	void testImpostaStanzaAdiacenteCatena() {
		
		Stanza cucina = new Stanza("Cucina");
		Stanza soggiorno = new Stanza("Soggiorno");
		Stanza giardino = new Stanza("Giardino");
		
		giardino.impostaStanzaAdiacente("sud", soggiorno);
		soggiorno.impostaStanzaAdiacente("nord", giardino);
		
		soggiorno.impostaStanzaAdiacente("sud", cucina);
		cucina.impostaStanzaAdiacente("nord", soggiorno);
		
		assertEquals(giardino.getStanzaAdiacente("sud").getStanzaAdiacente("sud"), cucina);
	}
	
	@Test
	void testImpostaStanzaAdiacenteCerchio() {
		
		Stanza cucina = new Stanza("Cucina");
		Stanza soggiorno = new Stanza("Soggiorno");
		Stanza giardino = new Stanza("Giardino");
		Stanza ingresso = new Stanza("Ingresso");
		
		giardino.impostaStanzaAdiacente("sud", soggiorno);
		soggiorno.impostaStanzaAdiacente("nord", giardino);
		
		soggiorno.impostaStanzaAdiacente("sud", cucina);
		cucina.impostaStanzaAdiacente("nord", soggiorno);
		
		cucina.impostaStanzaAdiacente("sud", ingresso);
		ingresso.impostaStanzaAdiacente("nord", cucina);
		
		ingresso.impostaStanzaAdiacente("sud", giardino);
		giardino.impostaStanzaAdiacente("nord", ingresso);
		
		assertEquals(giardino.getStanzaAdiacente("sud").getStanzaAdiacente("sud").getStanzaAdiacente("sud").getStanzaAdiacente("sud"), giardino);
		
	}
	
	

}
