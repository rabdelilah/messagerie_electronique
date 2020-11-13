package DAO;

import java.util.Vector;

import beans.Contact;


public class ContactDAO {
	public static Vector<Contact> lireContact() {   
		Client.contact=(Vector<Contact>) Client.mapServer.get("contact");
		   return Client.contact;
	}
	public static Vector<String> listeContact(){
		Vector<String>v=new Vector<String>();
		for(Contact c:Client.contact){
			v.add(c.getMail());
		}
		return v;
	}
	public static Vector<String> listRelation(){
		Vector<String>v=new Vector<String>();
		for(String c:Client.ListeRelation){
			v.add(c);
		}
		return v;
	}
	public static boolean verifExistContact(Contact contact){
		boolean exist=false;
		for(Contact c:Client.contact){
			if(c.getMail().equals(contact.getMail())){
				exist=true;
				break;
			}
		}
		return exist;
	}
}
