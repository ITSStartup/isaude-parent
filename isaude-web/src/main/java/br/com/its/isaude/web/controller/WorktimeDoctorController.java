package br.com.its.isaude.web.controller;

import br.com.its.isaude.core.exception.WorktimeDoctorException;
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

import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;
import br.com.its.isaude.core.interfaces.services.WorktimeDoctorService;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;
import br.com.its.isaude.web.util.AjaxMsg;

@Controller
@Path("/worktime")
public class WorktimeDoctorController {

    private List<AjaxMsg> listErrors;
    private AjaxMsg ajaxMessageError;
    private Response response;

    @Autowired
    @Qualifier("worktimeDoctorServiceImpl")
    private WorktimeDoctorService worktimeDoctorServiceImpl;

    @GET
    @Path("/{idMedico}/{idInstituicao}")
    @SuppressWarnings("finally")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@PathParam("idMedico") Long idMedico, @PathParam("idInstituicao") Long idInstituicao) throws Exception {

        listErrors = new ArrayList<AjaxMsg>();
        response = Response.ok().build();

        try {

            Doctor doctor = new Doctor();
            doctor.setId(idMedico);
            
            MedicalInstitutional medicalInstitutional = new MedicalInstitutional();
            medicalInstitutional.setId(idInstituicao);
            
            List<WorktimeDoctor> list = worktimeDoctorServiceImpl.listByDoctorAndMedicalInstitutional(doctor, medicalInstitutional);
            response = Response.status(Response.Status.OK).entity(list).build();

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
    public Response save(WorktimeDoctor worktimeDoctor) {

        listErrors = new ArrayList<AjaxMsg>();
        response = Response.ok().build();

        try {

            worktimeDoctorServiceImpl.save(worktimeDoctor);

        } catch (WorktimeDoctorException e) {

            String messageErrorName = e.getMsg().toString();
            ajaxMessageError = new AjaxMsg(messageErrorName);
            listErrors.add(ajaxMessageError);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();

        } catch (Exception e) {

            String messageError = MessageResponseStatusEnum.ERROR_UNEXPECTED.toString();
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
    public Response update(@PathParam("id") Long id, WorktimeDoctor worktimeDoctor) {

        listErrors = new ArrayList<AjaxMsg>();
        response = Response.ok().build();

        try {

            worktimeDoctor.setId(id);
            worktimeDoctorServiceImpl.update(worktimeDoctor);

        } catch (WorktimeDoctorException e) {

            String messageErrorName = e.getMsg().toString();
            ajaxMessageError = new AjaxMsg(messageErrorName);
            listErrors.add(ajaxMessageError);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();

        } catch (Exception e) {

            String messageError = MessageResponseStatusEnum.ERROR_UNEXPECTED.toString();
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

            WorktimeDoctor worktimeDoctor = worktimeDoctorServiceImpl.getById(id);
            worktimeDoctorServiceImpl.delete(worktimeDoctor);

        } catch (WorktimeDoctorException e) {

            String messageErrorName = e.getMsg().toString();
            ajaxMessageError = new AjaxMsg(messageErrorName);
            listErrors.add(ajaxMessageError);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(listErrors).build();

        } catch (Exception e) {

            String messageError = MessageResponseStatusEnum.ERROR_UNEXPECTED.toString();
            ajaxMessageError = new AjaxMsg(messageError);
            listErrors.add(ajaxMessageError);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(listErrors).build();

        } finally {

            return response;

        }

    }
    
}
