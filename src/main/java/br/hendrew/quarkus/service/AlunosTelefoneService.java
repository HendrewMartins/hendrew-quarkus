package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.AlunosTelefone;
import br.hendrew.quarkus.entity.AlunosTelefone_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface AlunosTelefoneService {

    List<AlunosTelefone_Auxiliar> getTelefonePorAluno(long id) throws MenssageNotFoundException;

    AlunosTelefone saveTelefone(AlunosTelefone_Auxiliar alunostelefoneaux, long alunos) throws MenssageNotFoundException;

}
