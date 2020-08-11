package carte;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Giocatore extends JFrame{

	private List<Integer> carte;
	private int cassa = 100;
	public Giocatore() {
		carte = new ArrayList<>();
	}
	public int getPunteggio() {
		int sum = 0;
		for(int i = 0; i<carte.size(); i++) {
			if(carte.get(i)==0)
				sum+=11;
			else if(carte.get(i)>10)
				sum+=10;
			else sum+=carte.get(i);
		}
		return sum;
	}
	public void addCarta(int carta) {
		this.carte.add(carta);
	}
	public void setNuovaMano() {
		this.carte.clear();
	}
	public int getCassa() {
		return this.cassa;
	}
	public void setCassa(int cassa) {
		this.cassa+=cassa;
	}
	public void setPuntata(int puntata) {
		this.cassa-=puntata;
		if(this.cassa<0) {
			JOptionPane.showOptionDialog(null, "Hai finito i soldi!", "Arrivederci", 
					JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			System.exit(ABORT);
		}
	}
}
