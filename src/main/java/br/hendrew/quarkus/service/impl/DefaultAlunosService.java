package br.hendrew.quarkus.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.AlunosRepository;
import br.hendrew.quarkus.service.AlunosService;

@ApplicationScoped
public class DefaultAlunosService implements AlunosService {

    private final AlunosRepository alunosRepository;

    @Inject
    public DefaultAlunosService(AlunosRepository alunosRepository) {
        this.alunosRepository = alunosRepository;
       
    }

    @Override
    public Alunos getAlunosById(long id) throws MenssageNotFoundException {
        return alunosRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Alunos doesn't exist"));
    }

    @Override
    public List<Alunos> getAlunosByNome(String nome) throws MenssageNotFoundException {
       // List<Alunos> alunos = alunosRepository
         //       .find("select alunos.id, alunos.nome, alunos.dt_nasc from alunos where alunos.nome = '" + nome + "'")
           //     .page(Page.ofSize(5)).list();

       // return alunos;
     return alunosRepository.findByNome(nome);
    }

    @Override
    public List<Alunos> getAllAlunos() {
        return alunosRepository.listAll();
    }

    @Transactional
    @Override
    public Alunos updateAlunos(long id, Alunos alunos) throws MenssageNotFoundException {
        Alunos existingAluno = getAlunosById(id);
        
        existingAluno.setNome(alunos.getNome());
        existingAluno.setDt_nasc(alunos.getDt_nasc());
        existingAluno.setCpf(alunos.getCpf());
        existingAluno.setMatricula(alunos.getMatricula());
        existingAluno.setNm_mae(alunos.getNm_mae());
        existingAluno.setNm_pai(alunos.getNm_pai());
        existingAluno.setRg_aluno(alunos.getRg_aluno());

        return existingAluno;
    }

    @Transactional
    @Override
    public Alunos saveAlunos(Alunos alunos) {
        alunosRepository.persistAndFlush(alunos);
        return alunos;
    }

    @Transactional
    @Override
    public void deleteAluno(long id) throws MenssageNotFoundException {
        alunosRepository.delete(getAlunosById(id));
    }
}
