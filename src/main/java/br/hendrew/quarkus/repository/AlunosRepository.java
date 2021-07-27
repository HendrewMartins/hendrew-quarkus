package br.hendrew.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.quarkus.entity.Alunos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AlunosRepository implements PanacheRepository<Alunos> {
}