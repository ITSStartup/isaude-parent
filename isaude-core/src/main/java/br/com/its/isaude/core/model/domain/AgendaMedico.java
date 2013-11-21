package br.com.its.isaude.core.model.domain;

// Generated 21/11/2013 15:00:51 by Hibernate Tools 4.0.0

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

/**
 * AgendaMedico generated by hbm2java
 */
@Entity
@Table(name = "agenda_medico")
public class AgendaMedico implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -73381106725948111L;
	private Integer id;
	private Medico medico;
	private InstituicaoMedica instituicaoMedica;
	private String nomeAgenda;
	private Set<AgendaConsultaMedica> agendaConsultaMedicas = new HashSet<AgendaConsultaMedica>();

	public AgendaMedico() {
	}

	public AgendaMedico(Medico medico, InstituicaoMedica instituicaoMedica) {
		this.medico = medico;
		this.instituicaoMedica = instituicaoMedica;
	}

	public AgendaMedico(Medico medico, InstituicaoMedica instituicaoMedica,
			String nomeAgenda, Set<AgendaConsultaMedica> agendaConsultaMedicas) {
		this.medico = medico;
		this.instituicaoMedica = instituicaoMedica;
		this.nomeAgenda = nomeAgenda;
		this.agendaConsultaMedicas = agendaConsultaMedicas;
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
	@JoinColumn(name = "MEDICO_ID", nullable = false)
	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTITUICAO_MEDICA_ID", nullable = false)
	public InstituicaoMedica getInstituicaoMedica() {
		return this.instituicaoMedica;
	}

	public void setInstituicaoMedica(InstituicaoMedica instituicaoMedica) {
		this.instituicaoMedica = instituicaoMedica;
	}

	@Column(name = "NOME_AGENDA", length = 45)
	public String getNomeAgenda() {
		return this.nomeAgenda;
	}

	public void setNomeAgenda(String nomeAgenda) {
		this.nomeAgenda = nomeAgenda;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agendaMedico")
	public Set<AgendaConsultaMedica> getAgendaConsultaMedicas() {
		return this.agendaConsultaMedicas;
	}

	public void setAgendaConsultaMedicas(Set<AgendaConsultaMedica> agendaConsultaMedicas) {
		this.agendaConsultaMedicas = agendaConsultaMedicas;
	}

}
