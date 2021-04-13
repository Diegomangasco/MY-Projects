package Project;
import java.awt.event.ActionEvent;




import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

//frontend of the application
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
	private JLabel NomeCognome;
	private JTextArea InserisciNome;
	private JTextArea Riscatti;
	private JTextArea Somme;
	private Math numeric;
	public Interfaccia() {
		this.setTitle("CalcolatorePeriodo");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		Inserisci_1 = new JButton("Inserisci_1");
		Inserisci_1.setBounds(100, 150, 200, 75);
		Inserisci_2 = new JButton("Inserisci_2");
		Inserisci_2.setBounds(400, 150, 200, 75);
		Calcola = new JButton("Calcola");
		Calcola.setBounds(100, 250, 200, 75);
		Reset = new JButton("Reset");
		Reset.setBounds(400, 360, 200, 75);
		Riscatto = new JButton("Riscatto");
		Riscatto.setBounds(400, 250, 200, 75);
		L1 = new JLabel("Clicca su Inserisci_1 per inserire la prima data");
		L1.setBounds(75, 75, 400, 75);
		L2 = new JLabel("Clicca su Inserisci_2 per inserire la seconda data");
		L2.setBounds(375, 75, 400, 75);
		TitoloS = new JLabel("Date inserite:");
		TitoloS.setBounds(100, 325, 200, 75);
		TitoloR = new JLabel("Riscatti inseriti:");
		TitoloR.setBounds(250, 325, 200, 75);
		Somme = new JTextArea();
		Somme.setBounds(100, 375, 100, 300);
		Riscatti = new JTextArea();
		Riscatti.setBounds(250, 375, 100, 300);
		NomeCognome = new JLabel("Inserisci Nome e Cognome: ");
		NomeCognome.setBounds(75, 25, 400, 75);
		InserisciNome = new JTextArea();
		InserisciNome.setBounds(300, 50, 300, 20);
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
		this.add(InserisciNome);
		this.add(NomeCognome);
		Inserisci_1.addActionListener(this);
		Inserisci_2.addActionListener(this);
		Calcola.addActionListener(this);
		Reset.addActionListener(this);
		Riscatto.addActionListener(this);
		numeric = new Math();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) throws NumberFormatException, ArrayIndexOutOfBoundsException{
		// TODO Auto-generated method stub

		if(e.getActionCommand().equals("Inserisci_1")) {
			int G1 = 0;
			String data1;
			String data2 [];
			try {
				data1 = JOptionPane.showInputDialog(
						null, "Inserisci la prima data",
						"Prima Data", JOptionPane.QUESTION_MESSAGE);
				data2 = data1.split("/");
				if(data2.length!=3) throw new ArrayIndexOutOfBoundsException();
				G1 = Integer.valueOf(data2[0]);
				if(G1 > 30) G1 = 30;
				numeric.setGiorni(G1);
				numeric.setMesi(Integer.valueOf(data2[1]));
				numeric.setAnni(Integer.valueOf(data2[2]));
				numeric.setTesto(data1);
				numeric.setCounter(1);
				Somme.append(data1 + "\n");
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
			}
			catch(ArrayIndexOutOfBoundsException a) {
	    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
	    	}
		}
	    if(e.getActionCommand().equals("Inserisci_2")) {
	    	int G2 = 0;
	    	String data1;
			String data2 [];
			try {
				data1 = JOptionPane.showInputDialog(
						null, "Inserisci la seconda data",
						"Seconda Data", JOptionPane.QUESTION_MESSAGE);
				data2 = data1.split("/");
				if(data2.length!=3) throw new ArrayIndexOutOfBoundsException();
				G2 = Integer.valueOf(data2[0]);
				if (G2 > 30) G2 = 30;
				numeric.setGiorni(G2);
				numeric.setMesi(Integer.valueOf(data2[1]));
				numeric.setAnni(Integer.valueOf(data2[2]));
				numeric.setTesto(data1);
				numeric.setCounter(1);
				Somme.append(data1 + "\n");
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
			}
			catch(ArrayIndexOutOfBoundsException a) {
	    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
	    	}
	    }
	    
	    if(e.getActionCommand().equals("Reset")) {
	    	String answer = JOptionPane.showInputDialog(
					null, "Resettare riscatto o data",
					"Reset", JOptionPane.QUESTION_MESSAGE);
	    	if((answer.compareTo("data")==0 || answer.compareTo("Data") == 0) && numeric.getCounter()>0) {
	    		numeric.setCounter(-1);	//remove the last member of all structures
	    		Somme.removeAll();
	    		Somme.setText("");
	    		int i;
				for(i = 0; i<numeric.getCounter(); i++) {
					Somme.append(numeric.getTesto(i) + "\n");
				}
				numeric.removeGiorni();
				numeric.removeMesi();
				numeric.removeAnni();
	    	}
	    	else if((answer.compareTo("riscatto")==0 || answer.compareTo("Riscatto") == 0) && numeric.getCounterR()>0) {
	    		numeric.setCounterR(-1);	//remove the last member of all structures
	    		Riscatti.removeAll();
	    		Riscatti.setText("");
	    		int i;
	    		for(i = 0; i<numeric.getCounterR(); i++) {
	    			Riscatti.append(numeric.getRisca(i) + "\n");
	    		}
	    		numeric.setCounterD(-1);
				numeric.setgiorni(-numeric.getLastDay(), 0);	//remove the last Riscatto
	    	}
	    }
	    
	    if(e.getActionCommand().equals("Riscatto")) {
	    	String data1 = JOptionPane.showInputDialog(
					null, "Inserisci il riscatto come Anni Mesi Giorni",
					"Riscatto", JOptionPane.QUESTION_MESSAGE);
	    	try {
		    	String data2 [] = data1.split(" ");
		    	numeric.setLastDay(Integer.valueOf(data2[2]) + 30*Integer.valueOf(data2[1]) + 30*12*Integer.valueOf(data2[0]));
		    	numeric.setgiorni(numeric.getLastDay(), 0);
		    	numeric.setRisca(data1);
		    	numeric.setCounterD(1);
		    	numeric.setCounterR(1);
		    	Riscatti.append(data1 + "\n");
	    	}
	    	catch(NumberFormatException ex) {
	    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
	    	}
	    	catch(ArrayIndexOutOfBoundsException a) {
	    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
	    	}
	    }
	    
	    if(e.getActionCommand().equals("Calcola")) {
	    	int decision = 0;
	    	String data1;
			String data2 [];
			String nome;
			int g, m, a;
	    	numeric.calcolaTot();
	    	numeric.normalizzatore();
			JOptionPane.showMessageDialog(null, 
					"Anni: " + numeric.getanni() + " Mesi: " + numeric.getmesi() + " Giorni: " + numeric.getgiorni());
			do {
				decision = JOptionPane.showConfirmDialog(null, "Vuoi modificare l'ultima data?");
				if(decision == 0) {
					numeric.setCounter(-1);
					Somme.removeAll();
					Somme.setText("");
					for(int t = 0; t<numeric.getCounter(); t++) {
						Somme.append(numeric.getTesto(t) + "\n");
					}
					try {
						data1 = JOptionPane.showInputDialog(
								null, "Inserisci la data",
								"Data", JOptionPane.QUESTION_MESSAGE);
						data2 = data1.split("/");	//try to add new date in case the examinated person can't reach the inn
						if(data2.length!=3) throw new ArrayIndexOutOfBoundsException();
						a = Integer.valueOf(data2[2]);	//the user want to watch how much months the person have before the inn
						m = Integer.valueOf(data2[1]);
						g = Integer.valueOf(data2[0]);
						if(g==31) g = 30;
						numeric.setTesto(data1);	//reset of the structures
				    	numeric.setCounter(1);
						numeric.removeGiorni();
						numeric.setGiorni(g);
						numeric.removeMesi();
						numeric.setMesi(m);
						numeric.removeAnni();
						numeric.setAnni(a);			//replace the last date with the new one
						numeric.setgiorni(0, -1);
						for(int i = 0; i<numeric.getCounterD(); i++)	//put the values of the Riscatti
							numeric.setgiorni(numeric.getLastDayIndex(i), 0);
						numeric.setanni(0);
						numeric.setmesi(0);
						numeric.calcolaTot();
						numeric.normalizzatore();
				    	Somme.append(data1 + "\n");
						JOptionPane.showMessageDialog(null, 
								"Anni: " + numeric.getanni() + " Mesi: " + numeric.getmesi() + " Giorni: " + numeric.getgiorni());
					}
					catch(NumberFormatException ex) {
			    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
			    		numeric.setCounter(1);
			    		Somme.removeAll();
						Somme.setText("");
						for(int t = 0; t<numeric.getCounter(); t++) {
							Somme.append(numeric.getTesto(t) + "\n");
						}
			    	}
			    	catch(ArrayIndexOutOfBoundsException index) {
			    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento!");
			    		numeric.setCounter(1);
			    		Somme.removeAll();
						Somme.setText("");
						for(int t = 0; t<numeric.getCounter(); t++) {
							Somme.append(numeric.getTesto(t) + "\n");
						}
			    	}
				}
			}while(decision==0);
			decision = JOptionPane.showConfirmDialog(null, "Desideri salvare?");
			if(decision == 0) {
				nome = InserisciNome.getText();
				while(numeric.creaFile(nome)==0) {
					JOptionPane.showMessageDialog(null, "Errore nella creazione del File");
					decision = JOptionPane.showConfirmDialog(null, "Desideri salvare?");
					if(decision!=0) break;
					else {
						String nn = JOptionPane.showInputDialog(
								null, "Inserisci un nuovo nome",
								"Nuovo Nome", JOptionPane.QUESTION_MESSAGE);
						InserisciNome.removeAll();
						InserisciNome.setText("");
						InserisciNome.setText(nn);
					}
				}
			}
			Somme.removeAll();
			Somme.setText("");
			Riscatti.removeAll();
			Riscatti.setText("");
			numeric.resetAll();
		}
	}
}
