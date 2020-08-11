package carte;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Gestione extends JFrame implements ActionListener{

	protected JButton carta;
	protected JButton vedo;
	private JLabel text;
	private JLabel point;
	private JLabel punt;
	private JLabel carteBanco;
	private JLabel punteggio_B;
	private JLabel punteggio_G;
	private Banco B;
	private Giocatore G;
	private int punteggioB = 0;
	private int punteggioG = 0;
	private int puntata = 0;
	private boolean vittoria = false;
	public Gestione() {
		this.setTitle("BlackJack");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		carta = new JButton("Carta");
		carta.setBounds(275, 300, 100, 100);
		this.add(carta);
		vedo = new JButton("Vedo");
		vedo.setBounds(275, 500, 100, 100);
		this.add(vedo);
		text = new JLabel("Carte: ");
		text.setBounds(275, 150, 200, 50);
		this.add(text);
		G = new Giocatore();
		B = new Banco();
		point = new JLabel("Cassa: " + String.valueOf(G.getCassa()));
		point.setBounds(275, 50, 200, 50);
		this.add(point);
		punt = new JLabel("Puntata: 0");
		punt.setBounds(375, 50, 200, 50);
		this.add(punt);
		carteBanco = new JLabel("");
		carteBanco.setBounds(375, 150, 200, 50);
		this.add(carteBanco);
		carteBanco = new JLabel("");
		carteBanco.setBounds(375, 150, 200, 50);
		this.add(carteBanco);
		punteggio_G = new JLabel("Punteggio: 0");
		punteggio_G.setBounds(275, 200, 200, 50);
		this.add(punteggio_G);
		punteggio_B = new JLabel("");
		punteggio_B.setBounds(375, 200, 200, 50);
		this.add(punteggio_B);
		vedo.addActionListener(this);
		carta.addActionListener(this);
	}
	
	public int getPuntata() {
		return this.puntata;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//Carta
		if(e.getActionCommand().equals("Carta")) {
			int p;
			punteggio_G.setText("Punteggio: " + String.valueOf(this.G.getPunteggio()));
			p = Integer.valueOf(JOptionPane.showInputDialog
					(null, "Devi puntare!",
					"Puntata", 
					JOptionPane.QUESTION_MESSAGE));
			this.puntata += p;
			this.G.setPuntata(p);
			point.setText("Cassa: " + String.valueOf(this.G.getCassa()));
			punt.setText("Puntata: " + String.valueOf(this.puntata));
			Random r = new Random();
			int x = r.nextInt(13);
			B.addCarta(x);
			int y = r.nextInt(13);
			G.addCarta(y);
			this.punteggio_G.setText("Punteggio: " + this.G.getPunteggio());
			if(y<11) {
				if(y==0)
					this.text.setText(this.text.getText() + " " + 'A');
				else this.text.setText(this.text.getText() + " " + y);
			}
			else {
				switch(y) {
				case 11:
					this.text.setText(this.text.getText() + " " + 'J');
					break;
				case 12:
					this.text.setText(this.text.getText() + " " + 'Q');
					break;
				case 13:
					this.text.setText(this.text.getText() + " " + 'K');
					break;
				}
			}
		}
		
		//Vedo
		if(e.getActionCommand().equals("Vedo")) {
			this.punteggioB = B.getPunteggio();
			this.punteggioG = G.getPunteggio();
			int scelta = 0;
			String [] opzioni = {"Continua", "Non ho più soldi"};
			carteBanco.setText("Carte Banco: " + this.B.getCarte());
			punteggio_B.setText("Punteggio Banco: " + this.B.getPunteggio());
			if(this.punteggioB > 21 && this.punteggioG <= 21) {
				//messaggio vincita giocatore
				scelta = JOptionPane.showOptionDialog
					(null, "Hai vinto!", "Continuazione", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);
				this.vittoria = true;
			}
			else if(this.punteggioG > 21) {
				//messaggio vincita banco
				scelta = JOptionPane.showOptionDialog
					(null, "Hai perso!", "Continuazione", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);
			}
			else if(this.punteggioB >= this.punteggioG) {
				//messaggio vincita banco
				scelta = JOptionPane.showOptionDialog
					(null, "Hai perso!", "Continuazione", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);
			}
			else if(this.punteggioB < this.punteggioG) {
				//messaggio vincita giocatore
				scelta = JOptionPane.showOptionDialog
				(null, "Hai vinto!", "Continuazione", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);
				this.vittoria = true;
			}
			
			if(vittoria) {
				JOptionPane.showOptionDialog
				(null, "Ti spetta il doppio della puntata, ovvero: " + this.puntata*2,
						null, JOptionPane.CLOSED_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				this.G.setCassa(this.puntata*2);
				vittoria = false;
			}
			if(scelta != 0) {
				System.exit(ABORT);
			}
			this.text.setText("Carte: ");
			this.punt.setText("Puntata: 0");
			this.carteBanco.setText("");
			this.point.setText("Cassa: " + this.G.getCassa());
			this.puntata = 0;
			this.punteggio_G.setText("Punteggio: 0");
			this.punteggio_B.setText("");
			this.B.setNuovaMano();
			this.G.setNuovaMano();
		}
	}
}
