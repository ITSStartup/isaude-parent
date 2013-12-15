package br.com.its.isaude.core.impl.dao;

import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.impl.dao.GenericHibernateDAO;
import br.com.its.isaude.core.interfaces.dao.WorktimeDoctorDAO;
import br.com.its.isaude.core.modal.domain.Doctor;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
import br.com.its.isaude.core.modal.domain.WorktimeDoctor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

@Repository
@SuppressWarnings("unchecked")
public class WorktimeDoctorDAOImpl extends GenericHibernateDAO<WorktimeDoctor> implements WorktimeDoctorDAO {

    public WorktimeDoctorDAOImpl() {
        super(WorktimeDoctor.class);
    }

    @Override
    public List<WorktimeDoctor> listByDoctorAndMedicalInstitutional(Doctor doctor, MedicalInstitutional medicalInstitutional) {
        List<WorktimeDoctor> worktimeDoctors = getCurrentSession()
            .createCriteria(WorktimeDoctor.class)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .setFetchMode("medico", FetchMode.JOIN)
            .setFetchMode("instituicaoMedica", FetchMode.JOIN)
            .setFetchMode("medico.especialidadeMedicas", FetchMode.JOIN)
            .setFetchMode("medico.instituicaoMedicas", FetchMode.JOIN)
            .setFetchMode("medico.expedienteMedicos", FetchMode.JOIN)
            .add(Restrictions.eq("medico.id", doctor.getId()))
            .add(Restrictions.eq("instituicaoMedica.id", medicalInstitutional.getId()))
            .list();
        return worktimeDoctors;
    }

    @Override
    public List<WorktimeDoctor> checkConflictBetweenWorktimeDoctor(WorktimeDoctor worktimeDoctor) {
        
        Criteria criteria = getCurrentSession().createCriteria(WorktimeDoctor.class);
        
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        criteria.add(Restrictions.eq("medico.id", worktimeDoctor.getMedico().getId()));
        
        criteria.add(Restrictions.ne("id", worktimeDoctor.getId()));
        
        Disjunction disjunction = Restrictions.disjunction();
        
        disjunction.add(Restrictions.conjunction()
            .add(Restrictions.le("horarioInicial", worktimeDoctor.getHorarioInicial()))
            .add(Restrictions.gt("horarioFinal", worktimeDoctor.getHorarioInicial()))
        );
        
        disjunction.add(Restrictions.conjunction()
            .add(Restrictions.lt("horarioInicial", worktimeDoctor.getHorarioFinal()))
            .add(Restrictions.ge("horarioFinal", worktimeDoctor.getHorarioFinal()))
        );
        
        disjunction.add(Restrictions.conjunction()
            .add(Restrictions.gt("horarioInicial", worktimeDoctor.getHorarioInicial()))
            .add(Restrictions.lt("horarioFinal", worktimeDoctor.getHorarioFinal()))
        );

        criteria.add(disjunction);

        List<WorktimeDoctor> list = criteria.list();
        
        return list;
        
    }

}
