package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10];
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if(this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public int getPeso() {
		int peso = 0;
		for(int i=0; i<this.numeroAttrezzi; i++) {
			peso += this.attrezzi[i].getPeso();
		}
		return peso;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for(int i=0; i<this.numeroAttrezzi; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		}
		return a;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	
	//Metodo per rimuovere un oggetto dalla borsa
	public boolean removeAttrezzo(Attrezzo attrezzo) { //Punto 3 dell'homework A
		// TODO da implementare
		if(this.isEmpty()) 
			return false;
		
		int indiceDaRimuovere = 0;
		for(int i=0; i<numeroAttrezzi; i++) {
			if(attrezzi[i].equals(attrezzo)) {
				//metodo che mette l'attrezzo nella borsa				
				//
				for(int j = indiceDaRimuovere+1; j<numeroAttrezzi; j++) {
					attrezzi[j-1] = attrezzi[j];
				}
				attrezzi[numeroAttrezzi-1] = null;
				numeroAttrezzi--;
				return true;
			}
			indiceDaRimuovere++;
		}
		
		return false;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if(!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg):  ");
			for(int i=0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
}
