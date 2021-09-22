package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosEndereco_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface AlunosEnderecoService {

    List<AlunosEndereco_Auxiliar> getEnderecoPorAluno(long id) throws MenssageNotFoundException;

    void saveEndereco(AlunosEndereco_Auxiliar alunosenderecoaux, Alunos alunos)
            throws MenssageNotFoundException;

    void deleteEnderecoAluno(long id) throws MenssageNotFoundException;        

}
