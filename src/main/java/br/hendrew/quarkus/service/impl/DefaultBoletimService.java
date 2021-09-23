package br.hendrew.quarkus.service.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Avaliacao;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Boletim;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.service.AlunosService;
import br.hendrew.quarkus.service.AvaliacaoService;
import br.hendrew.quarkus.service.BimestreService;
import br.hendrew.quarkus.service.BoletimService;
import br.hendrew.quarkus.service.NotaService;

@ApplicationScoped
public class DefaultBoletimService implements BoletimService {

	@Inject
	private NotaService servicenotas;
	@Inject
	private AlunosService servicealunos;
	@Inject
	private BimestreService servicebimestre;
	@Inject
	private AvaliacaoService serviceavaliacao;

	@Override
	public List<Boletim> buscarboletim(Long ano) throws MenssageNotFoundException {
		List<Boletim> lista = new ArrayList<Boletim>();
		List<Alunos> alunos = servicealunos.getAllAlunos();

		for (int i = 0; i < alunos.size(); i++) {
			Double nota1 = 0.00;
			Double nota2 = 0.00;
			Double nota3 = 0.0;
			Double nota4 = 0.0;
			Double notafinal = 0.0;
			long faltas = 0;
			long cont = 0;
			long letivo = 0;

			Boletim boletim = new Boletim();
			boletim.setDesc_Aluno(alunos.get(i).getNome());

			List<Bimestre> bimestre = servicebimestre.getBimestrePorAluno(alunos.get(i).getId());

			for (int a = 0; a < bimestre.size(); a++) {
				if (ano == bimestre.get(a).getAno()) {
					faltas = faltas + bimestre.get(a).getFaltas();
					Double nota_aux = 0.0;
					List<Nota> notas = servicenotas.getNotaPorBimestre(bimestre.get(a));

					for (int b = 0; b < notas.size(); b++) {
						Avaliacao avaliacao = serviceavaliacao.getAvaliacaoById(notas.get(b).getAvaliacao().getId());
						Double nota_temp = (notas.get(b).getNota() * avaliacao.getPeso()) / 10;
						nota_aux = nota_aux + nota_temp;
					}

					if (bimestre.get(a).getBimestre() == 1) {
						cont = cont + 1;
						letivo = letivo + 40;
						nota1 = nota_aux;
					} else if (bimestre.get(a).getBimestre() == 2) {
						cont = cont + 1;
						letivo = letivo + 40;
						nota2 = nota_aux;
					} else if (bimestre.get(a).getBimestre() == 3) {
						cont = cont + 1;
						letivo = letivo + 40;
						nota3 = nota_aux;
					} else if (bimestre.get(a).getBimestre() == 4) {
						cont = cont + 1;
						letivo = letivo + 40;
						nota4 = nota_aux;
					}
				}

			}
			if (cont > 0) {
				notafinal = (nota1 + nota2 + nota3 + nota4) / cont;
				DecimalFormat formato = new DecimalFormat("##.##", new DecimalFormatSymbols(new Locale("en", "USA")));
				notafinal = Double.valueOf(formato.format(notafinal));
			}

			Double mediafaltas = 0.0;

			if (letivo > 0) {
				mediafaltas = (double) (100 - ((faltas / letivo) * 100));
			}

			if (notafinal >= 6.0 && mediafaltas >= 75) {
				boletim.setSituacao("Aprovado");
			} else if ((notafinal >= 5.0 && notafinal < 6.0) && mediafaltas >= 75) {
				boletim.setSituacao("Recuperação");
			} else {
				boletim.setSituacao("Reprovado");
			}
			DecimalFormat formato = new DecimalFormat("##.##", new DecimalFormatSymbols(new Locale("en", "USA")));
			nota1 = Double.valueOf(formato.format(nota1));
			boletim.setNota_1(nota1);

			nota2 = Double.valueOf(formato.format(nota2));
			boletim.setNota_2(nota2);

			nota3 = Double.valueOf(formato.format(nota3));
			boletim.setNota_3(nota3);

			nota4 = Double.valueOf(formato.format(nota4));
			boletim.setNota_4(nota4);

			boletim.setFaltas(faltas);
			boletim.setMedia_final(notafinal);
			lista.add(i, boletim);
		}
		return lista;
	}

}
