package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.exception.AlunosNotFoundException;



public interface AlunosService {

	
    Alunos getAlunosById(long id) throws AlunosNotFoundException;

    List<Alunos> getAllAlunos();

    Alunos updateAlunos(long id, Alunos alunos) throws AlunosNotFoundException;

    Alunos saveAlunos(Alunos alunos);

    void deleteAluno(long id) throws AlunosNotFoundException;
}
