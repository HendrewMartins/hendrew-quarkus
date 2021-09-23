package br.hendrew.quarkus.convertion;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Bimestre_Angular;

public class BimestreConvertion {

    public BimestreConvertion() {

    }

    public Bimestre_Angular convertionBimestre(Bimestre bimestre) {
        Bimestre_Angular angular = new Bimestre_Angular();

        angular.setId(bimestre.getId());
        angular.setAno(bimestre.getAno());
        angular.setBimestre(bimestre.getBimestre());
        angular.setFaltas(bimestre.getFaltas());
        angular.setId_Aluno(bimestre.getAlunos());
        return angular;
    }

    public Bimestre bimestreConvertion(Bimestre_Angular angular, Alunos alunos) {
        Bimestre bimestre = new Bimestre();
        if (angular.getId() > 0) {
            bimestre.setId(angular.getId());
        }
        bimestre.setAno(angular.getAno());
        bimestre.setBimestre(angular.getBimestre());
        bimestre.setFaltas(angular.getFaltas());
        bimestre.setAlunos(alunos);
        return bimestre;
    }

}
