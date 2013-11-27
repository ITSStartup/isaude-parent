package br.com.its.isaude.core.exception;

import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;

@SuppressWarnings("serial")
public class MedicalSpecialityException extends Exception {

	private Exception rootException;
	
	private MessageResponseStatusEnum medicalSpecialityStatus; 
	
	public MedicalSpecialityException(Exception rootException, MessageResponseStatusEnum medicalSpecialityStatus) {
		this.rootException = rootException;
		this.medicalSpecialityStatus = medicalSpecialityStatus;
	}
	
	public Exception getRootException() {
		return rootException;
	}
	
	public MessageResponseStatusEnum getMedicalSpecialityStatus() {
		return medicalSpecialityStatus;
	}
	
	@Override
	public String getMessage() {
		return getRootException().getMessage();
	}
	
	@Override
	public String toString() {
		return getMedicalSpecialityStatus().name();
	}

}