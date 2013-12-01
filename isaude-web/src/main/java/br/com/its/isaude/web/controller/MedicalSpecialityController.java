package br.com.its.isaude.web.controller;

import java.util.ArrayList;
import java.util.List;

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

import br.com.its.isaude.core.exception.MedicalSpecialityException;
import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;
import br.com.its.isaude.core.interfaces.services.MedicalSpecialityService;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;
import br.com.its.isaude.web.util.AjaxMsg;

@Controller
@Path("/speciality")
public class MedicalSpecialityController {

	private List<AjaxMsg> listErrors;
	private AjaxMsg ajaxMessageError;
	private Response response;

	@Autowired
	@Qualifier("medicalSpecialityServiceImpl")
	private MedicalSpecialityService medicalSpecialityServiceImpl;

	@GET
	@SuppressWarnings("finally")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getList() throws Exception {

		listErrors = new ArrayList<AjaxMsg>();
		response = Response.ok().build();

		try {

			List<MedicalSpeciality> list = medicalSpecialityServiceImpl.list();
			response = Response.status(Response.Status.OK).entity(list).build();

		} catch (MedicalSpecialityException e) {

			String messageErrorName = e.getMsg().toString();
			ajaxMessageError = new AjaxMsg(messageErrorName);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();

		} catch (Exception e) {

			String messageError = MessageResponseStatusEnum.CANNOT_BE_LISTED.toString();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();

		} finally {

			return response;

		}

	}

	@POST
	@SuppressWarnings("finally")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(MedicalSpeciality medicalSpeciality) {

		listErrors = new ArrayList<AjaxMsg>();
		response = Response.ok().build();

		try {

			medicalSpecialityServiceImpl.save(medicalSpeciality);

		} catch (MedicalSpecialityException e) {

			String messageErrorName = e.getMsg().toString();
			ajaxMessageError = new AjaxMsg(messageErrorName);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();

		} catch (Exception e) {

			String messageError = MessageResponseStatusEnum.SAVE_NOT_SUCCESS.toString();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();

		} finally {

			return response;

		}
		
	}

	@PUT
	@Path("/{id}")
	@SuppressWarnings("finally")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, MedicalSpeciality medicalSpeciality) {
		
		listErrors = new ArrayList<AjaxMsg>();
		response = Response.ok().build();

		try {

			medicalSpeciality.setId(id);
			medicalSpecialityServiceImpl.update(medicalSpeciality);

		} catch (MedicalSpecialityException e) {

			String messageErrorName = e.getMsg().toString();
			ajaxMessageError = new AjaxMsg(messageErrorName);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();

		} catch (Exception e) {

			String messageError = MessageResponseStatusEnum.SAVE_NOT_SUCCESS.toString();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();

		} finally {

			return response;

		}

	}

	@DELETE
	@Path("/{id}")
	@SuppressWarnings("finally")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") Long id) {
	
		listErrors = new ArrayList<AjaxMsg>();
		response = Response.ok().build();

		try {

			MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(id);
			medicalSpecialityServiceImpl.delete(medicalSpeciality);

		} catch (MedicalSpecialityException e) {

			String messageErrorName = e.getMsg().toString();
			ajaxMessageError = new AjaxMsg(messageErrorName);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();

		} catch (Exception e) {

			String messageError = MessageResponseStatusEnum.SAVE_NOT_SUCCESS.toString();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();

		} finally {

			return response;

		}

	}

	public MedicalSpecialityService getMedicalSpecialityServiceImpl() {
		return medicalSpecialityServiceImpl;
	}
	
	public void setMedicalSpecialityServiceImpl(MedicalSpecialityService medicalSpecialityServiceImpl) {
		this.medicalSpecialityServiceImpl = medicalSpecialityServiceImpl;
	}

}