package br.hendrew.quarkus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.convertion.NotaConvertion;
import br.hendrew.quarkus.entity.Avaliacao;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.entity.Nota_Angular;
import br.hendrew.quarkus.entity.Nota_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.NotaRepository;
import br.hendrew.quarkus.service.NotaService;

@ApplicationScoped
public class DefaultNotaService implements NotaService {
	
	private final NotaRepository notaRepository;
    public NotaConvertion convertion = new NotaConvertion();

    @Inject
    public DefaultNotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    @Override
    public Nota getNotaById(long id) throws MenssageNotFoundException {
        return notaRepository.findByIdOptional(id).orElseThrow(() -> new MenssageNotFoundException("There Nota doesn't exist"));
    }

    @Override
    public Nota_Angular getNotaAngById(long id) throws MenssageNotFoundException {
        Nota nota = new Nota();
        nota = notaRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Bimestre doesn't exist"));
        return convertion.convertionNota(nota);
    }
    
    @Override
    public List<Nota> getNotaPorBimestre(Bimestre bimestre) throws MenssageNotFoundException {
        return notaRepository.findByBimestre(bimestre);
    }

    @Override
    public List<Nota> getAllNota() {
        return notaRepository.listAll();
    }

    @Override
    public  List<Nota_Auxiliar>  getAllNotaDescricao(){
        List<Nota> listNotas = notaRepository.listAll();	
		
		List<Nota_Auxiliar> lista = new ArrayList<Nota_Auxiliar>();
		for(int i = 0; i < listNotas.size(); i++)
		{
			Nota_Auxiliar aux = new Nota_Auxiliar();
				aux.setDesc_Aluno(listNotas.get(i).getBimestre().getAlunos().getNome());
				aux.setId(listNotas.get(i).getId());
				aux.setId_Bimestre(listNotas.get(i).getBimestre().getId());
				aux.setId_Aluno(listNotas.get(i).getBimestre().getAlunos().getId());
				aux.setId_Avaliacao(listNotas.get(i).getAvaliacao().getId());
				aux.setNotas(listNotas.get(i).getNota());
				aux.setDesc_avaliacao(listNotas.get(i).getAvaliacao().getDescricao()+" - "+listNotas.get(i).getAvaliacao().getPeso()+"%");
				aux.setAno(listNotas.get(i).getBimestre().getAno());
				aux.setDesc_bimestre(listNotas.get(i).getBimestre().getBimestre()+"º"+" Bimestre - "+listNotas.get(i).getBimestre().getAno());
			

			lista.add(i,aux);
		}
		
		return lista;
    }

    @Transactional
    @Override
    public Nota updateNota(long id, Nota_Angular nota_angular, Bimestre bimestre,Avaliacao avaliacao) throws MenssageNotFoundException {
    	Nota nota = convertion.notaConvertion(nota_angular, bimestre, avaliacao);

        Nota existingNota = getNotaById(id);
        existingNota.setAvaliacao(nota.getAvaliacao());
        existingNota.setBimestre(nota.getBimestre());
        existingNota.setNota(nota.getNota());
        return existingNota;
    }

    @Transactional
    @Override
    public Nota saveNota(Nota_Angular nota_angular, Bimestre bimestre, Avaliacao avaliacao) {
        Nota nota = convertion.notaConvertion(nota_angular, bimestre, avaliacao);
        notaRepository.persistAndFlush(nota);
        return nota;
    }

    @Transactional
    @Override
    public void deleteNota(long id) throws MenssageNotFoundException {
    	notaRepository.delete(getNotaById(id));
    }



}
