package beans;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="APP")
public class Relationcontacte implements Serializable {
	@Id
	private String nom;

	private String liste;

	private static final long serialVersionUID = 1L;

	public Relationcontacte() {
		super();
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getListe() {
		return this.liste;
	}

	public void setListe(String liste) {
		this.liste = liste;
	}

}
