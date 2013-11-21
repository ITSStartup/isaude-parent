package br.com.its.isaude.core.model.domain;

// Generated 21/11/2013 15:00:51 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AgendaConsultaMedica generated by hbm2java
 */
@Entity
@Table(name = "agenda_consulta_medica")
public class AgendaConsultaMedica implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6610793708956177589L;
	private Integer id;
	private AgendaMedico agendaMedico;
	private Date dataConsulta;
	private Date horarioConsulta;
	private Set<AgendamentoConsultaMedicaPaciente> agendamentoConsultaMedicaPacientes = new HashSet<AgendamentoConsultaMedicaPaciente>();

	public AgendaConsultaMedica() {
	}

	public AgendaConsultaMedica(AgendaMedico agendaMedico) {
		this.agendaMedico = agendaMedico;
	}

	public AgendaConsultaMedica(AgendaMedico agendaMedico, Date dataConsulta,
			Date horarioConsulta, Set<AgendamentoConsultaMedicaPaciente> agendamentoConsultaMedicaPacientes) {
		this.agendaMedico = agendaMedico;
		this.dataConsulta = dataConsulta;
		this.horarioConsulta = horarioConsulta;
		this.agendamentoConsultaMedicaPacientes = agendamentoConsultaMedicaPacientes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENDA_MEDICO_ID", nullable = false)
	public AgendaMedico getAgendaMedico() {
		return this.agendaMedico;
	}

	public void setAgendaMedico(AgendaMedico agendaMedico) {
		this.agendaMedico = agendaMedico;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CONSULTA", length = 10)
	public Date getDataConsulta() {
		return this.dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "HORARIO_CONSULTA", length = 8)
	public Date getHorarioConsulta() {
		return this.horarioConsulta;
	}

	public void setHorarioConsulta(Date horarioConsulta) {
		this.horarioConsulta = horarioConsulta;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agendaConsultaMedica")
	public Set<AgendamentoConsultaMedicaPaciente> getAgendamentoConsultaMedicaPacientes() {
		return this.agendamentoConsultaMedicaPacientes;
	}

	public void setAgendamentoConsultaMedicaPacientes(Set<AgendamentoConsultaMedicaPaciente> agendamentoConsultaMedicaPacientes) {
		this.agendamentoConsultaMedicaPacientes = agendamentoConsultaMedicaPacientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agendaMedico == null) ? 0 : agendaMedico.hashCode());
		result = prime * result
				+ ((dataConsulta == null) ? 0 : dataConsulta.hashCode());
		result = prime * result
				+ ((horarioConsulta == null) ? 0 : horarioConsulta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AgendaConsultaMedica))
			return false;
		AgendaConsultaMedica other = (AgendaConsultaMedica) obj;
		if (agendaMedico == null) {
			if (other.agendaMedico != null)
				return false;
		} else if (!agendaMedico.equals(other.agendaMedico))
			return false;
		if (dataConsulta == null) {
			if (other.dataConsulta != null)
				return false;
		} else if (!dataConsulta.equals(other.dataConsulta))
			return false;
		if (horarioConsulta == null) {
			if (other.horarioConsulta != null)
				return false;
		} else if (!horarioConsulta.equals(other.horarioConsulta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
