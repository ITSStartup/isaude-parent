package br.com.its.isaude.core.generic.impl.services;

import org.springframework.stereotype.Service;

import br.com.its.isaude.core.generic.interfaces.MedicalInstitutionService;
import br.com.its.isaude.core.generic.interfaces.dao.MedicalInstitutionDAO;
import br.com.its.isaude.core.model.domain.MedicalInstitutional;
@Service
public class MedicalInstitutionServiceImpl extends GenericServiceImpl<MedicalInstitutional, MedicalInstitutionDAO> implements MedicalInstitutionService {

}
