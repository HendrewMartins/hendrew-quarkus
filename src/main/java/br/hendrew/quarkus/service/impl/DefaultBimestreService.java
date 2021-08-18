package br.hendrew.quarkus.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.BimestreRepository;
import br.hendrew.quarkus.service.BimestreService;

@ApplicationScoped
public class DefaultBimestreService implements BimestreService{
	
	private final BimestreRepository bimestreRepository;

    @Inject
    public DefaultBimestreService(BimestreRepository bimestreRepository) {
        this.bimestreRepository = bimestreRepository;
    }

    @Override
    public Bimestre getBimestreById(long id) throws MenssageNotFoundException {
        return bimestreRepository.findByIdOptional(id).orElseThrow(() -> new MenssageNotFoundException("There Bimestre doesn't exist"));
    }
    
    @Override
    public List<Bimestre> getBimestrePorAluno(Alunos alunos) throws MenssageNotFoundException {
        return bimestreRepository.findByAlunos(alunos);
    }

    @Override
    public List<Bimestre> getAllBimestre() {
        return bimestreRepository.listAll();
    }

    @Transactional
    @Override
    public Bimestre updateBimestre(long id, Bimestre bimestre) throws MenssageNotFoundException {
    	Bimestre existingBimestre = getBimestreById(id);
        existingBimestre.setAlunos(bimestre.getAlunos());
        existingBimestre.setAno(bimestre.getAno());
        existingBimestre.setBimestre(bimestre.getBimestre());
        existingBimestre.setFaltas(bimestre.getFaltas());
        return existingBimestre;
    }

    @Transactional
    @Override
    public Bimestre saveBimestre(Bimestre bimestre) {
        bimestreRepository.persistAndFlush(bimestre);
        return bimestre;
    }

    @Transactional
    @Override
    public void deleteBimestre(long id) throws MenssageNotFoundException {
        bimestreRepository.delete(getBimestreById(id));
    }


}
