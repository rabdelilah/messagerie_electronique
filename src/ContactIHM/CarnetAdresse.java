package ContactIHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mailing.NouveauMail;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import DAO.Client;
import DAO.ContactDAO;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CarnetAdresse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JList jListCarnetAdresse;
	private JScrollPane jScrollPane0;
	private JButton btnAdd;
	private JButton btnQuitter;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public CarnetAdresse() {
		initComponents();
		laod();
	}
	private void laod(){
		setLocation(200, 100);
	}
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Leading(6, 247, 10, 10), new Leading(4, 220, 10, 10)));
		add(getBtnQuitter(), new Constraints(new Leading(172, 12, 12), new Leading(230, 18, 12, 12)));
		add(getBtnAdd(), new Constraints(new Leading(59, 12, 12), new Leading(230, 18, 12, 12)));
		setSize(276, 288);
	}

	private JButton getBtnQuitter() {
		if (btnQuitter == null) {
			btnQuitter = new JButton();
			btnQuitter.setText("Quitter");
			btnQuitter.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnQuitterActionActionPerformed(event);
				}
			});
		}
		return btnQuitter;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton();
			btnAdd.setText("Selectionne");
			btnAdd.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnAddActionActionPerformed(event);
				}
			});
		}
		return btnAdd;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJListCarnetAdresse());
		}
		return jScrollPane0;
	}

	private JList getJListCarnetAdresse() {
		if (jListCarnetAdresse == null) {
			jListCarnetAdresse = new JList(ContactDAO.listeContact());
		}
		return jListCarnetAdresse;
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
				CarnetAdresse frame = new CarnetAdresse();
				frame.setDefaultCloseOperation(CarnetAdresse.EXIT_ON_CLOSE);
				frame.setTitle("CarnetAdresse");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	private void btnAddActionActionPerformed(ActionEvent event) {
		String s=getJListCarnetAdresse().getSelectedValue().toString();
		if(Client.appelCarnetAdress.equals("a")){
			if(NouveauMail.getTxtA().getText().equals("")){
				NouveauMail.getTxtA().setText(s);
			}else{
				NouveauMail.getTxtA().setText(NouveauMail.getTxtA().getText()+";"+s);
			}
		}else{
			if(NouveauMail.getTxtCc().getText().equals("")){
				NouveauMail.getTxtCc().setText(s);
			}else{
				NouveauMail.getTxtCc().setText(NouveauMail.getTxtCc().getText()+";"+s);
			}
		}
	}

	private void btnQuitterActionActionPerformed(ActionEvent event) {
		CarnetAdresse.this.dispose();
	}

}
