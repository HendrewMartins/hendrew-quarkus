package br.hendrew.quarkus.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Nota;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class NotaRepository implements PanacheRepository<Nota>{

	public List<Nota> findByBimestre(Bimestre bimestre){
	       return list("bimestre", bimestre);
	   }
}
