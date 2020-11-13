package mailing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import beans.Contact;
import beans.Message;
import beans.demande;

import ContactIHM.CarnetAdresse;
import DAO.Client;


//VS4E -- DO NOT REMOVE THIS LINE!
public class NouveauMail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnEnvoie;
	private JLabel lblA;
	private JLabel lblCc;
	private JLabel lblObjet;
	private static JTextField txtCc;
	private JTextField txtObjet;
	private static JTextField txtA;
	private JTextPane txtMsg;
	private JScrollPane jScrollPane0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public NouveauMail() {
		initComponents();
		laod();
	}
	private void vider(){
		getTxtA().setText("");
		getTxtCc().setText("");
		getTxtMsg().setText("");
		getTxtObjet().setText("");
	}
	private void laod(){
		vider();
		setLocation(200, 100);
	}
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBtnEnvoie(), new Constraints(new Leading(4, 10, 10), new Leading(3, 24, 10, 10)));
		add(getLblA(), new Constraints(new Leading(7, 78, 12, 12), new Leading(39, 12, 12)));
		add(getLblCc(), new Constraints(new Leading(7, 74, 12, 12), new Leading(63, 10, 10)));
		add(getLblObjet(), new Constraints(new Leading(7, 74, 12, 12), new Leading(91, 12, 12)));
		add(getTxtCc(), new Constraints(new Bilateral(103, 0, 4), new Leading(67, 12, 12)));
		add(getTxtObjet(), new Constraints(new Bilateral(103, 0, 4), new Leading(91, 12, 12)));
		add(getTxtA(), new Constraints(new Bilateral(103, 0, 4), new Leading(39, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Bilateral(4, 0, 22), new Bilateral(113, 0, 22)));
		setSize(797, 392);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getTxtMsg());
		}
		return jScrollPane0;
	}

	private JTextPane getTxtMsg() {
		if (txtMsg == null) {
			txtMsg = new JTextPane();
		}
		return txtMsg;
	}

	public static JTextField getTxtA() {
		if (txtA == null) {
			txtA = new JTextField();
		}
		return txtA;
	}

	private JTextField getTxtObjet() {
		if (txtObjet == null) {
			txtObjet = new JTextField();
		}
		return txtObjet;
	}

	public static JTextField getTxtCc() {
		if (txtCc == null) {
			txtCc = new JTextField();
		}
		return txtCc;
	}

	private JLabel getLblObjet() {
		if (lblObjet == null) {
			lblObjet = new JLabel();
			lblObjet.setIcon(new ImageIcon(getClass().getResource("/EditContact[1].gif")));
			lblObjet.setText("Objet");
		}
		return lblObjet;
	}

	private JLabel getLblCc() {
		if (lblCc == null) {
			lblCc = new JLabel();
			lblCc.setIcon(new ImageIcon(getClass().getResource("/book_open[1].gif")));
			lblCc.setText("Cc");
			lblCc.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					lblCcMouseMouseClicked(event);
				}
			});
		}
		return lblCc;
	}
	private JLabel getLblA() {
		if (lblA == null) {
			lblA = new JLabel();
			lblA.setIcon(new ImageIcon(getClass().getResource("/book_open[1].gif")));
			lblA.setText("A");
			lblA.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					lblAMouseMouseClicked(event);
				}
			});
		}
		return lblA;
	}
	private JButton getBtnEnvoie() {
		if (btnEnvoie == null) {
			btnEnvoie = new JButton();
			btnEnvoie.setText("Envoyer");
			btnEnvoie.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnEnvoieActionActionPerformed(event);
				}
			});
		}
		return btnEnvoie;
	}
	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NouveauMail frame = new NouveauMail();
				frame.setDefaultCloseOperation(NouveauMail.EXIT_ON_CLOSE);
				frame.setTitle("NouveauMail");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	private void btnEnvoieActionActionPerformed(ActionEvent event) {
		String dest=getTxtA().getText()+";"+getTxtCc().getText();
		String t[]=dest.split(";");
		Date d=new Date();
		ArrayList<Message> data=new ArrayList<Message>();
		for(int i=0;i<t.length;i++){
			data.add(new Message(Client.email,t[i],new java.sql.Timestamp(d.getTime()),getTxtObjet().getText(),getTxtMsg().getText(),(short)0));
		}
		Client.data.addAll(data);
		Client.Lire(new demande("ajoutMsgs",data));
		NouveauMail.this.dispose();
	}
	private void lblAMouseMouseClicked(MouseEvent event) {
		Client.appelCarnetAdress="a";
		CarnetAdresse ca=new CarnetAdresse();
		ca.setVisible(true);
	}
	private void lblCcMouseMouseClicked(MouseEvent event) {
		Client.appelCarnetAdress="cc";
		CarnetAdresse ca=new CarnetAdresse();
		ca.setVisible(true);
	}
	private void validation(){
		String tA[]=getTxtA().getText().split(";");
		String tCc[]=getTxtCc().getText().split(";");
		boolean valide=true;
		for(String s:tA){
			if(s.indexOf("@")==-1 && s.indexOf(".")==-1){
				//JOptionPane.
				valide=false;
			}
		}
		for(String s:tCc){
			if(s.indexOf("@")==-1 && s.indexOf(".")==-1){
				valide=false;
			}
		}
	}

}
