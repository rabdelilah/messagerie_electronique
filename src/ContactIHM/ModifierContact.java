package ContactIHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mailing.Mail;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import DAO.Client;
import DAO.ContactDAO;
import beans.Contact;
import beans.demande;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ModifierContact extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblEmail;
	private JComboBox cbxEmail;
	private JLabel lblNom;
	private JTextField txtNom;
	private JLabel lblPrenom;
	private JTextField txtPrenom;
	private JButton btnSave;
	private JButton btnµSupprimer;
	Vector<Contact> v=new Vector<Contact>();
	private JLabel lblPass;
	private JTextField txtPass;
	private JLabel lblCota;
	private JTextField txtCota;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public ModifierContact() {
		initComponents();
		laod();
	}
	private void laod(){
		setLocation(200, 100);
	}
	private void initComponents() {
		setTitle("Modifier Contact");
		setLayout(new GroupLayout());
		add(getLblEmail(), new Constraints(new Leading(19, 10, 10), new Leading(23, 10, 10)));
		add(getCbxEmail(), new Constraints(new Leading(139, 169, 10, 10), new Leading(23, 21, 10, 10)));
		add(getTxtNom(), new Constraints(new Leading(139, 168, 12, 12), new Leading(53, 10, 10)));
		add(getTxtPrenom(), new Constraints(new Leading(139, 168, 12, 12), new Leading(82, 10, 10)));
		add(getLblNom(), new Constraints(new Leading(18, 46, 12, 12), new Leading(55, 12, 12)));
		add(getLblPrenom(), new Constraints(new Leading(18, 12, 12), new Leading(83, 12, 12)));
		add(getLblPass(), new Constraints(new Leading(19, 12, 12), new Leading(114, 12, 12)));
		add(getTxtPass(), new Constraints(new Leading(139, 168, 12, 12), new Leading(111, 12, 12)));
		add(getTxtCota(), new Constraints(new Leading(139, 168, 12, 12), new Leading(141, 12, 12)));
		add(getLblCota(), new Constraints(new Leading(19, 12, 12), new Leading(143, 12, 12)));
		add(getBtnµSupprimer(), new Constraints(new Trailing(12, 96, 288, 288), new Leading(190, 18, 10, 10)));
		add(getBtnSave(), new Constraints(new Leading(181, 10, 10), new Leading(190, 18, 12, 12)));
		setSize(418, 220);
	}
	private JTextField getTxtCota() {
		if (txtCota == null) {
			txtCota = new JTextField();
		}
		return txtCota;
	}
	private JLabel getLblCota() {
		if (lblCota == null) {
			lblCota = new JLabel();
			lblCota.setText("Cota Disk");
		}
		return lblCota;
	}
	private JTextField getTxtPass() {
		if (txtPass == null) {
			txtPass = new JTextField();
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
	private JButton getBtnµSupprimer() {
		if (btnµSupprimer == null) {
			btnµSupprimer = new JButton();
			btnµSupprimer.setText("Supprimer");
			btnµSupprimer.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnµSupprimerActionActionPerformed(event);
				}
			});
		}
		return btnµSupprimer;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton();
			btnSave.setText("Enregistrer");
			btnSave.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnSaveActionActionPerformed(event);
				}
			});
		}
		return btnSave;
	}

	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
		}
		return txtPrenom;
	}

	private JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel();
			lblPrenom.setText("Prenom");
		}
		return lblPrenom;
	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
		}
		return txtNom;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel();
			lblNom.setText("Nom");
		}
		return lblNom;
	}
	
	private JComboBox getCbxEmail() {
		if (cbxEmail == null) {
			cbxEmail = new JComboBox(ContactDAO.listeContact());
			cbxEmail.setDoubleBuffered(false);
			cbxEmail.setBorder(null);
			cbxEmail.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					cbxEmailActionActionPerformed(event);
				}
			});
		}
		return cbxEmail;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel();
			lblEmail.setText("Email");
		}
		return lblEmail;
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
				ModifierContact frame = new ModifierContact();
				frame.setDefaultCloseOperation(ModifierContact.EXIT_ON_CLOSE);
				frame.setTitle("ModifierContact");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void btnSaveActionActionPerformed(ActionEvent event) {
		Contact c=new Contact(getTxtNom().getText(),getTxtPrenom().getText(),getCbxEmail().getSelectedItem().toString(),getTxtPass().getText(),Integer.parseInt(getTxtCota().getText()));
		Client.Lire(new demande("maj",c));
		ModifierContact.this.dispose();
	}

	private void btnµSupprimerActionActionPerformed(ActionEvent event) {
		Contact c=new Contact(getTxtNom().getText(),getTxtPrenom().getText(),getCbxEmail().getSelectedItem().toString(),getTxtPass().getText(),Integer.parseInt(getTxtCota().getText()));
		for(int i=0;i<Client.contact.size();i++){
			 if(Client.contact.get(i).getMail().equals(getCbxEmail().getSelectedItem().toString())){
				 Client.contact.remove(i);
				 Mail.actualiserListeContacte();
				 break;
			 }
		 }
		Client.Lire(new demande("supp",c));
		ModifierContact.this.dispose();
	}

	private void cbxEmailActionActionPerformed(ActionEvent event) {
		for (Contact c:Client.contact){
			if(c.getMail().equals(getCbxEmail().getSelectedItem().toString())){
				getTxtNom().setText(c.getNom());
				getTxtPrenom().setText(c.getPrenom());
				getTxtCota().setText(String.valueOf(c.getCota()));
				getTxtPass().setText(getTxtPass().getText());
				break;
			}
		}	
		Contact contact=new Contact(getTxtNom().getText(),getTxtPrenom().getText(),getCbxEmail().getSelectedItem().toString(),getTxtPass().getText(),Integer.parseInt(getTxtCota().getText()));
		if(v.size()==0){
			v.add(contact);
		}else{
			v.set(0, contact);		
		}
	}

}
