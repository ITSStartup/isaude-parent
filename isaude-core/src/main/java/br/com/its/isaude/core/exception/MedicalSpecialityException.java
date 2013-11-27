package br.com.its.isaude.core.exception;

import br.com.its.isaude.core.exception.enums.MedicalSpecialityStatus;

@SuppressWarnings("serial")
public class MedicalSpecialityException extends Exception {

	private Exception rootException;
	
	private MedicalSpecialityStatus medicalSpecialityStatus; 
	
	public MedicalSpecialityException(Exception rootException, MedicalSpecialityStatus medicalSpecialityStatus) {
		this.rootException = rootException;
		this.medicalSpecialityStatus = medicalSpecialityStatus;
	}
	
	public Exception getRootException() {
		return rootException;
	}
	
	public MedicalSpecialityStatus getMedicalSpecialityStatus() {
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