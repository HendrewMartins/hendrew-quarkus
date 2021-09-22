package br.hendrew.quarkus.entity;

public class Bimestre_Angular {
    private long id;
	
	private long bimestre;
	private long ano;
	private long faltas;
	private Alunos id_Aluno;
    
    public Bimestre_Angular() {
	}

	public Alunos getId_Aluno() {
		return id_Aluno;
	}

	public void setId_Aluno(Alunos id_Aluno) {
		this.id_Aluno = id_Aluno;
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

}
