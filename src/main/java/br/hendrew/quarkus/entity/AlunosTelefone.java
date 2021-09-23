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
@Table(name = "alunos_telefone")
public class AlunosTelefone implements Serializable {

    @Id
    private long sequencia;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Alunos aluno;

    @Column(name = "tipo", nullable = false)
    private long tipo;

    @Column(name = "numero", nullable = false)
    @Size(max = 20)
    private String numero;

    @Column(name = "contato", nullable = false)
    @Size(max = 60)
    private String contato;

    public AlunosTelefone() {
    }

    public long getSequencia() {
        return sequencia;
    }

    public void setSequencia(long sequencia) {
        this.sequencia = sequencia;
    }

    public Alunos getAluno() {
        return aluno;
    }

    public void setAluno(Alunos aluno) {
        this.aluno = aluno;
    }

    public long getTipo() {
        return tipo;
    }

    public void setTipo(long tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "AlunosTelefone [aluno=" + aluno + ", contato=" + contato + ", numero=" + numero + ", sequencia="
                + sequencia + ", tipo=" + tipo + "]";
    }

}
