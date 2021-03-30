package Project;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Interfaccia extends JFrame implements ActionListener {
	/**
	 * Make an application to calculate the work period of an ASL's worker
	 */
	private static final long serialVersionUID = 1L;
	private JButton Inserisci_1;
	private JButton Inserisci_2;
	private JButton Calcola;
	private JButton Reset;
	private JButton Riscatto;
	private JLabel L1;
	private JLabel L2;
	private JLabel TitoloS;
	private JLabel TitoloR;
	private JTextArea Riscatti;
	private JTextArea Somme;
	private int anni, mesi, giorni, counter, counterR, counterD;
	private int [] lastDay;
	private String [] Testo;
	private String [] Risca;
	private ArrayList <Integer> Giorni;
	private ArrayList <Integer> Mesi;
	private ArrayList <Integer> Anni;
	private int NMax = 100;
	public Interfaccia() {
		this.setTitle("CalcolatorePeriodo");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		Inserisci_1 = new JButton("Inserisci_1");
		Inserisci_1.setBounds(100, 100, 200, 75);
		Inserisci_2 = new JButton("Inserisci_2");
		Inserisci_2.setBounds(400, 100, 200, 75);
		Calcola = new JButton("Calcola");
		Calcola.setBounds(100, 250, 200, 75);
		Reset = new JButton("Reset");
		Reset.setBounds(400, 250, 200, 75);
		Riscatto = new JButton("Riscatto");
		Riscatto.setBounds(400, 400, 200, 75);
		L1 = new JLabel("Clicca su Inserisci_1 per inserire la prima data");
		L1.setBounds(75, 25, 400, 75);
		L2 = new JLabel("Clicca su Inserisci_2 per inserire la seconda data");
		L2.setBounds(375, 25, 400, 75);
		TitoloS = new JLabel("Date inserite:");
		TitoloS.setBounds(100, 325, 200, 75);
		TitoloR = new JLabel("Riscatti inseriti:");
		TitoloR.setBounds(250, 325, 200, 75);
		Somme = new JTextArea();
		Somme.setBounds(100, 375, 100, 300);
		Riscatti = new JTextArea();
		Riscatti.setBounds(250, 375, 100, 300);
		this.add(Inserisci_1);
		this.add(Inserisci_2);
		this.add(Calcola);
		this.add(Reset);
		this.add(Riscatto);
		this.add(TitoloS);
		this.add(TitoloR);
		this.add(Somme);
		this.add(Riscatti);
		this.add(L1);
		this.add(L2);
		Inserisci_1.addActionListener(this);
		Inserisci_2.addActionListener(this);
		Calcola.addActionListener(this);
		Reset.addActionListener(this);
		Riscatto.addActionListener(this);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		int G1 = 0, G2 = 0;
		if(e.getActionCommand().equals("Inserisci_1")) {
			String data1;
			String data2 [];
			data1 = JOptionPane.showInputDialog(
					null, "Inserisci la prima data",
					"Prima Data", JOptionPane.QUESTION_MESSAGE);
			data2 = data1.split("/");
			G1 = Integer.valueOf(data2[0]);
			if(G1 > 30) G1 = 30;
			Giorni.add(G1);
			Mesi.add(Integer.valueOf(data2[1]));
			Anni.add(Integer.valueOf(data2[2]));
			Testo[counter] = data1;
			counter++;
			Somme.append(data1 + "\n");
		}
	    if(e.getActionCommand().equals("Inserisci_2")) {
	    	String data1;
			String data2 [];
			data1 = JOptionPane.showInputDialog(
					null, "Inserisci la seconda data",
					"Seconda Data", JOptionPane.QUESTION_MESSAGE);
			data2 = data1.split("/");
			G2 = Integer.valueOf(data2[0]);
			if (G2 > 30) G2 = 30;
			Giorni.add(G2);
			Mesi.add(Integer.valueOf(data2[1]));
			Anni.add(Integer.valueOf(data2[2]));
			Testo[counter] = data1;
			counter++;
			Somme.append(data1 + "\n");
	    }
	    
	    if(e.getActionCommand().equals("Reset")) {
	    	String answer = JOptionPane.showInputDialog(
					null, "Resettare riscatto o data",
					"Reset", JOptionPane.QUESTION_MESSAGE);
	    	if(answer.compareTo("data")==0 && counter>0) {
	    		counter--;	//remove the last member of all structures
	    		Somme.removeAll();
	    		Somme.setText("");
	    		int i;
    				for(i = 0; i<counter; i++) {
    					Somme.append(Testo[i] + "\n");
    				}
    				Giorni.remove(Giorni.size()-1);
    				Mesi.remove(Mesi.size()-1);
    				Anni.remove(Anni.size()-1);
	    	}
	    	else if(answer.compareTo("riscatto")==0 && counterR>0) {
	    		counterR--;	//remove the last member of all structures
	    		Riscatti.removeAll();
	    		Riscatti.setText("");
	    		int i;
    				for(i = 0; i<counterR; i++) {
    					Riscatti.append(Testo[i] + "\n");
    				}
    				this.giorni-=this.lastDay[counterD];
    				this.counterD--;
	    	}
	    }
	    
	    if(e.getActionCommand().equals("Riscatto")) {
	    	String data1 = JOptionPane.showInputDialog(
					null, "Inserisci il riscatto come Anni Mesi Giorni",
					"Riscatto", JOptionPane.QUESTION_MESSAGE);
	    	String data2 [] = data1.split(" ");
	    	this.lastDay[this.counterD] = Integer.valueOf(data2[2]) + 30*Integer.valueOf(data2[1]) + 30*12*Integer.valueOf(data2[0]);
	    	this.giorni += this.lastDay[counterD];
	    	Risca[counterR] = data1;
	    	counterR++;
	    	Riscatti.append(data1 + "\n");
	    	this.counterD++;
	    }
	    
	    if(e.getActionCommand().equals("Calcola")) {
	    	int i;
	    	for(i = 0; i<=this.Anni.size()-2; i = i+2) {	//use the function calcoloSingolo for all coupples
	    		this.calcoloSingolo(this.Giorni.get(i), this.Giorni.get(i+1), 
	    				this.Mesi.get(i), this.Mesi.get(i+1), 
	    				this.Anni.get(i), this.Anni.get(i+1));
	    	}
	    	i = 1;
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
			JOptionPane.showMessageDialog(null, 
					"Anni: " + this.anni + " Mesi: " + this.mesi + " Giorni: " + this.giorni);
			Anni.clear();
			Mesi.clear();
			Giorni.clear();
			Somme.removeAll();
			Somme.setText("");
			Riscatti.removeAll();
			Riscatti.setText("");
			this.counterR = 0;
			this.counter = 0;
			this.anni = 0;
			this.mesi = 0;
			this.giorni = 0;
			this.counterD = 0;
		}
	}
}
