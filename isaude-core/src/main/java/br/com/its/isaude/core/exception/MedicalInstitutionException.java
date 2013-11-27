package br.com.its.isaude.core.exception;

import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;



public class MedicalInstitutionException extends Exception {

	private static final long serialVersionUID = -8196278471624471676L;

	private MessageResponseStatusEnum msg;

	public MedicalInstitutionException(MessageResponseStatusEnum msg) {
		this.msg = msg;
	}

	public MessageResponseStatusEnum getMsg() {
		return msg;
	}

}
