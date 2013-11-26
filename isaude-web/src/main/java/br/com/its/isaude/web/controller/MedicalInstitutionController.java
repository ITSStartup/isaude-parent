package br.com.its.isaude.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import br.com.its.isaude.core.exception.MedicalInstitutionException;
import br.com.its.isaude.core.generic.interfaces.MedicalInstitutionService;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;

@Controller
@Path("/medicalinstitution")
public class MedicalInstitutionController {
	@Autowired
	@Qualifier("medicalInstitutionServiceImpl")
	private MedicalInstitutionService medicalInstitutionServiceImpl;

	public MedicalInstitutionService getMedicalInstitutionServiceImpl() {
		return medicalInstitutionServiceImpl;
	}

	public void setMedicalInstitutionServiceImpl(
			MedicalInstitutionService medicalInstitutionServiceImpl) {
		this.medicalInstitutionServiceImpl = medicalInstitutionServiceImpl;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(MedicalInstitutional medicalInstitutional) {
		try {
			medicalInstitutionServiceImpl.save(medicalInstitutional);
		} catch (MedicalInstitutionException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMsg()).build();
		}catch (Exception e) {
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MedicalInstitutional> list(){
		List<MedicalInstitutional> list = new ArrayList<MedicalInstitutional>();
		try {
			list = medicalInstitutionServiceImpl.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
