package br.hendrew.quarkus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.convertion.AlunosTelefoneConvertion;
import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosTelefone;
import br.hendrew.quarkus.entity.AlunosTelefone_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.AlunosRepository;
import br.hendrew.quarkus.repository.AlunosTelefoneRepository;
import br.hendrew.quarkus.service.AlunosTelefoneService;

@ApplicationScoped
public class DefaultAlunosTelefoneService implements AlunosTelefoneService {

    private final AlunosTelefoneRepository telefoneRepository;
    private final AlunosRepository alunosRepository;
    public AlunosTelefoneConvertion convertion = new AlunosTelefoneConvertion();

    @Inject
    public DefaultAlunosTelefoneService(AlunosTelefoneRepository telefoneRepository,
            AlunosRepository alunosRepository) {
        this.telefoneRepository = telefoneRepository;
        this.alunosRepository = alunosRepository;
    }

    @Override
    public List<AlunosTelefone_Auxiliar> getTelefonePorAluno(long id) throws MenssageNotFoundException {
        Alunos aluno = new Alunos();
        aluno = alunosRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Aluno doesn't exist"));
        List<AlunosTelefone> telefone = telefoneRepository.findByAlunos(aluno);
        List<AlunosTelefone_Auxiliar> telefoneAux = new ArrayList<AlunosTelefone_Auxiliar>();

        for (int i = 0; i < telefone.size(); i++) {
            AlunosTelefone_Auxiliar aux = new AlunosTelefone_Auxiliar();
            aux = convertion.convertionAlunosTelefone(telefone.get(i));
            telefoneAux.add(i, aux);
        }
        return telefoneAux;

    }

    @Transactional
    @Override
    public void saveTelefone(AlunosTelefone_Auxiliar alunostelefoneaux, Alunos alunos)
            throws MenssageNotFoundException {
        AlunosTelefone telefone = convertion.alunosEnderecoConvertion(alunostelefoneaux, alunos);
        telefoneRepository.persistAndFlush(telefone);
    }

    @Transactional
    @Override
    public void deleteTelefoneAluno(long id) throws MenssageNotFoundException {
        Alunos aluno = new Alunos();
        aluno = alunosRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Aluno doesn't exist"));
        List<AlunosTelefone> telefone = telefoneRepository.findByAlunos(aluno);

        for (int x = 0; x < telefone.size(); x++) {
            telefoneRepository.delete(telefone.get(x));
        }
    }

}
