package br.hendrew.quarkus.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.io.Serializable;

import javax.persistence.Column;


@Entity
@Table(name = "alunos_endereco")
public class AlunosEndereco implements Serializable{
    
    @Id
    private long sequencia;

    @Id
    @ManyToOne
	@JoinColumn(name="id_aluno")
    private Alunos aluno;

    @Column(name = "tipo", nullable = false)
    private long tipo;

    @Column(name = "logradouro", nullable = false)
    @Size(max = 100)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    @Size(max = 10)
    private String numero;

    @Column(name = "cep", nullable = false)
    @Size(max = 10)
    private String cep;

    @Column(name = "bairro", nullable = false)
    @Size(max = 50)
    private String bairro;

    @Column(name="complemento", nullable = false)
    @Size(max = 100)
    private String complemento;

    public AlunosEndereco() {
    }

    public long getSequencia() {
        return sequencia;
    }

    public void setSequencia(long sequencia) {
        this.sequencia = sequencia;
    }

    public Alunos getAlunos() {
        return aluno;
    }

    public void setAlunos(Alunos aluno) {
        this.aluno = aluno;
    }

    public long getTipo() {
        return tipo;
    }

    public void setTipo(long tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

   
    @Override
    public String toString() {
        return "AlunosEndereco [bairro=" + bairro + ", cep=" + cep + ", complemento=" + complemento + ", alunos="
                + aluno + ", logradouro=" + logradouro + ", numero=" + numero + ", sequencia=" + sequencia
                + ", tipo=" + tipo + "]";
    }



}
