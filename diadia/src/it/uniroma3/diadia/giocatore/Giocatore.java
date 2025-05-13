package it.uniroma3.diadia.giocatore;

public class Giocatore {

		static private final int CFU_INIZIALI = 20;
		private int cfu = CFU_INIZIALI;
		private Borsa borsa;

		public Giocatore(){
			borsa = new Borsa();
		}
		
		public int getCfuIniziali() {
			return CFU_INIZIALI;
		}
		
		public int getCfu() {
			return this.cfu;
		}

		public void setCfu(int cfu) {
			this.cfu = cfu;
		}

		public Borsa borsaGiocatore(){
			return borsa;
		}
}
