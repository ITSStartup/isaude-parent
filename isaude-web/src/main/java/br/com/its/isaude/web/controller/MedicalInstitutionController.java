package br.com.its.isaude.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import br.com.its.isaude.core.exception.MedicalInstitutionException;
import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;
import br.com.its.isaude.core.interfaces.services.MedicalInstitutionService;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.web.util.AjaxMsg;

@Controller
@Path("/medicalinstitution")
public class MedicalInstitutionController {
	@Autowired
	@Qualifier("medicalInstitutionServiceImpl")
	private MedicalInstitutionService medicalInstitutionServiceImpl;
	
	private List<AjaxMsg>listErrors = new ArrayList<AjaxMsg>();
	private AjaxMsg ajaxMessageError;
	private String messageError = null;
	

	public MedicalInstitutionService getMedicalInstitutionServiceImpl() {
		return medicalInstitutionServiceImpl;
	}

	public void setMedicalInstitutionServiceImpl(
			MedicalInstitutionService medicalInstitutionServiceImpl) {
		this.medicalInstitutionServiceImpl = medicalInstitutionServiceImpl;
	}

	@SuppressWarnings("finally")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(MedicalInstitutional medicalInstitutional) {
		 Response response = Response.ok().build();
		try {
			medicalInstitutionServiceImpl.save(medicalInstitutional);
		} catch (MedicalInstitutionException e) {
			messageError = e.getMsg().toString();
			ajaxMessageError =  new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
		
			 response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();
		}catch (ConstraintViolationException e) {
			messageError = MessageResponseStatusEnum.CNPJ_INVALID.toString();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();
		}
		
		catch (Exception e) {
			messageError = e.getMessage();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();
		}finally{
			return response;
		}
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(MedicalInstitutional medicalInstitutional){
		Response response = Response.ok().build();
		try {
			medicalInstitutionServiceImpl.update(medicalInstitutional);
		} catch (Exception e) {
			messageError = e.getMessage(); 
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();
		}finally{
			return response;
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public Response remove(@PathParam("id")Long id){
		Response response = Response.ok().build();
		try {
			MedicalInstitutional medicalInstitutional = medicalInstitutionServiceImpl.getById(id);
			medicalInstitutionServiceImpl.delete(medicalInstitutional);
		}
		catch (Exception e) {
			messageError = e.getMessage();
			ajaxMessageError = new AjaxMsg(messageError);
			response  = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();
		}finally{
			return response;
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(){
		List<MedicalInstitutional> list = new ArrayList<MedicalInstitutional>();
		 Response response = Response.ok().build();
		try {
			list = medicalInstitutionServiceImpl.list();
			response = Response.status(Response.Status.OK).entity(list).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}finally{
			return response;
		}
		
	}

}
