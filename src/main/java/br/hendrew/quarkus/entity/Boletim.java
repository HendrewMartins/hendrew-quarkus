package br.hendrew.quarkus.entity;

public class Boletim {

	private String desc_aluno;
	private Double nota_1;
	private Double nota_2;
	private Double nota_3;
	private Double nota_4;
	private Long faltas;
	private Double media_final;
	private String situacao;

	public Boletim() {

	}

	public String getDesc_Aluno() {
		return desc_aluno;
	}

	public void setDesc_Aluno(String desc_aluno) {
		this.desc_aluno = desc_aluno;
	}

	public Double getNota_1() {
		return nota_1;
	}

	public void setNota_1(Double nota_1) {
		this.nota_1 = nota_1;
	}

	public Double getNota_2() {
		return nota_2;
	}

	public void setNota_2(Double nota_2) {
		this.nota_2 = nota_2;
	}

	public Double getNota_3() {
		return nota_3;
	}

	public void setNota_3(Double nota_3) {
		this.nota_3 = nota_3;
	}

	public Double getNota_4() {
		return nota_4;
	}

	public void setNota_4(Double nota_4) {
		this.nota_4 = nota_4;
	}

	public Long getFaltas() {
		return faltas;
	}

	public void setFaltas(Long faltas) {
		this.faltas = faltas;
	}

	public Double getMedia_final() {
		return media_final;
	}

	public void setMedia_final(Double media_final) {
		this.media_final = media_final;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
