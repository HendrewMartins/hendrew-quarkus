package br.hendrew.quarkus.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Bimestre;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BimestreRepository implements PanacheRepository<Bimestre>{
	
	public List<Bimestre> findByAlunos(Alunos alunos){
	       return list("alunos", alunos);
	   }


}
