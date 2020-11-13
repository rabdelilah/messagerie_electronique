package beans;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema="APP")
@NamedQueries({
    @NamedQuery(name="MessageLu",
                query="update Message m set m.etat=1 where m.msg= :msg and m.object= :object and m.src= :src"),
  
})
public class Message implements Serializable {
	@Id
	private java.sql.Timestamp date;

	private String src;

	private String dest;

	private String object;

	private String msg;

	private short etat;

	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	}
	public Message(String src, String dest, java.sql.Timestamp date, String objet, String msg,short etat) {
		super();
		this.src = src;
		this.dest = dest;
		this.date = date;
		this.object = objet;
		this.msg = msg;
		this.etat = etat;
	}
	public Message(String src, String dest, java.sql.Timestamp date , String objet, String msg) {
		super();
		this.src = src;
		this.dest = dest;
		this.date = date;
		this.object = objet;
		this.msg = msg;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return this.dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public short getEtat() {
		return this.etat;
	}

	public void setEtat(short etat) {
		this.etat = etat;
	}

}
