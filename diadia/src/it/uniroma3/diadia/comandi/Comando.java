package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public interface Comando {
    /*
    * Esecuzione del comando
    * */
    public void esegui(Partita partita);

    /*
    * Set parametro del comando
    * */
    public void setParametro(String parametro);
}
