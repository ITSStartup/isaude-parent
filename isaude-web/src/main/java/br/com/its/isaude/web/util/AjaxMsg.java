package br.com.its.isaude.web.util;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class AjaxMsg implements java.io.Serializable {

	private String message;
	
	public AjaxMsg(){}
	
	public AjaxMsg(String message) {
		this.message = message;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}
	
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}
	
}
