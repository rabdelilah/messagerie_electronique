package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import beans.Admin;
import beans.Contact;

public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mail_V_Jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Contact a=new Contact();
		Query req=em.createNamedQuery("listeContactParContact");
		req.setParameter("n", "hasnaa@rs.com");
		List<Contact> arr= req.getResultList();
		for(Contact c:arr){
			System.out.println(c.getMail()+" "+c.getPass());
		}
		a.setMail("hasnaa@rs.com");a.setPass("123");
		em.merge(a);
		em.getTransaction().commit();*/
		System.out.println(new java.util.Date("10/05/2010 10:12:23"));
		
	}

}
