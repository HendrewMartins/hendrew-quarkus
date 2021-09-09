package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Avaliacao;
import br.hendrew.quarkus.entity.Avaliacao_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface AvaliacaoService {
	
		Avaliacao getAvaliacaoById(long id) throws MenssageNotFoundException;

	    List<Avaliacao_Auxiliar> getAllAvaliacao();

	    Avaliacao updateAvaliacao(long id, Avaliacao avaliacao) throws MenssageNotFoundException;

	    Avaliacao saveAvaliacao(Avaliacao avaliacao);

	    void deleteAvaliacao(long id) throws MenssageNotFoundException;
}
