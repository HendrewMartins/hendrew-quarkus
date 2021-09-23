package br.hendrew.quarkus.entity;

public class Avaliacao_Auxiliar {

	private long id;

	private String descricao;

	private Double peso;

	public Avaliacao_Auxiliar() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Avaliacao [id=" + id + ", descricao=" + descricao + ", peso=" + peso + "]";
	}
}
