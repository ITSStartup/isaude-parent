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
	public void testGetEachOneMedicalSpecialityRegistered() throws Exception {
		
		MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(1l);
		
		String medicalSpecialityCardiologia = medicalSpeciality.getDescription();
		
		medicalSpeciality = medicalSpecialityServiceImpl.getById(2l);
		
		String medicalSpecialityGinecologia = medicalSpeciality.getDescription();

		medicalSpeciality = medicalSpecialityServiceImpl.getById(3l);
		
		String medicalSpecialityUrologia = medicalSpeciality.getDescription();

		medicalSpeciality = medicalSpecialityServiceImpl.getById(4l);
		
		String medicalSpecialityGeriatria = medicalSpeciality.getDescription();

		assertEquals("Cardiologia", medicalSpecialityCardiologia);
		
		assertEquals("Ginecologia", medicalSpecialityGinecologia);
		
		assertEquals("Urologia", medicalSpecialityUrologia);
		
		assertEquals("Geriatria", medicalSpecialityGeriatria);
		
	}

	@Test
	public void testSaveMedicalSpecialityWithSuccess() throws Exception {
		
		int beforeSaveQuantity = listAllMedicalSpecialities().size();
		
		medicalSpecialityServiceImpl.save(medicalSpeciality);

		MedicalSpeciality medicalSpecialitySaved = getMedicalSpecialityById(medicalSpeciality.getId());

		int afterSaveQuantity = listAllMedicalSpecialities().size();

		assertTrue(afterSaveQuantity > beforeSaveQuantity);
		
		assertEquals("Ginecologia", medicalSpecialitySaved.getDescription());
		
	}

	@Test
	public void testUpdateMedicalSpecialityWithSuccess() throws Exception {
		
		int beforeSaveQuantity = listAllMedicalSpecialities().size();
		
		MedicalSpeciality medicalSpecialityBefore = getMedicalSpecialityById(1l);

		medicalSpecialityBefore.setDescription("Cardiologista");
		
		medicalSpecialityServiceImpl.update(medicalSpecialityBefore);

		MedicalSpeciality medicalSpecialityUpdated = getMedicalSpecialityById(1l);

		int afterSaveQuantity = listAllMedicalSpecialities().size();

		assertEquals(afterSaveQuantity, beforeSaveQuantity);
		
		assertEquals("Cardiologista", medicalSpecialityUpdated.getDescription());
		
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
		
		int beforeSaveQuantity = listAllMedicalSpecialities().size();
		
		MedicalSpeciality medicalSpecialityToBeRemoved = getMedicalSpecialityById(1L);
		
		medicalSpecialityServiceImpl.delete(medicalSpecialityToBeRemoved);

		MedicalSpeciality medicalSpecialityRemoved = getMedicalSpecialityById(1L);

		int afterSaveQuantity = listAllMedicalSpecialities().size();

		assertTrue(afterSaveQuantity < beforeSaveQuantity);
		
		assertNull(medicalSpecialityRemoved);

	}

	private MedicalSpeciality getNewMedicalSpeciality() {
		MedicalSpeciality m = new MedicalSpeciality();
		m.setDescription("Ginecologia");
		return m;
	}

	private List<MedicalSpeciality> listAllMedicalSpecialities() throws Exception {
		List<MedicalSpeciality> list = medicalSpecialityServiceImpl.list();
		return list;
	}

	private MedicalSpeciality getMedicalSpecialityById(long id) throws Exception {
		MedicalSpeciality medicalSpeciality = medicalSpecialityServiceImpl.getById(id);
		return medicalSpeciality;
	}

}