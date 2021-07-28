package br.hendrew.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.quarkus.entity.Avaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AvaliacaoRepository implements PanacheRepository<Avaliacao>{

}
