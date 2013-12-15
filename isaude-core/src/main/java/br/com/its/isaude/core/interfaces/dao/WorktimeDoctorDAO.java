package br.com.its.isaude.core.interfaces.dao;

import br.com.its.isaude.core.generic.interfaces.dao.GenericDAO;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;
import java.util.List;

public interface WorktimeDoctorDAO extends GenericDAO<WorktimeDoctor>{

    List<WorktimeDoctor> listByDoctorAndMedicalInstitutional(Doctor doctor, MedicalInstitutional medicalInstitutional);
    
    List<WorktimeDoctor> checkConflictBetweenWorktimeDoctor(WorktimeDoctor worktimeDoctor);

}