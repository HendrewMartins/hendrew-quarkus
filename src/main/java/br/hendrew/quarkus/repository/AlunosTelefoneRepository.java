package br.hendrew.quarkus.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosTelefone;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AlunosTelefoneRepository implements PanacheRepository<AlunosTelefone>{
    
    public List<AlunosTelefone> findByAlunos(Alunos aluno){
        return list("aluno", aluno);
    }
}
