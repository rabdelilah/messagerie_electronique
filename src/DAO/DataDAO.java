package DAO;

import java.util.ArrayList;
import java.util.Vector;

import javax.xml.crypto.Data;

import beans.Message;


public class DataDAO {
	public static Vector<beans.Message> lireData() {    
		Client.data= (Vector<beans.Message>)Client.data ;
	       return Client.data;
	}
	public static Vector<Message> data(){
		Vector<beans.Message> listeMsg=new Vector<Message>();
		for(Message d:Client.data){
			if(d.getDest().equalsIgnoreCase(Client.email) && !listeMsg.contains(d)){
				listeMsg.add(d);
			}
		}
		return listeMsg;
	}
	public static Vector<Message> dataEnvoie(){
		Vector<Message> listeMsg=new Vector<Message>();
		for(Message d:Client.data){
			if(d.getSrc().equalsIgnoreCase(Client.email) && !listeMsg.contains(d)){
				listeMsg.add(d);
			}
		}
		return listeMsg;
	}
	
}
