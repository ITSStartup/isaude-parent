package com.its.isaude.core.impl.services;

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
	private MedicalSpecialityService medicalSpecialityService;
	
	private MedicalSpeciality medicalSpeciality;
	
	@Before
	public void setUp() throws Exception {
		medicalSpeciality = getNewMedicalSpeciality();
		getSetUpOperation().execute(getConnection(), getDataSet());
	}

	@Test
	public void testGetAllMedicalSpecialityRegistered() throws Exception {
		
		List<MedicalSpeciality> medicalSpecialities = medicalSpecialityService.list();

		assertNotNull(medicalSpecialities);
		assertFalse(medicalSpecialities.isEmpty());

	}

	@Test
	public void testSaveMedicalSpecialityWithSuccess() throws Exception {
		
		int beforeSaveQuantity = listAllMedicalSpecialities().size();
		
		medicalSpecialityService.save(medicalSpeciality);

		int afterSaveQuantity = listAllMedicalSpecialities().size();

		assertTrue(afterSaveQuantity > beforeSaveQuantity);

	}

	@Test
	public void testDescriptionCannotBeNullMedicalSpecialityCannotBeSavedExceptionThrow(){
		medicalSpeciality.setDescription(null);
		try {
			medicalSpecialityService.save(medicalSpeciality);
			fail("should be not executed");
		} catch (Exception e) {
			assertTrue("expected result", true);
		}
	}

	@Test
	public void testDescriptionCannotBeEmptyMedicalSpecialityCannotBeSavedExceptionThrow(){
		medicalSpeciality.setDescription("");
		try {
			medicalSpecialityService.save(medicalSpeciality);
			fail("should be not executed");
		} catch (Exception e) {
			assertTrue("expected result", true);
		}
	}

	@Test
	public void testRemoveMedicalSpecialityWithSuccess() throws Exception {
		
		int beforeSaveQuantity = listAllMedicalSpecialities().size();
		
		MedicalSpeciality medicalSpecialityToBeRemoved = getMedicalSpecialityById(1);
		
		medicalSpecialityService.delete(medicalSpecialityToBeRemoved);

		int afterSaveQuantity = listAllMedicalSpecialities().size();

		assertTrue(afterSaveQuantity < beforeSaveQuantity);

	}

	private MedicalSpeciality getNewMedicalSpeciality() {
		MedicalSpeciality m = new MedicalSpeciality();
		m.setDescription("SpecialityToTest");
		return m;
	}

	private List<MedicalSpeciality> listAllMedicalSpecialities() {
		return (List<MedicalSpeciality>) listAllEntitiesByType(getSessionFactory(), MedicalSpeciality.class);
	}

	private MedicalSpeciality getMedicalSpecialityById(long id) {
		return (MedicalSpeciality) getEntityByTypeAndId(getSessionFactory(), MedicalSpeciality.class, id);
	}

}
