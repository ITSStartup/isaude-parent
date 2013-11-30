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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.its.isaude.core.exception.DoctorException;
import br.com.its.isaude.core.interfaces.services.DoctorService;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.web.util.AjaxMsg;

@Path("/doctor")
public class DoctorController {
	@Autowired
	@Qualifier("doctorServiceImpl")
	private DoctorService doctorServiceImpl;
	private List<AjaxMsg>listErrors = new ArrayList<AjaxMsg>();
	private AjaxMsg ajaxMessageError;
	
	private String messageError = null;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Doctor doctor){
		Response response=Response.ok().build();
		try {
			doctorServiceImpl.save(doctor);
		}catch(DoctorException e){
			messageError = e.getMsg().toString();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Status.NOT_ACCEPTABLE).entity(listErrors).build();
		}catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getList(){
		 Response response = Response.ok().build();
		 try {
			List<Doctor> listDoctors = doctorServiceImpl.list();
			response = Response.status(Status.OK).entity(listDoctors).build();
		}catch(DoctorException e){
			messageError = e.getMsg().toString();
			ajaxMessageError = new AjaxMsg(messageError);
			listErrors.add(ajaxMessageError);
			response = Response.status(Status.NOT_ACCEPTABLE).entity(listErrors).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}finally{
		 return response;
		} 
	}

	public DoctorService getDoctorServiceImpl() {
		return doctorServiceImpl;
	}
	
}
