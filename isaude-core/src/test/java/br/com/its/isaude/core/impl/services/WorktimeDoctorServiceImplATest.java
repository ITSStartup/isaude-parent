package br.com.its.isaude.core.impl.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.its.isaude.core.dbunit.config.DBUnitConfiguration;
import br.com.its.isaude.core.exception.WorktimeDoctorException;
import br.com.its.isaude.core.interfaces.services.DoctorService;
import br.com.its.isaude.core.interfaces.services.MedicalInstitutionService;
import br.com.its.isaude.core.interfaces.services.WorktimeDoctorService;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;
import org.joda.time.LocalTime;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorktimeDoctorServiceImplATest extends DBUnitConfiguration {

    @Autowired
    @Qualifier("medicalInstitutionServiceImpl")
    private MedicalInstitutionService medicalInstitutionServiceImpl;

    @Autowired
    @Qualifier("doctorServiceImpl")
    private DoctorService doctorServiceImpl;

    @Autowired
    @Qualifier("worktimeDoctorServiceImpl")
    private WorktimeDoctorService worktimeDoctorServiceImpl;

    private WorktimeDoctor worktimeDoctor;

    @Before
    public void setUp() throws Exception {
        worktimeDoctor = getNewWorktimeDoctor();
        getSetUpOperation().execute(getConnection(), getDataSet());
    }

    @Test
    public void testGetAllWorktimeDoctorRegistered() throws Exception {

        List<WorktimeDoctor> worktimeDoctors = worktimeDoctorServiceImpl.list();

        int quantity = worktimeDoctors.size();

        int quantityExpected = 2;
        
        assertNotNull(worktimeDoctors);

        assertFalse(worktimeDoctors.isEmpty());

        assertEquals(quantityExpected, quantity);

    }

    @Test
    public void testSaveWorktimeDoctorWithSuccessAfterWorktimeRegistered() throws Exception {

        createWorktimeDoctorForSaveTest();

        java.util.Date horarioInicial = new LocalTime(12, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(22, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.save(worktimeDoctor);

        WorktimeDoctor worktimeDoctorFromDataBase = worktimeDoctorServiceImpl.getById(worktimeDoctor.getId());

        assertEquals(horarioInicial, worktimeDoctorFromDataBase.getHorarioInicial());
        assertEquals(horarioFinal, worktimeDoctorFromDataBase.getHorarioFinal());

    }

    @Test
    public void testSaveWorktimeDoctorWithSuccessBeforeWorktimeRegistered() throws Exception {

        createWorktimeDoctorForSaveTest();

        java.util.Date horarioInicial = new LocalTime(01, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(06, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.save(worktimeDoctor);

        WorktimeDoctor worktimeDoctorFromDataBase = worktimeDoctorServiceImpl.getById(worktimeDoctor.getId());

        assertEquals(horarioInicial, worktimeDoctorFromDataBase.getHorarioInicial());
        assertEquals(horarioFinal, worktimeDoctorFromDataBase.getHorarioFinal());

    }

    @Test
    public void testUpdateWorktimeDoctorWithSuccessAfterWorktimeRegistered() throws Exception {

        createWorktimeDoctorForUpdateTest();

        java.util.Date horarioInicial = new LocalTime(18, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(23, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.update(worktimeDoctor);

        WorktimeDoctor worktimeDoctorFromDataBase = worktimeDoctorServiceImpl.getById(worktimeDoctor.getId());

        assertEquals(horarioInicial, worktimeDoctorFromDataBase.getHorarioInicial());
        assertEquals(horarioFinal, worktimeDoctorFromDataBase.getHorarioFinal());

    }

    @Test
    public void testUpdateWorktimeDoctorWithSuccessBeforeWorktimeRegistered() throws Exception {

        createWorktimeDoctorForUpdateTest();

        java.util.Date horarioInicial = new LocalTime(1, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(8, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.update(worktimeDoctor);

        WorktimeDoctor worktimeDoctorFromDataBase = worktimeDoctorServiceImpl.getById(worktimeDoctor.getId());

        assertEquals(horarioInicial, worktimeDoctorFromDataBase.getHorarioInicial());
        assertEquals(horarioFinal, worktimeDoctorFromDataBase.getHorarioFinal());

    }

    @Test
    public void testUpdateWorktimeDoctorWithSuccessDecreasingWorktimeRegistered() throws Exception {

        createWorktimeDoctorForUpdateTest();

        java.util.Date horarioInicial = new LocalTime(8, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(12, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.update(worktimeDoctor);

        WorktimeDoctor worktimeDoctorFromDataBase = worktimeDoctorServiceImpl.getById(worktimeDoctor.getId());

        assertEquals(horarioInicial, worktimeDoctorFromDataBase.getHorarioInicial());
        assertEquals(horarioFinal, worktimeDoctorFromDataBase.getHorarioFinal());

    }

    @Test(expected = WorktimeDoctorException.class)
    public void testCannotSaveWorktimeDoctorWithHorarioInicialConflicting() throws Exception {

        createWorktimeDoctorForSaveTest();

        java.util.Date horarioInicial = new LocalTime(8, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(18, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.save(worktimeDoctor);

    }

    @Test(expected = WorktimeDoctorException.class)
    public void testCannotSaveWorktimeDoctorWithHorarioFinalConflicting() throws Exception {

        createWorktimeDoctorForSaveTest();

        java.util.Date horarioInicial = new LocalTime(4, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(10, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.save(worktimeDoctor);

    }

    @Test(expected = WorktimeDoctorException.class)
    public void testCannotSaveWorktimeDoctorWithHorarioInicialAndFinalConflicting() throws Exception {

        createWorktimeDoctorForSaveTest();

        java.util.Date horarioInicial = new LocalTime(8, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(10, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.save(worktimeDoctor);

    }

    @Test(expected = WorktimeDoctorException.class)
    public void testCannotSaveWorktimeDoctorWithHorarioInicialFinalConflictingAround() throws Exception {

        createWorktimeDoctorForSaveTest();

        java.util.Date horarioInicial = new LocalTime(4, 00).toDateTimeToday().toDate();
        java.util.Date horarioFinal = new LocalTime(18, 00).toDateTimeToday().toDate();

        worktimeDoctor.setHorarioInicial(horarioInicial);
        worktimeDoctor.setHorarioFinal(horarioFinal);

        worktimeDoctorServiceImpl.save(worktimeDoctor);

    }

    private void createWorktimeDoctorForUpdateTest() throws Exception {
        
        Long idWorktimeDoctor = 2l;
        
        worktimeDoctor = worktimeDoctorServiceImpl.getById(idWorktimeDoctor);
        
    }

    private void createWorktimeDoctorForSaveTest() throws Exception {

        Long idMedicalInstitutional = 2l;
        MedicalInstitutional medicalInstitutional = medicalInstitutionServiceImpl.getById(idMedicalInstitutional);

        Long idDoctor = 1l;
        Doctor doctor = doctorServiceImpl.getById(idDoctor);

        worktimeDoctor.setMedico(doctor);
        worktimeDoctor.setInstituicaoMedica(medicalInstitutional);

    }

    private WorktimeDoctor getNewWorktimeDoctor() {
        WorktimeDoctor wd = new WorktimeDoctor();
        LocalTime horarioInicial = new LocalTime(06, 00);
        wd.setHorarioInicial(horarioInicial.toDateTimeToday().toDate());
        LocalTime horarioFinal = new LocalTime(17, 00);
        wd.setHorarioFinal(horarioFinal.toDateTimeToday().toDate());
        wd.setInstituicaoMedica(null);
        return wd;
    }

    public MedicalInstitutionService getMedicalInstitutionServiceImpl() {
        return medicalInstitutionServiceImpl;
    }

    public DoctorService getDoctorServiceImpl() {
        return doctorServiceImpl;
    }

    public WorktimeDoctorService getWorktimeDoctorServiceImpl() {
        return worktimeDoctorServiceImpl;
    }

}
