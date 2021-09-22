package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosTelefone_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface AlunosTelefoneService {

    List<AlunosTelefone_Auxiliar> getTelefonePorAluno(long id) throws MenssageNotFoundException;

    void saveTelefone(AlunosTelefone_Auxiliar alunostelefoneaux, Alunos alunos) throws MenssageNotFoundException;

    void deleteTelefoneAluno(long id) throws MenssageNotFoundException;
}
