package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mailing.Mail;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import beans.Contact;
import beans.Message;
import beans.demande;

import DAO.Client;
import DAO.ContactDAO;
import DAO.DataDAO4;


//VS4E -- DO NOT REMOVE THIS LINE!
public class Identification extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblHste;
	private JLabel lblNom;
	private JLabel lblPwd;
	private JButton btnConnecte;
	private JTextField txtAdrr;
	private JTextField jTextField1;
	private JTextField txtPwd;
	private JTextField txtUser;
	//public static Map<String, Object> mail=null;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	
	public Identification() {
		initComponents();
		try {
			laod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void laod() throws Exception{
		setLocation(200, 100);
		Client.socket = new Socket(Client.host, 1900);
		Client.oos = new ObjectOutputStream(Client.socket.getOutputStream());
		Client.ois = new ObjectInputStream(Client.socket.getInputStream());
	}
	private void initComponents() {
		setTitle("Se Connecte");
		setVisible(true);
		setLayout(new GroupLayout());
		add(getLblHste(), new Constraints(new Leading(14, 10, 10), new Leading(19, 10, 10)));
		add(getLblNom(), new Constraints(new Leading(14, 12, 12), new Leading(45, 12, 12)));
		add(getLblPwd(), new Constraints(new Leading(14, 12, 12), new Leading(73, 12, 12)));
		add(getTxtAdrr(), new Constraints(new Leading(150, 171, 10, 10), new Leading(15, 12, 12)));
		add(getTxtUser(), new Constraints(new Leading(150, 170, 12, 12), new Leading(41, 12, 12)));
		add(getTxtPwd(), new Constraints(new Leading(150, 170, 12, 12), new Leading(69, 12, 12)));
		add(getBtnConnecte(), new Constraints(new Leading(233, 138, 10, 10), new Leading(107, 22, 10, 10)));
		setSize(397, 167);
	}
	private JTextField getTxtUser() {
		if (txtUser == null) {
			txtUser = new JTextField("hasnaa@rs.com");
		}
		return txtUser;
	}

	private JTextField getTxtPwd() {
		if (txtPwd == null) {
			txtPwd = new JTextField("123");
		}
		return txtPwd;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JTextField getTxtAdrr() {
		if (txtAdrr == null) {
			txtAdrr = new JTextField("localhost");
		}
		return txtAdrr;
	}

	private JButton getBtnConnecte() {
		if (btnConnecte == null) {
			btnConnecte = new JButton();
			btnConnecte.setIcon(new ImageIcon(getClass().getResource("/register_pass[1].gif")));
			btnConnecte.setText("Se Connecte");
			btnConnecte.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnConnecteActionActionPerformed(event);
				}
			});
		}
		return btnConnecte;
	}

	private JLabel getLblPwd() {
		if (lblPwd == null) {
			lblPwd = new JLabel();
			lblPwd.setText("Mot de Passe");
		}
		return lblPwd;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel();
			lblNom.setText("Utilisateur");
		}
		return lblNom;
	}

	private JLabel getLblHste() {
		if (lblHste == null) {
			lblHste = new JLabel();
			lblHste.setText("Adresse Server");
		}
		return lblHste;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL + " on this platform:" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new Identification();
				frame.setBounds(10, 10, 320, 240);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Identification");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void btnConnecteActionActionPerformed(ActionEvent event) {
		Client.email=getTxtUser().getText();
		try {
			   Client.Lire(new demande("identification",getTxtUser().getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
		}
		Contact c=Client.utilisateur;
		if(c.getMail().equalsIgnoreCase(getTxtUser().getText()) 
		&& c.getPass().equalsIgnoreCase(getTxtPwd().getText())){
			mailing.Mail mail=new mailing.Mail();
			mail.setTitle(Client.email);
			mail.setVisible(true);
			Identification.this.setVisible(false);
	}
	}

}
