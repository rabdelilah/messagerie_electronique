package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="APP")
public class Groupe implements Serializable {
	@Id
	@Column(name="N_GROUP")
	private int nGroup;

	@Column(name="NOM_GROUP")
	private String nomGroup;

	private static final long serialVersionUID = 1L;

	public Groupe() {
		super();
	}

	public int getNGroup() {
		return this.nGroup;
	}

	public void setNGroup(int nGroup) {
		this.nGroup = nGroup;
	}

	public String getNomGroup() {
		return this.nomGroup;
	}

	public void setNomGroup(String nomGroup) {
		this.nomGroup = nomGroup;
	}

}
