package Project;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//backend of the application
public class Math {
	private int anni;
	private int mesi;
	private int giorni;
	private int counter;
	private int counterR;
	private int counterD;
	private int [] lastDay;
	private ArrayList <Integer> Giorni;
	private ArrayList <Integer> Mesi;
	private ArrayList <Integer> Anni;
	private String [] Testo;
	private String [] Risca;
	private int NMax = 100;
	public Math() {
		Giorni = new ArrayList<>();
		Mesi = new ArrayList<>();
		Anni = new ArrayList<>();
		Testo = new String[NMax];
		Risca = new String[NMax];
		lastDay = new int[NMax];
		giorni = 0;
		mesi = 0;
		anni = 0;
		counter = 0;
		counterR = 0;
		counterD = 0;
	}
	
	public void setGiorni(int g) {
		Giorni.add(g);
	}
	
	public void removeGiorni() {
		Giorni.remove(Giorni.size()-1);
	}
	
	public void setMesi(int g) {
		Mesi.add(g);
	}
	
	public void removeMesi() {
		Mesi.remove(Mesi.size()-1);
	}
	
	public void setAnni(int g) {
		Anni.add(g);
	}
	
	public void removeAnni() {
		Anni.remove(Anni.size()-1);
	}
	
	public void setCounter(int i) {
		if(i == 1)
			counter++;
		if(i == -1)
			counter--;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounterR(int i) {
		if(i == 1)
			counterR++;
		if(i == -1)
			counterR--;
	}
	
	public int getCounterR() {
		return counterR;
	}
	
	public void setCounterD(int i) {
		if(i == 1)
			counterD++;
		if(i == -1)
			counterD--;
	}
	
	public int getCounterD() {
		return counterD;
	}
	
	public String getTesto(int i) {
		return Testo[i];
	}
	
	public void setTesto(String t) {
		Testo[counter] = t;
	}
	
	public String getRisca(int i) {
		return Risca[i];
	}
	
	public void setRisca(String t) {
		Risca[counterR] = t;
	}
	
	public void setgiorni(int n, int i) {
		if(i == 0)
			giorni+=n;
		if(i == -1)
			giorni = n;
	}
	
	public int getgiorni() {
		return giorni;
	}
	
	public void setmesi(int n) {
		mesi=n;
	}
	
	public int getmesi() {
		return mesi;
	}
	
	public void setanni(int n) {
		anni = n;
	}
	
	public int getanni() {
		return anni;
	}
	
	public int getLastDay() {
		return lastDay[counterD];
	}
	
	public int getLastDayIndex(int i) {
		return lastDay[i];
	}
	
	public void setLastDay(int i) {
		lastDay[counterD] = i;
	}
	
	public void calcolaTot() {
		int i;
		for(i = 0; i<counter-1; i = i+2) {
			calcoloSingolo(this.Giorni.get(i), this.Giorni.get(i+1), 
	    				this.Mesi.get(i), this.Mesi.get(i+1), 
	    				this.Anni.get(i), this.Anni.get(i+1));
		}
	}
	public void calcoloSingolo(int G1, int G2, int M1, int M2, int A1, int A2) {
		int number1 = (M1*30)+G1;
	    int number2 = (M2*30)+G2;	//number of days from the beginning of the year
	    if(A2 > A1){
	    	if(number2 > number1){
	    		this.anni += A2 - A1;
	            if(G2>=G1){
	            	this.mesi += M2 - M1;
	            	this.giorni += G2 - G1 + 1;
	            }
	            else{
	            	this.mesi += M2 - M1 - 1;
	            	this.giorni += (30 - G1) + G2 + 1;
	            }
	    	}
	    	else{
	    		this.anni += A2 - A1 - 1;
	    		if(G2>=G1){
            		this.mesi += (12 - M1) + M2;
            		this.giorni += G2 - G1 + 1;
	    		}
	    		else{
	    			this.mesi += (12 - M1) + M2 - 1;
	    			this.giorni += (30 - G1) + G2 + 1;
	    		}
	    	}
	    }
	    else{
    		this.anni += 0;
        	this.mesi += M2 - M1;
        	this.giorni += G2 - G1 + 1;
    	}
	}
	
	public void normalizzatore() {
		int i = 1;
    	while(i == 1) {		//normalization of the results in 30 days, 12 months per years
	    	if(this.giorni >= 30) {
	    		this.giorni = this.giorni - 30;
	    		this.mesi++;
	    	}
	    	else i = 0;
	    }
	    i = 1;
	    while(i == 1) {
	    	if(this.mesi >= 12) {
	    		this.mesi = this.mesi - 12;
	    		this.anni++;
	    	}
	    	else i = 0;
	    }
	}
	
	public int creaFile(String nome) {
		String nuovoNome = nome.replaceAll(" ", "_");
		String path = "C:\\Users\\UTENTE\\Desktop\\";
		File doc = new File(path + nuovoNome + ".doc");
		try {
			if(doc.createNewFile()) {
				doc.setReadable(true);
				doc.setExecutable(true);
				doc.setWritable(true);
				JOptionPane.showMessageDialog(null, "File creato con successo!");
				try {
					FileWriter w = new FileWriter(path + nuovoNome + ".doc");
					w.write(nome + "\n");
					w.write("\nPeriodo lavorativo in giorno/mese/anno: \n");
					for(int i = 0; i<counter; i++) {
						if(i%2!=0) {
							w.write(Testo[i] + "\n");
						}
						else {
							w.write(Testo[i] + " - ");
						}
					}
					w.write("\nRiscatti in anni mesi giorni: \n");
					for(int i = 0; i<counterR; i++) {
						w.write(Risca[i] + "\n");
					}
					w.write("\nTotale anzianità contributiva: " + anni + " anni, " + mesi + " mesi, " + giorni + " giorni");
					w.close();
				}catch(IOException e) {
					e.printStackTrace();
					return 0;
				}
				return 1;
			}
			else {
				JOptionPane.showMessageDialog(null, "File già esistente");
				return 0;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public void resetAll() {
		Anni.clear();
		Mesi.clear();
		Giorni.clear();
		this.counterR = 0;
		this.counter = 0;
		this.anni = 0;
		this.mesi = 0;
		this.giorni = 0;
		this.counterD = 0;
	}
}
