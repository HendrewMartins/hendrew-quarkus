package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosEndereco;
import br.hendrew.quarkus.entity.AlunosEndereco_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface AlunosEnderecoService {
    AlunosEndereco getEnderecoAngById(long id) throws MenssageNotFoundException;

    AlunosEndereco getEnderecoById(long id) throws MenssageNotFoundException;
	
	List<AlunosEndereco_Auxiliar> getEnderecoPorAluno(long id) throws MenssageNotFoundException;

    List<AlunosEndereco> getEnderecoPorAluno_Angular(long id) throws MenssageNotFoundException;

    List<AlunosEndereco> getAllEndereco();

    AlunosEndereco updateEndereco(long id, AlunosEndereco alunosendereco , Alunos alunos) throws MenssageNotFoundException;

    AlunosEndereco saveEndereco(AlunosEndereco_Auxiliar alunosenderecoaux, long alunos) throws MenssageNotFoundException;

    void deleteEndereco(long id) throws MenssageNotFoundException;

    List<AlunosEndereco> getNotaBimestrePorAluno(long id) throws MenssageNotFoundException;
}
