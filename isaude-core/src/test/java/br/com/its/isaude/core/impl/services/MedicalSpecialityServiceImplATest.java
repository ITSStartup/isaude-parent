package br.com.its.isaude.core.impl.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.its.isaude.core.dbunit.config.DBUnitConfiguration;
import br.com.its.isaude.core.interfaces.services.MedicalSpecialityService;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalSpecialityServiceImplATest extends DBUnitConfiguration {

	@Autowired
	@Qualifier("medicalSpecialityServiceImpl")
	private MedicalSpecialityService medicalSpecialityServiceImpl;
	
	private MedicalSpeciality medicalSpeciality;
	
	@Before
	public void setUp() throws Exception {
		medicalSpeciality = getNewMedicalSpeciality();
		getSetUpOperation().execute(getConnection(), getDataSet());
	}

	@Test
	public void testGetAllMedicalSpecialityRegistered() throws Exception {
		
		List<MedicalSpeciality> medicalSpecialities = medicalSpecialityServiceImpl.list();
		
		int quantity = medicalSpecialities.size();
		
		assertNotNull(medicalSpecialities);
		
		assertFalse(medicalSpecialities.isEmpty());
	
		assertEquals(4, quantity);
		
	}

	@Test
	public void testGetMedicalSpecialityCardiologiaRegistered() throws Exception {
		
		MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(1l);
		
		String medicalSpecialityDescriptionExpected = "Cardiologia";

		assertEquals(medicalSpecialityDescriptionExpected, medicalSpeciality.getDescription());
		
	}

	@Test
	public void testGetMedicalSpecialityGinecologiaRegistered() throws Exception {
		
		MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(2l);
		
		String medicalSpecialityDescriptionExpected = "Ginecologia";

		assertEquals(medicalSpecialityDescriptionExpected, medicalSpeciality.getDescription());
		
	}

	@Test
	public void testGetMedicalSpecialityUrologiaRegistered() throws Exception {
		
		MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(3l);
		
		String medicalSpecialityDescriptionExpected = "Urologia";

		assertEquals(medicalSpecialityDescriptionExpected, medicalSpeciality.getDescription());
		
	}

	@Test
	public void testGetMedicalSpecialityGeriatriaRegistered() throws Exception {
		
		MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(4l);
		
		String medicalSpecialityDescriptionExpected = "Geriatria";

		assertEquals(medicalSpecialityDescriptionExpected, medicalSpeciality.getDescription());
		
	}

	@Test
	public void testSaveMedicalSpecialityWithSuccess() throws Exception {
		
		medicalSpecialityServiceImpl.save(medicalSpeciality);

		MedicalSpeciality medicalSpecialitySaved = getMedicalSpecialityById(medicalSpeciality.getId());

		String medicalSpecialityDescriptionExpected = "Ginecologia";
		
		assertEquals(medicalSpecialityDescriptionExpected, medicalSpecialitySaved.getDescription());
		
	}

	@Test
	public void testUpdateMedicalSpecialityWithSuccess() throws Exception {
		
		MedicalSpeciality medicalSpecialityBefore = getMedicalSpecialityById(1l);

		medicalSpecialityBefore.setDescription("Cardiologista");
		
		medicalSpecialityServiceImpl.update(medicalSpecialityBefore);

		MedicalSpeciality medicalSpecialityUpdated = getMedicalSpecialityById(1l);

		String medicalSpecialityDescriptionExpected = "Cardiologista";
		
		assertEquals(medicalSpecialityDescriptionExpected, medicalSpecialityUpdated.getDescription());
		
	}

	@Test
	public void testDescriptionCannotBeNullMedicalSpecialityCannotBeSavedExceptionThrow(){
		try {
			medicalSpeciality.setDescription(null);
			medicalSpecialityServiceImpl.save(medicalSpeciality);
			fail("Not Expected result");
		} catch (Exception e) {
			assertTrue("expected result", true);
		}
	}

	@Test
	public void testDescriptionCannotBeEmptyMedicalSpecialityCannotBeSavedExceptionThrow(){
		try {
			medicalSpeciality.setDescription("");
			medicalSpecialityServiceImpl.save(medicalSpeciality);
			fail("Not Expected result");
		} catch (Exception e) {
			assertTrue("expected result", true);
		}
	}

	@Test
	public void testRemoveMedicalSpecialityWithSuccess() throws Exception {
		
		MedicalSpeciality medicalSpecialityToBeRemoved = getMedicalSpecialityById(1L);
		
		medicalSpecialityServiceImpl.delete(medicalSpecialityToBeRemoved);

		MedicalSpeciality medicalSpecialityRemoved = getMedicalSpecialityById(1L);

		assertNull(medicalSpecialityRemoved);

	}

	private MedicalSpeciality getNewMedicalSpeciality() {
		MedicalSpeciality m = new MedicalSpeciality();
		m.setDescription("Ginecologia");
		return m;
	}

	private MedicalSpeciality getMedicalSpecialityById(long id) throws Exception {
		MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(id);
		return medicalSpeciality;
	}

}
