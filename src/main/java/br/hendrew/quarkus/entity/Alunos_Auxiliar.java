package br.hendrew.quarkus.entity;

public class Alunos_Auxiliar {
   
    private long id;

    private String nome;

    private String dt_nasc;
    
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

	@Override
	public String toString() {
		return "Alunos [id=" + id + ", nome=" + nome +", dt_nasc=" + dt_nasc + "]";
	}
}
