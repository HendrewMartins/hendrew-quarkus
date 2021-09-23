package br.hendrew.quarkus.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosEndereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AlunosEnderecoRepository implements PanacheRepository<AlunosEndereco> {

    public List<AlunosEndereco> findByAlunos(Alunos aluno) {
        return list("aluno", aluno);
    }
}
