package br.hendrew.quarkus.entity;

public class Nota_Auxiliar {

	private long id;
	private long id_Avaliacao;
	private long id_Bimestre;
	private double notas;
	private long id_Aluno;
	private long ano;
	private String desc_avaliacao;
	private String desc_bimestre;
	private String desc_aluno;
	
	
	public String getDesc_avaliacao() {
		return desc_avaliacao;
	}

	public void setDesc_avaliacao(String desc_avaliacao) {
		this.desc_avaliacao = desc_avaliacao;
	}

	public long getAno() {
		return ano;
	}

	public void setAno(long ano) {
		this.ano = ano;
	}

	public String getDesc_Aluno() {
		return desc_aluno;
	}

	public void setDesc_Aluno(String desc_aluno) {
		this.desc_aluno = desc_aluno;
	}

	public Nota_Auxiliar() {
	}
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_Avaliacao() {
		return id_Avaliacao;
	}

	public void setId_Avaliacao(long id_Avaliacao) {
		this.id_Avaliacao = id_Avaliacao;
	}
		
	public double getNotas() {
		return notas;
	}
	public void setNotas(double notas) {
		this.notas = notas;
	}

	public long getId_Bimestre() {
		return id_Bimestre;
	}

	public void setId_Bimestre(long id_Bimestre) {
		this.id_Bimestre = id_Bimestre;
	}

	public long getId_Aluno() {
		return id_Aluno;
	}

	public void setId_Aluno(long id_Aluno) {
		this.id_Aluno = id_Aluno;
	}

	public String getDesc_bimestre() {
		return desc_bimestre;
	}

	public void setDesc_bimestre(String desc_bimestre) {
		this.desc_bimestre = desc_bimestre;
	}
}
