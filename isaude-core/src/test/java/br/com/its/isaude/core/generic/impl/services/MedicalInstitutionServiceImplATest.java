package br.com.its.isaude.core.generic.impl.services;


import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.its.isaude.core.dbunit.config.DBUnitConfiguration;
import br.com.its.isaude.core.exception.MedicalInstitutionException;
import br.com.its.isaude.core.generic.interfaces.MedicalInstitutionService;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;



@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalInstitutionServiceImplATest extends DBUnitConfiguration {

	@Autowired
	@Qualifier("medicalInstitutionServiceImpl")
	private MedicalInstitutionService medicalInstitutionServiceImpl;
	private MedicalInstitutional medicalInstitutional;
	
	@Before
	public void setUp() throws Exception {
		medicalInstitutional = new MedicalInstitutional();
		medicalInstitutional.setNomeFantasia("Hospital Espanhol");
		final String cnpj =  "66863152000126";
		medicalInstitutional.setCnpj(cnpj);
		medicalInstitutional.setRazaoSocial("Grupo Espanhol Bahia ltda");
		getSetUpOperation().execute(getConnection(), getDataSet());
		
	}

	@Test
	public void testMedicalInstitutionalWasSavedWithSuccess() {
		try{
			medicalInstitutionServiceImpl.save(medicalInstitutional);
		}catch(Exception e){
			fail("Not Expected result");
		}
	}
	
	@Test
	public void testNameFantasyCannotBeNullMedicalInstitutionalCannotBeSavedExcpectionThrow(){
		try {
			medicalInstitutional.setNomeFantasia(null);
			medicalInstitutionServiceImpl.save(medicalInstitutional);
			fail("Not Expected result");
		} catch (Exception e) {
			assertTrue("Expected result", true);
		}
	}
	@Test
	public void testRazaoSocialCannotBeNullMedicalInstitutionalCannotSavedExceptionThrow(){
		medicalInstitutional.setRazaoSocial(null);
		try {
			medicalInstitutionServiceImpl.save(medicalInstitutional);
			fail("should be not executed");
		} catch (Exception e) {
			assertTrue("expected result", true);
		}
	}
	
	@Test
	public void testCNPJCannotBeNullMedicalInstitutionalCannotBeSavedExceptionThrow(){
		medicalInstitutional.setCnpj(null);
		try {
			medicalInstitutionServiceImpl.save(medicalInstitutional);
		} catch (Exception e) {
			assertTrue("expected Result",true);
		}
	}

	@Test
	public void testGetAllMedicalInstitution() throws Exception {
		assertFalse(medicalInstitutionServiceImpl.list().isEmpty());
	}
	
	@Test
	public void testCannotSaveMedicalInstitutionCNPJIsInvalidItShort(){
		final String cnpjInvalidShort = "184956620001";
		medicalInstitutional.setCnpj(cnpjInvalidShort);
		try {
			medicalInstitutionServiceImpl.save(medicalInstitutional);
			fail("Should not be executed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCNPJCannotBeAlphanumericThrowException(){
		String cnpjInvalidAlphanumeric = "36565A2557012";
		medicalInstitutional.setCnpj(cnpjInvalidAlphanumeric);
		try {
			medicalInstitutionServiceImpl.save(medicalInstitutional);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	
	@Test
	public void testGetMedicalInstitutionalByCnpj(){
		MedicalInstitutional medicalInstitutional = medicalInstitutionServiceImpl.getByCnpj("18495662000145");
		Long idExpected = 1L;
		assertEquals(idExpected,medicalInstitutional.getId());
	}
	
	@Test(expected=MedicalInstitutionException.class)
	public void testCannotAddMedicalInstituicionalCnpjBecauseItAlreadyExistThrowMedicalInstitutionException() throws Exception{
		medicalInstitutional.setCnpj("18495662000145");
		medicalInstitutionServiceImpl.save(medicalInstitutional);
	}
	@Ignore
	@Test(expected=MedicalInstitutionException.class)
	public void testCannotAddMedicalInstituicionalRazaoSocialAlreadyExist() throws Exception{
		String razaoSocial = "hospital holandes SA";
		medicalInstitutional.setRazaoSocial(razaoSocial );
		medicalInstitutionServiceImpl.save(medicalInstitutional);
		
	}
	@Test
	public void testUpdateNameFantasyWithSuccess() throws Exception{
		MedicalInstitutional medInstitutional = medicalInstitutionServiceImpl.getById(1L);
		final String newNameFantasy = "Hospital Espanhol";
		medInstitutional.setNomeFantasia(newNameFantasy);
 		medicalInstitutionServiceImpl.update(medInstitutional);
 		assertEquals(newNameFantasy, medicalInstitutionServiceImpl.getById(1L).getNomeFantasia());
	}
	@Test
	public void testGetListMedicalInstitutionalOrderDescById() throws Exception{
		List<MedicalInstitutional> listMedicalInstitutional = medicalInstitutionServiceImpl.list();
		Long idExpected = 2L;
		assertEquals(idExpected , listMedicalInstitutional.get(0).getId());
	}

}
