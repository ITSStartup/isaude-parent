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
import br.com.its.isaude.core.modal.domain.AgendaDoctor;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;
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
	public void testGetAllMedicosByOrderDescId() throws Exception{
		List<Doctor> list = doctorServiceImpl.list();
		Integer idExpected = 2;
		final int indexElement = 0;
		assertEquals(idExpected , list.get(indexElement).getId());
	}
	@Test
	public void testSaveDoctorWithSuccess(){
		try {
		Set<AgendaDoctor> agendaMedicos = new HashSet<AgendaDoctor>();
		Doctor doctor = new Doctor("Carlos", "Burton", "bt@xpto.com", "Formado em cardiologia", "1156987458", "CRM-980", especialidadeMedicas, agendaMedicos, 
				instituicaoMedicas);
		doctorServiceImpl.save(doctor );
		} catch (Exception e) {
			fail("Not Expected result");
		}
	}
	@Test(expected=DoctorException.class)
	public void testCannotBeSaveDoctorCRMAlreadyExists() throws Exception{
		Set<AgendaDoctor> agendaMedicos = new  HashSet<AgendaDoctor>();
		Doctor doctor = new Doctor("Chapolin", "Colorado", "chapolin@colorado", "Formado em 1980", "11365985784", "CRM-982", especialidadeMedicas, agendaMedicos , instituicaoMedicas);
		doctorServiceImpl.save(doctor);
	}
	@Test(expected=DoctorException.class)
	public void testCannotBeSaveDoctorEmailAlreadyExists() throws Exception {
		Set<AgendaDoctor> agendaMedicos = new  HashSet<AgendaDoctor>();
		Doctor doctor = new Doctor("Chaves", "Barbosa", "rc@xpto.com", "Formado 1969", "11365985784", "CRM-9802", especialidadeMedicas, agendaMedicos , instituicaoMedicas);
		doctorServiceImpl.save(doctor);
	}

}
