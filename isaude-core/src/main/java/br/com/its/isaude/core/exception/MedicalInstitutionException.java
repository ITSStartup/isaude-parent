package br.com.its.isaude.core.exception;

import br.com.its.isaude.core.enums.MsgResponseEnum;

public class MedicalInstitutionException extends Exception {

	private static final long serialVersionUID = -8196278471624471676L;

	private MsgResponseEnum msg;

	public MedicalInstitutionException(MsgResponseEnum message) {
		this.msg = message;
	}

	public MsgResponseEnum getMsg() {
		return msg;
	}

}
