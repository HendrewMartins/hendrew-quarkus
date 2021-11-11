package br.hendrew.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import br.hendrew.quarkus.entity.Alunos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class AlunosRepository implements PanacheRepository<Alunos> {

    public List<Alunos> findByNome(String nome) {
        return find("lower(nome) like lower(:nome)", Parameters.with("nome", "%" + nome + "%")).list();
    }

    
}