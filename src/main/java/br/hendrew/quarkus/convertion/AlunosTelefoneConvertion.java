package br.hendrew.quarkus.convertion;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosTelefone;
import br.hendrew.quarkus.entity.AlunosTelefone_Auxiliar;

public class AlunosTelefoneConvertion {
    public AlunosTelefoneConvertion() {

    }

    public AlunosTelefone_Auxiliar convertionAlunosTelefone(AlunosTelefone telefone) {
        AlunosTelefone_Auxiliar angular = new AlunosTelefone_Auxiliar();
        String tipodesc = "";

        angular.setContato(telefone.getContato());
        angular.setNumero(telefone.getNumero());
        angular.setSequencia(telefone.getSequencia());

        long tipo = telefone.getTipo();

        if (tipo == 0) {
            tipodesc = "Celular";
        } else if (tipo == 1) {
            tipodesc = "Fixo";
        } else if (tipo == 2) {
            tipodesc = "Contato";
        }

        angular.setTipo(tipodesc);
        return angular;
    }

    public AlunosTelefone alunosEnderecoConvertion(AlunosTelefone_Auxiliar angular, Alunos alunos) {
        AlunosTelefone telefone = new AlunosTelefone();
        long tipo = 0;

        telefone.setAluno(alunos);
        telefone.setContato(angular.getContato());
        telefone.setNumero(angular.getNumero());
        telefone.setSequencia(angular.getSequencia());

        String tipodesc = angular.getTipo();
        if (tipodesc.equals("Celular")) {
            tipo = 0;
        } else if (tipodesc.equals("Fixo")) {
            tipo = 1;
        } else if (tipodesc.equals("Contato")) {
            tipo = 2;
        }

        telefone.setTipo(tipo);
        return telefone;
    }

}
