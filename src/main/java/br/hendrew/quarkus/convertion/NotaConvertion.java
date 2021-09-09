package br.hendrew.quarkus.convertion;

import br.hendrew.quarkus.entity.Avaliacao;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.entity.Nota_Angular;

public class NotaConvertion {

    public NotaConvertion() {

    }

    public Nota_Angular convertionNota(Nota nota){
        Nota_Angular angular = new Nota_Angular();
        
        angular.setId(nota.getId());
        angular.setId_Aluno(nota.getBimestre().getAlunos().getId());
        angular.setId_Avaliacao(nota.getAvaliacao().getId());
        angular.setNota(nota.getNota());
        angular.setId_Bimestre(nota.getBimestre().getId());
        return angular;
    }

    public Nota notaConvertion(Nota_Angular angular, Bimestre bimestre, Avaliacao avaliacao){
        Nota nota = new Nota();

        if(angular.getId() > 0){
            nota.setId(angular.getId());
        }
        nota.setNota(angular.getNota());
        nota.setAvaliacao(avaliacao);
        nota.setBimestre(bimestre);
        
        return nota;
    }
    
}
