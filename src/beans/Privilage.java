package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="APP")
public class Privilage implements Serializable {
	@Id
	@Column(name="N_PRIVILAGE")
	private int nPrivilage;

	@Column(name="NOM_PRIVILAGE")
	private String nomPrivilage;

	private static final long serialVersionUID = 1L;

	public Privilage() {
		super();
	}

	public int getNPrivilage() {
		return this.nPrivilage;
	}

	public void setNPrivilage(int nPrivilage) {
		this.nPrivilage = nPrivilage;
	}

	public String getNomPrivilage() {
		return this.nomPrivilage;
	}

	public void setNomPrivilage(String nomPrivilage) {
		this.nomPrivilage = nomPrivilage;
	}

}
