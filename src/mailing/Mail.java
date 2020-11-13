package mailing;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;

import beans.Contact;
import beans.demande;

import ContactIHM.AjoutContact;
import ContactIHM.ModifierContact;
import DAO.Client;
import DAO.DataDAO4;
import DAO.MsgDAO;


//VS4E -- DO NOT REMOVE THIS LINE!
public class Mail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuItem MenuAjoutContact;
	private JMenuItem MenuNewMail;
	private JMenu MenuFichier;
	private JMenuBar jMenuBar0;
	private JSplitPane jSplitPaneGauche;
	private JSplitPane jSplitPaneDroit;
	private JSplitPane jSplitPanePrincipale;
	private static JTable jTableAfficheMsg;
	private static JScrollPane jScrollPaneAfficheMsg;
	private static JEditorPane jEditorPaneMsg;
	private JScrollPane jScrollPaneMsg;
	private static JTree jTreeEnvoieReception;
	private static JTree jTreeListeContact;
	static String dest="";
	static String[][] ListeRecevoire=null;
	static String[][] listeEnvoyer=null;
	private JMenuItem MenuModifierContact;
	private JMenu jMenuContact;
	private JMenuItem jMenuItemRecevoir;
	private JMenu jMenuOutils;
	private JMenuItem jMenuItemEnvoierRecevoireTout;
	private JMenu jMenuEnvoieRecevoire;
	private JMenuItem jMenuItemEnvoyer;
	static JPopupMenu jpopupmenu = new JPopupMenu("------------");
	static Point p=new Point();
	static String aff="";
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public Mail() {
		//laod();
		initComponents();
	}
	private void laod(){
		
		setLocation(200, 100);
		MsgDAO.listeMsgRecevoire();
		System.out.println(MsgDAO.nbrNonLu);
		addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent arg0) {
			System.exit(0);
		}
		});
	}
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJSplitPanePrincipale(), new Constraints(new Bilateral(0, 0, 252), new Bilateral(0, 0, 66)));
		setJMenuBar(getJMenuBar0());
		setSize(864, 550);
	}

	private JMenuItem getJMenuItemEnvoyer() {
		if (jMenuItemEnvoyer == null) {
			jMenuItemEnvoyer = new JMenuItem();
			jMenuItemEnvoyer.setText("Envoyer");
			jMenuItemEnvoyer.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jMenuItemEnvoyerActionActionPerformed(event);
				}
			});
		}
		return jMenuItemEnvoyer;
	}
	private JMenu getJMenuEnvoieRecevoire() {
		if (jMenuEnvoieRecevoire == null) {
			jMenuEnvoieRecevoire = new JMenu();
			jMenuEnvoieRecevoire.setText("Envoyer et Recevoir");
			jMenuEnvoieRecevoire.add(getJMenuItemEnvoierRecevoireTout());
		}
		return jMenuEnvoieRecevoire;
	}
	private JMenuItem getJMenuItemEnvoierRecevoireTout() {
		if (jMenuItemEnvoierRecevoireTout == null) {
			jMenuItemEnvoierRecevoireTout = new JMenuItem();
			jMenuItemEnvoierRecevoireTout.setText("Envoyer Recevoir Tous");
			jMenuItemEnvoierRecevoireTout.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jMenuItemEnvoierRecevoireToutActionActionPerformed(event);
				}
			});
		}
		return jMenuItemEnvoierRecevoireTout;
	}
	private JMenu getJMenuOutils() {
		if (jMenuOutils == null) {
			jMenuOutils = new JMenu();
			jMenuOutils.setText("Outils");
			jMenuOutils.add(getJMenuEnvoieRecevoire());
			jMenuOutils.add(getJMenuItemEnvoyer());
			jMenuOutils.add(getJMenuItemRecevoir());
		}
		return jMenuOutils;
	}
	private JMenuItem getJMenuItemRecevoir() {
		if (jMenuItemRecevoir == null) {
			jMenuItemRecevoir = new JMenuItem();
			jMenuItemRecevoir.setText("Recevoir");
			jMenuItemRecevoir.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jMenuItemRecevoirActionActionPerformed(event);
				}
			});
		}
		return jMenuItemRecevoir;
	}
	private JMenu getJMenuContact() {
		if (jMenuContact == null) {
			jMenuContact = new JMenu();
			jMenuContact.setText("Carnet Adresse");
			jMenuContact.setOpaque(false);
			jMenuContact.add(getMenuAjoutContact());
			jMenuContact.add(getMenuModifierContact());
		}
		return jMenuContact;
	}
	private JMenuItem getMenuModifierContact() {
		if (MenuModifierContact == null) {
			MenuModifierContact = new JMenuItem();
			MenuModifierContact.setText("Modifier Contact");
			MenuModifierContact.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					MenuModifierContactActionActionPerformed(event);
				}
			});
		}
		return MenuModifierContact;
	}
	private JTree getJTreeListeContact() {
		if (jTreeListeContact == null) {
			jTreeListeContact = new JTree();
			actualiserListeContacte();
		}
		return jTreeListeContact;
	}
	
	public static void actualiserListeContacte(){
		DefaultMutableTreeNode listeContact = new DefaultMutableTreeNode("Contactes");
		Vector<Contact> v=Client.contact;
		if(v!=null)
		for(String c:Client.ListeRelation){
			DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(c);
			listeContact.add(node2);
		}
		DefaultTreeModel treeModel = null;
		treeModel = new DefaultTreeModel(listeContact);
		jTreeListeContact.setModel(treeModel);
		jTreeListeContact.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
			  dest=event.getPath().toString().split(",")[1].replace("]", "").replace(" ", "");
			  NouveauMail nm=new NouveauMail();
			  nm.setTitle("Nouveau Message Vert "+dest);
			  NouveauMail.getTxtA().setText(dest);
			  nm.setVisible(true);
			}
		});	
	}
	public static void actualiseAffRecevoir(){
		  String t[][]=MsgDAO.listeMsgRecevoire();
		  jTableAfficheMsg.setDefaultRenderer(Object.class, new EvenOddRenderer());
		  Client.MsgNonLu=MsgDAO.MsgNonLu();
		  getJTableAfficheMsg().setModel(new DefaultTableModel(t,MsgDAO.TitrelisteMsgRecevoir()));
	}
	public static JTree getJTreeEnvoieReception() {
			jTreeEnvoieReception = new JTree();
			DefaultTreeModel treeModel = null;
			{
				DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("JMail Express");
				{
					DefaultMutableTreeNode nodeReception = new DefaultMutableTreeNode("Boite de reception ");
					node0.add(nodeReception);
				}
				{
					DefaultMutableTreeNode nodeEnvoie = new DefaultMutableTreeNode("Boite envoie");
					node0.add(nodeEnvoie);
				}
				
				treeModel = new DefaultTreeModel(node0); 
				
				jTreeEnvoieReception.addTreeSelectionListener(new TreeSelectionListener() {
					public void valueChanged(TreeSelectionEvent event) {
					 if( event.getPath().toString().split(",")[1].replace("]", "").equals(" Boite envoie")){
						 aff="envoie";
						 jTableAfficheMsg.setDefaultRenderer(Object.class, new Renderer());
						 getJTableAfficheMsg().setModel(new DefaultTableModel(MsgDAO.listeMsgEnvoyer(),MsgDAO.TitrelisteMsg())); 
						}else{
							aff="reception";
						 actualiseAffRecevoir();
					}
					}
				});	
			}
			jTreeEnvoieReception.setModel(treeModel);
		
		return jTreeEnvoieReception;
	}

	private JScrollPane getJScrollPaneMsg() {
		if (jScrollPaneMsg == null) {
			jScrollPaneMsg = new JScrollPane();
			jScrollPaneMsg.setViewportView(getJEditorPaneMsg());
		}
		return jScrollPaneMsg;
	}

	public static JEditorPane getJEditorPaneMsg() {
		if (jEditorPaneMsg == null) {
			jEditorPaneMsg = new JEditorPane();
			jEditorPaneMsg.setEditable(false);
		}
		return jEditorPaneMsg;
	}

	private JScrollPane getJScrollPaneAfficheMsg() {
		if (jScrollPaneAfficheMsg == null) {
			jScrollPaneAfficheMsg = new JScrollPane();
			jScrollPaneAfficheMsg.setViewportView(getJTableAfficheMsg());
		}
		return jScrollPaneAfficheMsg;
	}


	public static JTable getJTableAfficheMsg() {
		if (jTableAfficheMsg == null) {
			jTableAfficheMsg = new JTable();
			jTableAfficheMsg.setAutoCreateRowSorter(true);
			jTableAfficheMsg.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					if(arg0.getButton()==3){
						
					}else if(arg0.getButton()==1){
						p=arg0.getLocationOnScreen();
						jTableAfficheMsgMouseMouseClicked(arg0);
					}
				}
			});
		}
		return jTableAfficheMsg;
	}
	private JSplitPane getJSplitPanePrincipale() {
		if (jSplitPanePrincipale == null) {
			jSplitPanePrincipale = new JSplitPane();
			jSplitPanePrincipale.setDividerLocation(89);
			jSplitPanePrincipale.setLeftComponent(getJSplitPaneGauche());
			jSplitPanePrincipale.setRightComponent(getJSplitPaneDroit());
		}
		return jSplitPanePrincipale;
	}

	private JSplitPane getJSplitPaneDroit() {
		if (jSplitPaneDroit == null) {
			jSplitPaneDroit = new JSplitPane();
			jSplitPaneDroit.setDividerLocation(250);
			jSplitPaneDroit.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPaneDroit.setTopComponent(getJScrollPaneAfficheMsg());
			jSplitPaneDroit.setBottomComponent(getJScrollPaneMsg());
		}
		return jSplitPaneDroit;
	}

	private JSplitPane getJSplitPaneGauche() {
		if (jSplitPaneGauche == null) {
			jSplitPaneGauche = new JSplitPane();
			jSplitPaneGauche.setDividerLocation(250);
			jSplitPaneGauche.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPaneGauche.setTopComponent(getJTreeEnvoieReception());
			jSplitPaneGauche.setBottomComponent(getJTreeListeContact());
		}
		return jSplitPaneGauche;
	}

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getMenuFichier());
			jMenuBar0.add(getJMenuContact());
			jMenuBar0.add(getJMenuOutils());
		}
		return jMenuBar0;
	}
	private JMenu getMenuFichier() {
		if (MenuFichier == null) {
			MenuFichier = new JMenu();
			MenuFichier.setText("Fichier");
			MenuFichier.setOpaque(false);
			MenuFichier.add(getMenuNewMail());
		}
		return MenuFichier;
	}
	private JMenuItem getMenuNewMail() {
		if (MenuNewMail == null) {
			MenuNewMail = new JMenuItem();
			MenuNewMail.setText("Nouveaux Email");
			MenuNewMail.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					MenuDeconnecteActionActionPerformed(event);
				}
			});
		}
		return MenuNewMail;
	}

	private JMenuItem getMenuAjoutContact() {
		if (MenuAjoutContact == null) {
			MenuAjoutContact = new JMenuItem();
			MenuAjoutContact.setText("Ajout Contact");
			MenuAjoutContact.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent arg0) {
					ContactIHM.Contact a=new ContactIHM.Contact();
					a.setVisible(true);
				}
			});
		}
		return MenuAjoutContact;
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
				Mail frame = new Mail();
				frame.setDefaultCloseOperation(Mail.EXIT_ON_CLOSE);
				frame.setTitle("Mail");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void MenuDeconnecteActionActionPerformed(ActionEvent event) {
		NouveauMail n=new NouveauMail();
		n.setVisible(true);
	}
	private void MenuModifierContactActionActionPerformed(ActionEvent event) {
		ModifierContact maj=new ModifierContact();
		maj.setVisible(true);
	}
	private void jMenuItemEnvoierRecevoireToutActionActionPerformed(ActionEvent event) {
		   Client.Lire(new demande("identification",Client.email));
		   getJTableAfficheMsg().setModel(new DefaultTableModel(MsgDAO.listeMsgEnvoyer(),MsgDAO.TitrelisteMsg()));
		   actualiseAffRecevoir();
	}
	private void jMenuItemEnvoyerActionActionPerformed(ActionEvent event) {
		   Client.Lire(new demande("identification",Client.email));
		   getJTableAfficheMsg().setModel(new DefaultTableModel(MsgDAO.listeMsgEnvoyer(),MsgDAO.TitrelisteMsg()));
	}
	private void jMenuItemRecevoirActionActionPerformed(ActionEvent event) {
		   Client.Lire(new demande("identification",Client.email));
		   actualiseAffRecevoir();
	}
	private static void jTableAfficheMsgMouseMouseClicked(MouseEvent event) {
		getJEditorPaneMsg().setText(getJTableAfficheMsg().getValueAt(getJTableAfficheMsg().getSelectedRow(), 3).toString());
	}

}
