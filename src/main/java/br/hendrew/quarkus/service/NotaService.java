package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface NotaService {

	Nota getNotaById(long id) throws MenssageNotFoundException;
	
	List<Nota> getNotaPorBimestre(Bimestre bimestre) throws MenssageNotFoundException;

    List<Nota> getAllNota();

    Nota updateNota(long id, Nota nota) throws MenssageNotFoundException;

    Nota saveNota(Nota nota);

    void deleteNota(long id) throws MenssageNotFoundException;

}
