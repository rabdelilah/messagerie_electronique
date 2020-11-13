package DAO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import beans.Contact;
import beans.Message;
import beans.demande;
import beans.reponse;


public class Client {
	public static String host="localhost";
	public static ObjectInputStream ois=null;
	public static ObjectOutputStream oos=null;
	public static Socket socket=null;
	public static String email="";
	public static int nbrNouveauxMsg=0;
	public static Vector<beans.Message> data=null;
	public static  Vector<Contact> contact=null;
	public static Contact utilisateur=null;
	public static  Map<String,Object> mapServer=null;
	public static Vector<Contact> user=null;
	public static Vector<String> ListeRelation=null;
	public static Message dataServer=null;
	public static String appelCarnetAdress="";
	public static Map<Message, Boolean> mapEtat=null;
	public static Vector<Message> MsgNonLu=null;
	public static ArrayList<beans.Message> EtatMsgEnvoyer=new ArrayList<Message>();
	@SuppressWarnings("unchecked")
	public static void Lire(demande envoie) {   
		    reponse ReponseServer=null;
		try {
	        oos.writeObject(envoie);
	        oos.flush();
	        ReponseServer=(reponse) ois.readObject();
	        if(ReponseServer.getTitre().equals("identification")){	        
	        	Map<String, Object> contenue=(Map<String, Object>) ReponseServer.getContenue();
	        	contact=(Vector<Contact>) contenue.get("listeContact");
	        	data=(Vector<Message>) contenue.get("listeMessage");
	        	utilisateur=(Contact) contenue.get("utilisateur");
	        	ListeRelation=(Vector<String>) contenue.get("listRelation");
	        }
		} catch (Exception e) {e.printStackTrace();}
		//return msgServer;  
	}
	public static void main(String[] args) throws Exception {}
}
