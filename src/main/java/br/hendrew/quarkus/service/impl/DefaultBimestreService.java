package br.hendrew.quarkus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.convertion.BimestreConvertion;
import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Bimestre_Angular;
import br.hendrew.quarkus.entity.Bimestre_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.AlunosRepository;
import br.hendrew.quarkus.repository.BimestreRepository;
import br.hendrew.quarkus.service.BimestreService;

@ApplicationScoped
public class DefaultBimestreService implements BimestreService {

    private final BimestreRepository bimestreRepository;
    public BimestreConvertion convertion = new BimestreConvertion();
    private final AlunosRepository alunosRepository;

    @Inject
    public DefaultBimestreService(BimestreRepository bimestreRepository, AlunosRepository alunosRepository) {
        this.bimestreRepository = bimestreRepository;
        this.alunosRepository = alunosRepository;
    }

    @Override
    public Bimestre_Angular getBimestreAngById(long id) throws MenssageNotFoundException {
        Bimestre bimestre = new Bimestre();
        bimestre = bimestreRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Bimestre doesn't exist"));
        return convertion.convertionBimestre(bimestre);
    }

    @Override
    public Bimestre getBimestreById(long id) throws MenssageNotFoundException {
        Bimestre bimestre = new Bimestre();
        bimestre = bimestreRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Bimestre doesn't exist"));
        return bimestre;
    }


    @Override
    public List<Bimestre_Angular> getBimestrePorAluno_Angular(long id) throws MenssageNotFoundException {
        Alunos aluno = new Alunos();
        List<Bimestre_Angular> listaAngular = new ArrayList<Bimestre_Angular>();
        aluno = alunosRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Aluno doesn't exist"));
        List<Bimestre> bimestre = bimestreRepository.findByAlunos(aluno);
        for (int i = 0; i < bimestre.size(); i++) {
            Bimestre_Angular bimestreAngular = convertion.convertionBimestre(bimestre.get(i));
            listaAngular.add(i, bimestreAngular);
        }
        return listaAngular;
    }

    @Override
    public List<Bimestre_Auxiliar> getNotaBimestrePorAluno(long id) throws MenssageNotFoundException {
        Alunos aluno = new Alunos();
        List<Bimestre_Auxiliar> listaAuxiliar = new ArrayList<Bimestre_Auxiliar>();
        aluno = alunosRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Aluno doesn't exist"));
        List<Bimestre> bimestre = bimestreRepository.findByAlunos(aluno);
        for (int i = 0; i < bimestre.size(); i++) {
            Bimestre_Auxiliar bimestreAux = new Bimestre_Auxiliar();
            bimestreAux.setAno(bimestre.get(i).getAno());
            bimestreAux.setBimestre(bimestre.get(i).getBimestre());
            bimestreAux.setFaltas(bimestre.get(i).getFaltas());
            bimestreAux.setId(bimestre.get(i).getId());
            bimestreAux.setId_Alunos(bimestre.get(i).getAlunos().getId());
            bimestreAux.setNm_Alunos(bimestre.get(i).getAlunos().getNome());
            bimestreAux.setDesc_bimestre(bimestre.get(i).getId()+" - "+bimestreAux.getBimestre()+"° Bimestre de "+bimestreAux.getAno());

            listaAuxiliar.add(i, bimestreAux);
        }
        return listaAuxiliar;
    }

    @Override
    public List<Bimestre> getBimestrePorAluno(long id) throws MenssageNotFoundException {
        Alunos aluno = new Alunos();
        aluno = alunosRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Aluno doesn't exist"));
        return bimestreRepository.findByAlunos(aluno);
    }

    @Override
    public List<Bimestre> getAllBimestre() {
        return bimestreRepository.listAll();
    }

    @Override
    public List<Bimestre_Auxiliar> getAllBimestreNmAluno() {
        List<Bimestre> listBimestre = bimestreRepository.listAll();

        List<Bimestre_Auxiliar> lista = new ArrayList<Bimestre_Auxiliar>();
        for (int i = 0; i < listBimestre.size(); i++) {
            Bimestre_Auxiliar aux = new Bimestre_Auxiliar();
            aux.setNm_Alunos(listBimestre.get(i).getAlunos().getNome());
            aux.setAno(listBimestre.get(i).getAno());
            aux.setId(listBimestre.get(i).getId());
            aux.setBimestre(listBimestre.get(i).getBimestre());
            aux.setDesc_bimestre(listBimestre.get(i).getBimestre() + "º" + " Bimestre");
            aux.setId_Alunos(listBimestre.get(i).getAlunos().getId());
            aux.setFaltas(listBimestre.get(i).getFaltas());
            lista.add(i, aux);
        }

        return lista;
    }

    @Transactional
    @Override
    public Bimestre updateBimestre(long id, Bimestre_Angular bimestre_angular, Alunos alunos) throws MenssageNotFoundException {     
        Bimestre bimestre = convertion.bimestreConvertion(bimestre_angular, alunos);

        Bimestre existingBimestre = getBimestreById(id);
        existingBimestre.setAlunos(bimestre.getAlunos());
        existingBimestre.setAno(bimestre.getAno());
        existingBimestre.setBimestre(bimestre.getBimestre());
        existingBimestre.setFaltas(bimestre.getFaltas());
        return existingBimestre;
    }

    @Transactional
    @Override
    public Bimestre saveBimestre(Bimestre_Angular bimestre_angular, Alunos alunos) {
        Bimestre bimestre = convertion.bimestreConvertion(bimestre_angular, alunos);
        bimestreRepository.persistAndFlush(bimestre);
        return bimestre;
    }

    @Transactional
    @Override
    public void deleteBimestre(long id) throws MenssageNotFoundException {
        bimestreRepository.delete(getBimestreById(id));
    }

}
