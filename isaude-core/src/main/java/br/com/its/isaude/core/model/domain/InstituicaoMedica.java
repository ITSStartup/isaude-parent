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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * InstituicaoMedica generated by hbm2java
 */
@Entity
@Table(name = "instituicao_medica")
public class InstituicaoMedica implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4637365955190772684L;
	private Integer id;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private Set<AgendaMedico> agendaMedicos = new HashSet<AgendaMedico>();
	private Set<Medico> medicos = new HashSet<Medico>();

	public InstituicaoMedica() {
	}

	public InstituicaoMedica(String nomeFantasia, String razaoSocial,
			String cnpj, Set<AgendaMedico> agendaMedicos, Set<Medico> medicos) {
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.agendaMedicos = agendaMedicos;
		this.medicos = medicos;
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

	@Column(name = "NOME FANTASIA", length = 80)
	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Column(name = "RAZAO_SOCIAL", length = 45)
	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Column(name = "CNPJ", length = 45)
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instituicaoMedica")
	public Set<AgendaMedico> getAgendaMedicos() {
		return this.agendaMedicos;
	}

	public void setAgendaMedicos(Set<AgendaMedico> agendaMedicos) {
		this.agendaMedicos = agendaMedicos;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "instituicao_medica_has_medico",  joinColumns = { @JoinColumn(name = "INSTITUICAO_MEDICA_ID", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "MEDICO_ID", nullable = false, updatable = false) })
	public Set<Medico> getMedicos() {
		return this.medicos;
	}

	public void setMedicos(Set<Medico> medicos) {
		this.medicos = medicos;
	}

}