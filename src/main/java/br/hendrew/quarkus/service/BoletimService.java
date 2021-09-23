package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Boletim;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface BoletimService {

	List<Boletim> buscarboletim(Long ano) throws MenssageNotFoundException;

}
