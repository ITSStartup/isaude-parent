package br.com.its.isaude.core.interfaces.services;

import br.com.its.isaude.core.generic.interfaces.services.GenericService;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;
import java.util.List;

public interface WorktimeDoctorService extends GenericService<WorktimeDoctor> {

    WorktimeDoctor getByDoctorAndMedicalInstitutional(Doctor doctor, MedicalInstitutional medicalInstitutional);

    List<WorktimeDoctor> checkConflictBetweenWorktimeDoctor(WorktimeDoctor worktimeDoctor);

}
