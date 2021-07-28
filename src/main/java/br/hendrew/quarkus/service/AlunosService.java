package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.exception.MenssageNotFoundException;



public interface AlunosService {

	
    Alunos getAlunosById(long id) throws MenssageNotFoundException;

    List<Alunos> getAllAlunos();

    Alunos updateAlunos(long id, Alunos alunos) throws MenssageNotFoundException;

    Alunos saveAlunos(Alunos alunos);

    void deleteAluno(long id) throws MenssageNotFoundException;
}
