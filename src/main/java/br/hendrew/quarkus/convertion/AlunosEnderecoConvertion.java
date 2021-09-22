package br.hendrew.quarkus.convertion;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosEndereco;
import br.hendrew.quarkus.entity.AlunosEndereco_Auxiliar;

public class AlunosEnderecoConvertion {
    public AlunosEnderecoConvertion() {

    }

    public AlunosEndereco_Auxiliar convertionAlunosEndereco(AlunosEndereco endereco) {
        AlunosEndereco_Auxiliar angular = new AlunosEndereco_Auxiliar();
        String tipodesc = "";

        angular.setBairro(endereco.getBairro());
        angular.setCep(endereco.getCep());
        angular.setComplemento(endereco.getComplemento());
        angular.setLogradouro(endereco.getLogradouro());
        angular.setNumero(endereco.getNumero());
        angular.setSequencia(endereco.getSequencia());

        long tipo = endereco.getTipo();

        if (tipo == 0) {
            tipodesc = "Residencial";
        } else if (tipo == 1) {
            tipodesc = "Comercial";
        } else if (tipo == 2) {
            tipodesc = "Correspondência";
        }

        angular.setTipo(tipodesc);
        return angular;
    }

    public AlunosEndereco alunosEnderecoConvertion(AlunosEndereco_Auxiliar angular, Alunos alunos) {
        AlunosEndereco endereco = new AlunosEndereco();
        long tipo = 0;

        endereco.setAlunos(alunos);
        endereco.setBairro(angular.getBairro());
        endereco.setCep(angular.getCep());
        endereco.setComplemento(angular.getComplemento());
        endereco.setLogradouro(angular.getLogradouro());
        endereco.setNumero(angular.getNumero());
        endereco.setSequencia(angular.getSequencia());

        String tipodesc = angular.getTipo();
        if (tipodesc.equals("Residencial")) {
            tipo = 0;
        } else if (tipodesc.equals("Comercial")) {
            tipo = 1;
        } else if (tipodesc.equals("Correspondência")) {
            tipo = 2;
        }

        endereco.setTipo(tipo);
        return endereco;
    }
}
