package ContactIHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mailing.Mail;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import DAO.Client;
import beans.Relationcontacte;
import beans.demande;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Contact extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblEmail;
	private JButton btnSave;
	Vector<Contact> v=new Vector<Contact>();
	private JTextField jTextField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public Contact() {
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
		add(getBtnSave(), new Constraints(new Leading(333, 10, 10), new Leading(54, 20, 10, 10)));
		add(getJTextField0(), new Constraints(new Leading(129, 179, 10, 10), new Leading(21, 12, 12)));
		setSize(418, 88);
		pack();
	}
	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton();
			btnSave.setText("Add");
			btnSave.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnSaveActionActionPerformed(event);
				}
			});
		}
		return btnSave;
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
				Contact frame = new Contact();
				frame.setDefaultCloseOperation(Contact.EXIT_ON_CLOSE);
				frame.setTitle("ModifierContact");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void btnSaveActionActionPerformed(ActionEvent event) {
		Relationcontacte r=new Relationcontacte();
		r.setNom(Client.utilisateur.getMail());r.setListe(getJTextField0().getText());
		Client.Lire(new demande("Ajout",r));
		Client.contact.add(new beans.Contact(getJTextField0().getText()));
		Mail.actualiserListeContacte();
	}
}
