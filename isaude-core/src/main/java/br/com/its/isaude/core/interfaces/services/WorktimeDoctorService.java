package br.com.its.isaude.core.interfaces.services;

import br.com.its.isaude.core.generic.interfaces.services.GenericService;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface WorktimeDoctorService extends GenericService<WorktimeDoctor> {

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    List<WorktimeDoctor> listByDoctorAndMedicalInstitutional(Doctor doctor, MedicalInstitutional medicalInstitutional) throws Exception;

    List<WorktimeDoctor> validateWorktimeDoctor(WorktimeDoctor worktimeDoctor);

}
