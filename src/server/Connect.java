package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import DAO.Client;

import beans.Contact;
import beans.Message;
import beans.demande;
import beans.reponse;

public class Connect extends Thread {
   private Socket client = null;
   private ObjectInputStream ois = null;
   private ObjectOutputStream oos = null;
     
   public Connect() {}

   public Connect(Socket clientSocket) {
     client = clientSocket;
     try {
      ois = new ObjectInputStream(client.getInputStream());
      oos = new ObjectOutputStream(client.getOutputStream());
      
     } catch(Exception e1) {
         try {
            client.close();
         }catch(Exception e) {
           System.out.println(e.getMessage());
         }
         return;
     }
     this.start();
   }


   public void run() {
      try {
    	  while (true) {
    		 reponse reponse=null;
    	     Object mail= ois.readObject();
    	      if(((demande) mail).getTitre().equals("identification")){
    	    	 String userMail=(String) ((demande) mail).getContenue();
    	    	 Map<String, Object> contenue=new TreeMap<String,Object>();
    	    	 contenue.put("listeContact", Server.listeContacteParContacte(userMail));
    	    	 contenue.put("listeMessage", Server.listeMessageParContact(userMail));
    	    	 contenue.put("utilisateur", Server.Identification(userMail));
    	    	 contenue.put("listRelation", Server.listeRelation(userMail));
    	    	 reponse=new reponse("identification",contenue);
    	     }else if(((demande) mail).getTitre().equals("Ajout")){
    	    	 Server.ajout(((demande) mail).getContenue());
    	    	 reponse=new reponse("Ajout","");
    	     }else if(((demande) mail).getTitre().equals("maj")){
    	    	 Server.Modiffier(((demande) mail).getContenue());
    	    	 reponse=new reponse("maj","");
    	     }else if(((demande) mail).getTitre().equals("MessageLu")){
    	    	 Message m=(Message) ((demande) mail).getContenue();
    	    	 Server.MessageLu(m.getMsg(),m.getSrc(), m.getObject());
    	    	 reponse=new reponse("MessageLu","");
    	     }else if(((demande) mail).getTitre().equals("Supp")){
    	    	 Server.Supp(((demande) mail).getContenue());
    	    	 reponse=new reponse("supp","");
    	     }else if(((demande) mail).getTitre().equals("ajoutMsgs")){
    	    	 ArrayList<Message> liste=(ArrayList<Message>) ((demande) mail).getContenue();
    	    	 Server.AjoutMessage(liste);
    	    	 reponse=new reponse("ajoutMsgs","");
    	     }
			 oos.writeObject(reponse);
			 oos.flush();
    	 }
      } catch(Exception e) {}       
   }
}