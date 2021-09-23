package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Bimestre_Angular;
import br.hendrew.quarkus.entity.Bimestre_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface BimestreService {

    Bimestre_Angular getBimestreAngById(long id) throws MenssageNotFoundException;

    Bimestre getBimestreById(long id) throws MenssageNotFoundException;

    List<Bimestre> getBimestrePorAluno(long id) throws MenssageNotFoundException;

    List<Bimestre_Angular> getBimestrePorAluno_Angular(long id) throws MenssageNotFoundException;

    List<Bimestre> getAllBimestre();

    List<Bimestre_Auxiliar> getAllBimestreNmAluno();

    List<Bimestre_Auxiliar> getAllBimestreNmAlunoPage(int pag, int quant);

    Bimestre updateBimestre(long id, Bimestre_Angular bimestre_angular, Alunos alunos) throws MenssageNotFoundException;

    Bimestre saveBimestre(Bimestre_Angular bimestre_angular, Alunos alunos);

    void deleteBimestre(long id) throws MenssageNotFoundException;

    List<Bimestre_Auxiliar> getNotaBimestrePorAluno(long id) throws MenssageNotFoundException;

    long countBimestre();

}
