package br.hendrew.quarkus.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.NotaRepository;
import br.hendrew.quarkus.service.NotaService;

@ApplicationScoped
public class DefaultNotaService implements NotaService {
	
	private final NotaRepository notaRepository;

    @Inject
    public DefaultNotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    @Override
    public Nota getNotaById(long id) throws MenssageNotFoundException {
        return notaRepository.findByIdOptional(id).orElseThrow(() -> new MenssageNotFoundException("There Nota doesn't exist"));
    }
    
    @Override
    public List<Nota> getNotaPorBimestre(Bimestre bimestre) throws MenssageNotFoundException {
        return notaRepository.findByBimestre(bimestre);
    }

    @Override
    public List<Nota> getAllNota() {
        return notaRepository.listAll();
    }

    @Transactional
    @Override
    public Nota updateNota(long id, Nota nota) throws MenssageNotFoundException {
    	Nota existingNota = getNotaById(id);
        existingNota.setAvaliacao(nota.getAvaliacao());
        existingNota.setBimestre(nota.getBimestre());
        existingNota.setNota(nota.getNota());
        return existingNota;
    }

    @Transactional
    @Override
    public Nota saveNota(Nota nota) {
        notaRepository.persistAndFlush(nota);
        return nota;
    }

    @Transactional
    @Override
    public void deleteNota(long id) throws MenssageNotFoundException {
    	notaRepository.delete(getNotaById(id));
    }



}
