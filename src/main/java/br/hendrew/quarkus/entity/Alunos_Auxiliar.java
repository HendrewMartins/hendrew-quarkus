package br.hendrew.quarkus.entity;

public class Alunos_Auxiliar {
   
    private long id;

    private String nome;

    private String dt_nasc;

    private String nm_mae;

    private String matricula;

    private String nm_pai;
    
    private String rg_aluno;

    private String cpf;

    
    public Alunos_Auxiliar() {
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

	public String getDt_nasc(){
		return dt_nasc;
	}

	public void setDt_nasc(String dt_nasc){
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
		return "Alunos_Auxiliar [cpf=" + cpf + ", dt_nasc=" + dt_nasc + ", id=" + id + ", matricula=" + matricula
				+ ", nm_mae=" + nm_mae + ", nm_pai=" + nm_pai + ", nome=" + nome + ", rg_aluno=" + rg_aluno + "]";
	}

}
