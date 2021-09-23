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
@Table(name = "notas_new")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_avaliacao")
	private Avaliacao avaliacao;

	@Column(name = "nota", nullable = false)
	private double nota;

	@ManyToOne
	@JoinColumn(name = "id_bimestre")
	private Bimestre bimestre;

	public Nota() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Bimestre getBimestre() {
		return bimestre;
	}

	public void setBimestre(Bimestre bimestre) {
		this.bimestre = bimestre;
	}

	@Override
	public String toString() {
		return "Notas [id=" + id + ", avaliacao=" + avaliacao + ", nota=" + nota + ", bimestre=" + bimestre + "]";
	}

}
