package br.hendrew.quarkus.entity;

public class Nota_Angular {
    
    private long id;
	
	private long id_Avaliacao;
	private double nota;
	private long id_Bimestre;
	private Alunos id_Aluno;
	
    public Nota_Angular(){

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
		
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}

	public long getId_Bimestre() {
		return id_Bimestre;
	}

	public void setId_Bimestre(long id_Bimestre) {
		this.id_Bimestre = id_Bimestre;
	}

	public Alunos getId_Aluno() {
		return id_Aluno;
	}

	public void setId_Aluno(Alunos id_aluno) {
		this.id_Aluno = id_aluno;
	}
}
