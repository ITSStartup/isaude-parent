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
import br.com.its.isaude.core.exception.enums.MedicalSpecialityStatus;
import br.com.its.isaude.core.interfaces.services.MedicalSpecialityService;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;
import br.com.its.isaude.web.util.AjaxMsg;

@Controller
@Path("/speciality")
public class MedicalSpecialityController {

	@Autowired
	@Qualifier("medicalSpecialityServiceImpl")
	private MedicalSpecialityService medicalSpecialityServiceImpl;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getList() throws Exception {
		
		List<MedicalSpeciality> medicalSpecialities = new ArrayList<MedicalSpeciality>();
		
		List<AjaxMsg> errors = new ArrayList<AjaxMsg>();
		
		Response responseStatus = null;
		
		try {
			
			medicalSpecialities = medicalSpecialityServiceImpl.list();
		
		} catch (MedicalSpecialityException e) {
			
			errors.add(addAjaxMessage(e.getMedicalSpecialityStatus()));
		
		} catch (Exception e) {
			
			errors.add(addAjaxMessage(MedicalSpecialityStatus.CANNOT_BE_LISTED));
		
		} finally {
			
			responseStatus = checkErrors(errors, medicalSpecialities);
		
		}
		
		return responseStatus;
	
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(MedicalSpeciality medicalSpeciality) {

		return saveOrUpdate(medicalSpeciality, true);
	
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, MedicalSpeciality medicalSpeciality) {
		
		medicalSpeciality.setId(id);

		return saveOrUpdate(medicalSpeciality, false);

	}

	private Response saveOrUpdate(MedicalSpeciality medicalSpeciality, boolean insertingMedicalSpeciality) {
		
		MedicalSpecialityStatus medicalSpecialityStatusSuccess = MedicalSpecialityStatus.EDIT_SUCCESS;
		MedicalSpecialityStatus medicalSpecialityStatusNotSuccess = MedicalSpecialityStatus.EDIT_NOT_SUCCESS;
		
		if (insertingMedicalSpeciality) {
			
			medicalSpecialityStatusSuccess = MedicalSpecialityStatus.INSERT_SUCCESS;
			medicalSpecialityStatusNotSuccess = MedicalSpecialityStatus.INSERT_NOT_SUCCESS;
		
		}
		
		AjaxMsg success = new AjaxMsg(medicalSpecialityStatusSuccess.name());

		List<AjaxMsg> errors = new ArrayList<AjaxMsg>();

		errors = new ArrayList<AjaxMsg>();

		try {
			
			if (insertingMedicalSpeciality) {
				
				medicalSpecialityServiceImpl.save(medicalSpeciality);
			
			} else {
				
				medicalSpecialityServiceImpl.update(medicalSpeciality);
				
			}
			
		} catch (MedicalSpecialityException e) {
			
			errors.add(addAjaxMessage(e.getMedicalSpecialityStatus()));
		
		} catch (Exception e) {
			
			errors.add(addAjaxMessage(medicalSpecialityStatusNotSuccess));
		
		} finally {
			
			if (errors != null && errors.isEmpty() == false) {
				
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(errors).build();
				
			}
		
		}
		
		return Response.status(Response.Status.OK).entity(success).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") Integer id) {
		
		Response responseStatus = null;
		List<AjaxMsg> errors = new ArrayList<AjaxMsg>();

		try {

			MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(id);
			medicalSpecialityServiceImpl.delete(medicalSpeciality);

		} catch (MedicalSpecialityException e) {
			
			errors.add(new AjaxMsg(e.getMedicalSpecialityStatus().name()));

		} catch (Exception e) {
			
			errors.add(addAjaxMessage(MedicalSpecialityStatus.REMOVE_NOT_SUCCESS));

		} finally {
			
			if (errors != null && errors.isEmpty() == false) {
				 responseStatus = Response.status(Response.Status.NOT_ACCEPTABLE).entity(errors).build();
				return responseStatus;
			}
			
			AjaxMsg removeSuccess = new AjaxMsg(MedicalSpecialityStatus.REMOVE_SUCCESS.name());
			responseStatus = Response.status(Response.Status.OK).entity(removeSuccess).build();
			
		}

		return responseStatus;

	}

	private AjaxMsg addAjaxMessage(MedicalSpecialityStatus medicalSpecialityStatus) {
		return new AjaxMsg(medicalSpecialityStatus.name());
	}

	public MedicalSpecialityService getMedicalSpecialityServiceImpl() {
		return medicalSpecialityServiceImpl;
	}
	
	public void setMedicalSpecialityServiceImpl(MedicalSpecialityService medicalSpecialityServiceImpl) {
		this.medicalSpecialityServiceImpl = medicalSpecialityServiceImpl;
	}

	public Response checkErrors(List<AjaxMsg> errors,List<MedicalSpeciality> medicalSpecialities){
		
		Response responseStatus = null;
		
		if (errors != null && errors.isEmpty() == false) {
		
			responseStatus = Response.status(Response.Status.NOT_ACCEPTABLE).entity(errors).build();
			
			return responseStatus;
		
		}
		
		responseStatus = Response.status(Response.Status.OK).entity(medicalSpecialities).build();
		
		return responseStatus;
	
	}

}