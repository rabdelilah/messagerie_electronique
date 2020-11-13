package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import beans.Contact;
import beans.Message;
import beans.Relationcontacte;




public class Server extends Thread {

	   private ServerSocket dateServer;
	   public static ArrayList<Message> data=null; 
	   public static Map<String,Vector<Contact>> contact=null;
	   public static Vector<Contact> user=null;
	   public static Map<String, Socket> sockets=new TreeMap<String, Socket>();
	   static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mail_V_Jpa");
	   static EntityManager em = emf.createEntityManager();
	   public static void main(String argv[]) throws Exception {
	     new Server();
	   }

	   public Server() throws Exception { 	   
		 dateServer = new ServerSocket(1900);
	     System.out.println("Server listening on port 1900.");
	     this.start();
	   } 

	   public void run() {
	     while(true) {
	       try {
	        System.out.println("Waiting for connections.");
	        Socket client = dateServer.accept();
	       
	        System.out.println("Accepted a connection from: "+client.getInetAddress());
	        //IHMserver.getTxt().setText("\nAccepted a connection from: "+client.getInetAddress()+" ; "+new Date());
	        Connect c = new Connect(client);
	       } catch(Exception e) {}
	     }
	   }
	  public static List<Contact> listeContacteParContacte(String email){
		  em.getTransaction().begin();
		  Query req=em.createNamedQuery("listeContactParContact");
		  req.setParameter("n", email);
		  em.getTransaction().commit();
		  return  req.getResultList();
	  }
	  public static List<String> listeRelation(String email){
		  em.getTransaction().begin();
		  Query req=em.createNamedQuery("listRelation");
		  req.setParameter("n", email);
		  em.getTransaction().commit();
		  return  req.getResultList();
	  }
	  public static List<Message> listeMessageParContact(String email){
		  em.getTransaction().begin();
		  Query req=em.createQuery("select m from Message m where m.src = :source or m.dest = :dst");
		  req.setParameter("source", email);req.setParameter("dst", email);
		  em.getTransaction().commit();
		  return req.getResultList();
	  }
	  public static Contact Identification(String email){
		  em.getTransaction().begin();
		  Contact c=em.find(Contact.class, email);
		  em.getTransaction().commit();
		  return  c;
	  }
	  public static void ajout(Object o){
		  em.getTransaction().begin();
		  em.persist(o);
		  em.getTransaction().commit();
	  }
	  public static void AjoutMessage(ArrayList<Message> o){
		  em.getTransaction().begin();
		  for(Message obj:o){
			  em.persist(obj);
		  }
		  em.getTransaction().commit();
	  }
	  public static void Modiffier(Object o){
		  em.getTransaction().begin();
		  em.merge(o);
		  em.getTransaction().commit();
	  }
	  public static void MessageLu(String msg,String email,String Objet){
		  em.getTransaction().begin();
		  Query req=em.createNamedQuery("MessageLu");
		  req.setParameter("msg", msg);
		  req.setParameter("src", email);
		  req.setParameter("object", Objet);
		  System.out.println(req.executeUpdate() + " mise a jour");
		  em.getTransaction().commit();
	  }
	  public static void Supp(Object o){
		  em.getTransaction().begin();
		  em.remove(o);
		  em.getTransaction().commit();
	  }
	  
	}



