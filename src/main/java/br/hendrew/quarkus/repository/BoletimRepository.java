package br.hendrew.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.quarkus.entity.Boletim;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BoletimRepository implements PanacheRepository<Boletim> {

}
