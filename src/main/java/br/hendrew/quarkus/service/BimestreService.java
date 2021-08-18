package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface BimestreService {

	Bimestre getBimestreById(long id) throws MenssageNotFoundException;
	
	List<Bimestre> getBimestrePorAluno(Alunos alunos) throws MenssageNotFoundException;

    List<Bimestre> getAllBimestre();

    Bimestre updateBimestre(long id, Bimestre bimestre) throws MenssageNotFoundException;

    Bimestre saveBimestre(Bimestre bimestre);

    void deleteBimestre(long id) throws MenssageNotFoundException;
}
