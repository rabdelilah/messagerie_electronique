package beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Contacte",schema="APP")
@NamedQueries({
    @NamedQuery(name="listeContactParContact",
                query="select c from Contact c where c.mail in(select r.liste from Relationcontacte r where r.nom = :n)"),
    @NamedQuery(name="listRelation",
                query="select r.liste from Relationcontacte r where r.nom = :n"),            
  
})

public class Contact implements Serializable {
	@Id
	private String mail;

	private String nom;

	private String prenom;

	private String pass;

	private int cota;

	private static final long serialVersionUID = 1L;

	public Contact() {
		super();
	}
	public Contact(String nom, String prenom, String mail, String pwd, int cota) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.pass = pwd;
		this.cota = cota;
	}
	public Contact(String nom, String prenom, String mail) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}
	
	public Contact(String mail) {
		super();
		this.mail = mail;
	}
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getCota() {
		return this.cota;
	}

	public void setCota(int cota) {
		this.cota = cota;
	}

}
