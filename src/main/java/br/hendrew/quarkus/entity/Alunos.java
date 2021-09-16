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

	public Date getDt_nasc(){
		return dt_nasc;
	}

	public void setDt_nasc(Date dt_nasc){
		this.dt_nasc = dt_nasc;
	}

	@Override
	public String toString() {
		return "Alunos [id=" + id + ", nome=" + nome +", dt_nasc=" + dt_nasc + "]";
	}
    

}
