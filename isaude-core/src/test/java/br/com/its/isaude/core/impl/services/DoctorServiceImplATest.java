package br.com.its.isaude.core.impl.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.its.isaude.core.dbunit.config.DBUnitConfiguration;
import br.com.its.isaude.core.exception.DoctorException;
import br.com.its.isaude.core.interfaces.services.DoctorService;
import br.com.its.isaude.core.interfaces.services.MedicalInstitutionService;
import br.com.its.isaude.core.interfaces.services.MedicalSpecialityService;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;

@RunWith(SpringJUnit4ClassRunner.class)
public class DoctorServiceImplATest extends DBUnitConfiguration {

    @Autowired
    @Qualifier("doctorServiceImpl")
    private DoctorService doctorServiceImpl;

    @Autowired
    @Qualifier("medicalSpecialityServiceImpl")
    private MedicalSpecialityService medicalSpecialityServiceImpl;

    @Autowired
    @Qualifier("medicalInstitutionServiceImpl")
    private MedicalInstitutionService medicalInstitutionServiceImpl;
    private MedicalSpeciality cardiologia;

    private HashSet<MedicalSpeciality> especialidadeMedicas;

    private HashSet<MedicalInstitutional> instituicaoMedicas;

    @Before
    public void setUp() throws Exception {
        getSetUpOperation().execute(getConnection(), getDataSet());
        final long id = 1L;
        cardiologia = medicalSpecialityServiceImpl.getById(id);
        final MedicalInstitutional hospitalHolandes = medicalInstitutionServiceImpl.getById(id);
        instituicaoMedicas = new HashSet<MedicalInstitutional>();
        instituicaoMedicas.add(hospitalHolandes);
        especialidadeMedicas = new HashSet<MedicalSpeciality>();
        especialidadeMedicas.add(cardiologia);
    }

    @Test
    public void testGetAllMedicosByOrderDescId() throws Exception {
        List<Doctor> list = doctorServiceImpl.list();
        Long idExpected = 3L;
        final int indexElement = 0;
        assertEquals(idExpected, list.get(indexElement).getId());
    }

    @Test
    public void testSaveDoctorWithSuccess() {
        try {
            Set<WorktimeDoctor> worktimeDoctors = new HashSet<WorktimeDoctor>();
            Doctor doctor = new Doctor("Carlos", "Burton", "bt@xpto.com", "Formado em cardiologia", "1156987458", "CRM-BA 9800", especialidadeMedicas, worktimeDoctors, instituicaoMedicas);
            doctorServiceImpl.save(doctor);
        } catch (Exception e) {
            fail("Not Expected result");
        }
    }

    @Test(expected = DoctorException.class)
    public void testCannotBeSaveDoctorCRMAlreadyExists() throws Exception {
        Set<WorktimeDoctor> worktimeDoctors = new HashSet<WorktimeDoctor>();
        Doctor doctor = new Doctor("Chapolin", "Colorado", "chapolin@colorado", "Formado em 1980", "11365985784", "CRM-SP 9828", especialidadeMedicas, worktimeDoctors, instituicaoMedicas);
        doctorServiceImpl.save(doctor);
    }

    @Test(expected = DoctorException.class)
    public void testCannotBeSaveDoctorEmailAlreadyExists() throws Exception {
        Set<WorktimeDoctor> worktimeDoctors = new HashSet<WorktimeDoctor>();
        Doctor doctor = new Doctor("Chaves", "Barbosa", "rc@xpto.com", "Formado 1969", "11365985784", "CRM-9802", especialidadeMedicas, worktimeDoctors, instituicaoMedicas);
        doctorServiceImpl.save(doctor);
    }
    @Test
    public void testSearchDoctorByCRMExactlyExpectedOneDoctor(){
    	final String expectedCRM = "CRM-SP 9828";
		final String description = expectedCRM;
		List<Doctor> list = doctorServiceImpl.search(description);
		final int index = 0;
		final Doctor doctorFound = list.get(index);
		assertEquals(expectedCRM,doctorFound.getCrm());
    }
    
    @Test
    public void testSearchDoctorByName(){
    	List<Doctor> list = doctorServiceImpl.search("Roberto");
    	String nameExpected = "Roberto";
    	for (Doctor doctor : list) {
			assertEquals(nameExpected ,doctor.getNome());
		}
    }
    
    @Test
    public void testSearchDoctorByLastname(){
    	List<Doctor> list = doctorServiceImpl.search("Pereira");
    	final int totalDoctorExpected = 2;
		assertEquals(totalDoctorExpected,list.size());
    }
    
    @Test
    public void testSearchDoctorBySpecialityMedical(){
    	List<Doctor> list = doctorServiceImpl.search("Urologia");
    	int totalDoctorExpected = 2;
		assertEquals(totalDoctorExpected , list.size());
    }
    @Test
    public void testSearchDoctorByCRMNoExist(){
    	List<Doctor> list = doctorServiceImpl.search("CRM-RJ 9999");
    	assertTrue(list.isEmpty());
    }
    @Test
    public void testSearchDoctorByNameNoExist(){
    	List<Doctor> list = doctorServiceImpl.search("Saulo");
    	assertTrue(list.isEmpty());
    }
    
    @Test
    public void testSearchDoctorByLastnameNoExist(){
    	List<Doctor> list = doctorServiceImpl.search("Monteiro");
    	assertTrue(list.isEmpty());
    }
    
    @Test
    public void testSearchDoctorBySpecialityMedicalNoExist(){
    	List<Doctor> list = doctorServiceImpl.search("Pediatria");
    	assertTrue(list.isEmpty());	
    }
    @Test
    public void testSearchDoctorBySpecialityMedicalPartialDescription(){
    	List<Doctor> list = doctorServiceImpl.search("Uro");
    	assertFalse(list.isEmpty());
    	int totalDoctorUrologiaExpected = 2;
		assertEquals(totalDoctorUrologiaExpected , list.size());
		for (Doctor doctor : list) {
			Set<MedicalSpeciality> listMedicalSpecialities = doctor.getEspecialidadeMedicas();
			for (MedicalSpeciality medicalSpeciality : listMedicalSpecialities) {
				final String expectedMedicalSpeciality = "Urologia";
				assertEquals(expectedMedicalSpeciality, medicalSpeciality.getDescription());
			}
		}
    }
    @Test
    public void testSearchDoctorByCRMParcialDescription(){
    	List<Doctor> list = doctorServiceImpl.search("CRM-SP");
    	int totalDoctorsExpected=2;
		assertEquals(totalDoctorsExpected, list.size());
    }
    @Test
    public void testSearchDoctorCRMCaseInsensitive(){
    	List<Doctor> list = doctorServiceImpl.search("crm-BA 6698");
    	final int index = 0;
		final String doctorCrm = list.get(index).getCrm();
		assertEquals("CRM-BA 6698", doctorCrm);
    }
}
