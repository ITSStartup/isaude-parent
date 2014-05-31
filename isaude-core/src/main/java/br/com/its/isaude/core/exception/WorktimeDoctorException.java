package br.com.its.isaude.core.exception;

import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;

@SuppressWarnings("serial")
public class WorktimeDoctorException extends Exception {

	private MessageResponseStatusEnum msg;

	public WorktimeDoctorException(MessageResponseStatusEnum msg) {
		this.msg = msg;
	}

	public MessageResponseStatusEnum getMsg() {
		return msg;
	}

}