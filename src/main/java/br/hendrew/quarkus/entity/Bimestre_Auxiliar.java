package br.hendrew.quarkus.entity;

public class Bimestre_Auxiliar {
	private long id;

	private long bimestre;
	private String desc_bimestre;
	private long ano;
	private long faltas;
	private long id_alunos;
	private String nm_alunos;

	public Bimestre_Auxiliar() {
	}

	public long getId_Alunos() {
		return id_alunos;
	}

	public void setId_Alunos(long id_alunos) {
		this.id_alunos = id_alunos;
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

	public String getNm_Alunos() {
		return nm_alunos;
	}

	public void setNm_Alunos(String nm_alunos) {
		this.nm_alunos = nm_alunos;
	}

	public String getDesc_bimestre() {
		return desc_bimestre;
	}

	public void setDesc_bimestre(String desc_bimestre) {
		this.desc_bimestre = desc_bimestre;
	}
}
