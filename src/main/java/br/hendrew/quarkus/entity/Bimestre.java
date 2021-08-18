package br.hendrew.quarkus.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bimestre_new")
public class Bimestre {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "bimestre", nullable = false)
	private long bimestre;
	
	@Column(name = "ano", nullable = false)
	private long ano;
	
	@Column(name = "faltas", nullable = false)
	private long faltas;
	
	@ManyToOne
	@JoinColumn(name="id_alunos")
	private Alunos alunos;

	public Bimestre() {
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getBimestre() {
		return bimestre;
	}

	public void setBimestre(long bimestre) {
		this.bimestre = bimestre;
	}

	public long getAno() {
		return ano;
	}

	public void setAno(long ano) {
		this.ano = ano;
	}

	public long getFaltas() {
		return faltas;
	}

	public void setFaltas(long faltas) {
		this.faltas = faltas;
	}

	public Alunos getAlunos() {
		return alunos;
	}

	public void setAlunos(Alunos alunos) {
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		return "Bimestre [id=" + id + ", bimestre=" + bimestre + ", ano=" + ano + ", faltas=" + faltas + ", alunos="
				+ alunos + "]";
	}

	
}
