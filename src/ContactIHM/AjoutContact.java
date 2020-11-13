package ContactIHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mailing.Mail;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import DAO.Client;
import DAO.ContactDAO;
import beans.Contact;
import beans.demande;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AjoutContact extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtEmail;
	private JLabel lblPrenom;
	private JLabel lblemail;
	private JLabel lblNom;
	private JButton btnAjout;
	private JLabel lblPass;
	private JPasswordField txtPass;
	private JTextField txtCota;
	private JLabel lblCota;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AjoutContact() {
		initComponents();
		laod();
	}
	private void laod(){
		if(Client.contact==null){
			Client.contact=new Vector<Contact>();
		}
		setLocation(200, 100);
		pack();
	}
	private void initComponents() {
		setTitle("Ajout Contact");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getTxtNom(), new Constraints(new Leading(109, 157, 10, 10), new Leading(14, 12, 12)));
		add(getTxtPrenom(), new Constraints(new Leading(109, 156, 12, 12), new Leading(42, 12, 12)));
		add(getLblNom(), new Constraints(new Leading(12, 12, 12), new Leading(14, 12, 12)));
		add(getTxtEmail(), new Constraints(new Leading(109, 156, 12, 12), new Leading(68, 12, 12)));
		add(getLblPrenom(), new Constraints(new Leading(12, 12, 12), new Leading(46, 12, 12)));
		add(getLblemail(), new Constraints(new Leading(12, 12, 12), new Leading(70, 12, 12)));
		add(getLblPass(), new Constraints(new Leading(12, 12, 12), new Leading(100, 12, 12)));
		add(getTxtPass(), new Constraints(new Leading(109, 156, 12, 12), new Leading(100, 12, 12)));
		add(getTxtCota(), new Constraints(new Leading(109, 156, 12, 12), new Leading(132, 12, 12)));
		add(getLblCota(), new Constraints(new Leading(12, 12, 12), new Leading(134, 12, 12)));
		add(getBtnAjout(), new Constraints(new Leading(232, 127, 10, 10), new Leading(170, 19, 12, 12)));
		setSize(406, 238);
	}
	private JLabel getLblCota() {
		if (lblCota == null) {
			lblCota = new JLabel();
			lblCota.setText("Cota Diske");
		}
		return lblCota;
	}
	private JTextField getTxtCota() {
		if (txtCota == null) {
			txtCota = new JTextField();
		}
		return txtCota;
	}
	private JPasswordField getTxtPass() {
		if (txtPass == null) {
			txtPass = new JPasswordField();
		}
		return txtPass;
	}
	private JLabel getLblPass() {
		if (lblPass == null) {
			lblPass = new JLabel();
			lblPass.setText("Mot De Passe");
		}
		return lblPass;
	}
	private JButton getBtnAjout() {
		if (btnAjout == null) {
			btnAjout = new JButton();
			btnAjout.setText("Ajouter");
			btnAjout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					btnAjoutActionActionPerformed(event);
				}
			});
		}
		return btnAjout;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel();
			lblNom.setText("Nom");
		}
		return lblNom;
	}

	private JLabel getLblemail() {
		if (lblemail == null) {
			lblemail = new JLabel();
			lblemail.setText("E-Mail");
		}
		return lblemail;
	}

	private JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel();
			lblPrenom.setText("Prenom");
		}
		return lblPrenom;
	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
		}
		return txtEmail;
	}

	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
		}
		return txtPrenom;
	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
		}
		return txtNom;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL+ " on this platform:" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				AjoutContact frame = new AjoutContact();
				frame.setDefaultCloseOperation(AjoutContact.EXIT_ON_CLOSE);
				frame.setTitle("AjoutContact");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	private void btnAjoutActionActionPerformed(ActionEvent event) {
		Vector<Contact> v=new Vector<Contact>();
		Contact c=new Contact(getTxtNom().getText(),getTxtPrenom().getText(),getTxtEmail().getText(),getTxtPass().getText(),Integer.parseInt(getTxtCota().getText()));
		if(!ContactDAO.verifExistContact(c)){
			Client.contact.add(c);
			Client.Lire(new demande("Ajout",c));
			Mail.actualiserListeContacte();
			AjoutContact.this.dispose();
		}else{
			JOptionPane.showMessageDialog(AjoutContact.this, "Contact exist déja","Message",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
