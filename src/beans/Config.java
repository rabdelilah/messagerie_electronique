package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="APP")
public class Config implements Serializable {
	@Id
	@Column(name="PARAM_NAME")
	private String paramName;

	@Column(name="PARAM_VALUE")
	private String paramValue;

	private static final long serialVersionUID = 1L;

	public Config() {
		super();
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
