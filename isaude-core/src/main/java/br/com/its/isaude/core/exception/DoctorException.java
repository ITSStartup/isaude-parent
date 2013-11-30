package br.com.its.isaude.core.exception;

import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;

public class DoctorException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6019396323888832416L;
	private MessageResponseStatusEnum msg;

	public DoctorException(MessageResponseStatusEnum msg) {
		this.msg = msg;
	}

	public MessageResponseStatusEnum getMsg() {
		return msg;
	}
}
