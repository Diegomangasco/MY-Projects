package carte;

import java.util.ArrayList;
import java.util.List;

public class Banco{
	
	private List<Integer> carte;
	public Banco() {
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
	public String getCarte() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<carte.size(); i++) {
			if(this.carte.get(i)<11) {
				if(this.carte.get(i)==0)
					sb.append("A ");
				else sb.append(this.carte.get(i) + " ");
			}
			else {
				switch(this.carte.get(i)) {
				case 11:
					sb.append("J ");
					break;
				case 12:
					sb.append("Q ");
					break;
				case 13:
					sb.append("K ");
					break;
				}
			}
		}
		return sb.toString();
	}

}
