package br.com.its.isaude.core.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.its.isaude.core.exception.WorktimeDoctorException;
import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;
import br.com.its.isaude.core.generic.impl.services.GenericServiceImpl;
import br.com.its.isaude.core.interfaces.dao.WorktimeDoctorDAO;
import br.com.its.isaude.core.interfaces.services.WorktimeDoctorService;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;
import java.util.List;

@Service
public class WorktimeDoctorServiceImpl extends GenericServiceImpl<WorktimeDoctor, WorktimeDoctorDAO> implements WorktimeDoctorService {

    @Override
    @Autowired
    @Qualifier("worktimeDoctorDAOImpl")
    public void setDao(WorktimeDoctorDAO dao) {
        super.setDao(dao);
    }

    @Override
    public void save(WorktimeDoctor entity) throws Exception {
        checkConflictBetweenWorktimeDoctors(entity);
        try {
            super.save(entity);
        } catch (Exception e) {
            throw new WorktimeDoctorException(MessageResponseStatusEnum.SAVE_NOT_SUCCESS);
        }
    }

    @Override
    public void update(WorktimeDoctor entity) throws Exception {
        checkConflictBetweenWorktimeDoctors(entity);
        try {
            super.update(entity);
        } catch (Exception e) {
            throw new WorktimeDoctorException(MessageResponseStatusEnum.SAVE_NOT_SUCCESS);
        }
    }

    private void checkConflictBetweenWorktimeDoctors(WorktimeDoctor worktimeDoctor) throws WorktimeDoctorException {
        int minimalQuantityToBeOk = 0;
        List<WorktimeDoctor> list = validateWorktimeDoctor(worktimeDoctor);
        Boolean existsConflict = list.size() > minimalQuantityToBeOk;
        if (existsConflict) {
            throw new WorktimeDoctorException(MessageResponseStatusEnum.WORKTIMEDOCTOR_EXISTS);
        }
    }

    @Override
    public void delete(WorktimeDoctor entity) throws Exception {
        try {
            super.delete(entity);
        } catch (Exception e) {
            throw new WorktimeDoctorException(MessageResponseStatusEnum.REMOVE_NOT_SUCCESS);
        }
    }

    @Override
    public List<WorktimeDoctor> listByDoctorAndMedicalInstitutional(Doctor doctor, MedicalInstitutional medicalInstitutional) {
        return getDao().listByDoctorAndMedicalInstitutional(doctor, medicalInstitutional);
    }

    @Override
    public List<WorktimeDoctor> validateWorktimeDoctor(WorktimeDoctor worktimeDoctor) {
        return getDao().validateWorktimeDoctor(worktimeDoctor);
    }

}
