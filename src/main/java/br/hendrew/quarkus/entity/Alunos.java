package br.hendrew.quarkus.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "alunos")
public class Alunos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nome", nullable = false)
	@Size(max = 100)
	private String nome;

	@Column(name = "dt_nasc", nullable = false)
	private Date dt_nasc;

	@Column(name = "nm_mae", nullable = false)
	private String nm_mae;

	@Column(name = "matricula", nullable = false)
	private String matricula;

	@Column(name = "nm_pai", nullable = false)
	private String nm_pai;

	@Column(name = "rg_aluno", nullable = false)
	private String rg_aluno;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	public Alunos() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	public String getNm_mae() {
		return nm_mae;
	}

	public void setNm_mae(String nm_mae) {
		this.nm_mae = nm_mae;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNm_pai() {
		return nm_pai;
	}

	public void setNm_pai(String nm_pai) {
		this.nm_pai = nm_pai;
	}

	public String getRg_aluno() {
		return rg_aluno;
	}

	public void setRg_aluno(String rg_aluno) {
		this.rg_aluno = rg_aluno;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Alunos [cpf=" + cpf + ", dt_nasc=" + dt_nasc + ", id=" + id + ", matricula=" + matricula + ", nm_mae="
				+ nm_mae + ", nm_pai=" + nm_pai + ", nome=" + nome + ", rg_aluno=" + rg_aluno + "]";
	}

}
