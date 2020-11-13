package beans;

import java.io.Serializable;

public class demande implements Serializable{
	private String titre;
	private Object contenue;
	public demande(String titre, Object contenue) {
		super();
		this.titre = titre;
		this.contenue = contenue;
	}
	public demande() {
		super();
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public  Object getContenue() {
		return contenue;
	}
	public void setContenue(Object contenue) {
		this.contenue = contenue;
	}
}
