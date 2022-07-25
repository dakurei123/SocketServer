package error;

import java.io.Serializable;

public class Errors implements Serializable{
	private static final long serialVersionUID = 7702928382574108637L;
	private String code;
	private String errorContent;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getErrorContent() {
		return errorContent;
	}
	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}
	public Errors(String code, String errorContent) {
		super();
		this.code = code;
		this.errorContent = errorContent;
	}
	public Errors() {
		super();
	}
	
}
