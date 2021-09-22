package br.hendrew.quarkus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.convertion.AlunosEnderecoConvertion;
import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosEndereco;
import br.hendrew.quarkus.entity.AlunosEndereco_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.AlunosEnderecoRepository;
import br.hendrew.quarkus.repository.AlunosRepository;
import br.hendrew.quarkus.service.AlunosEnderecoService;

@ApplicationScoped
public class DefaultAlunosEnderecoService implements AlunosEnderecoService {

    private final AlunosEnderecoRepository enderecoRepository;
    private final AlunosRepository alunosRepository;
    public AlunosEnderecoConvertion convertion = new AlunosEnderecoConvertion();

    @Inject
    public DefaultAlunosEnderecoService(AlunosEnderecoRepository enderecoRepository, AlunosRepository alunosRepository) {
        this.enderecoRepository = enderecoRepository;
        this.alunosRepository = alunosRepository;
    }

    @Override
    public void deleteEndereco(long id) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<AlunosEndereco> getAllEndereco() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AlunosEndereco getEnderecoAngById(long id) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AlunosEndereco getEnderecoById(long id) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AlunosEndereco_Auxiliar> getEnderecoPorAluno(long id) throws MenssageNotFoundException {
        Alunos aluno = new Alunos();
        aluno = alunosRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Aluno doesn't exist"));
        List<AlunosEndereco> endereco = enderecoRepository.findByAlunos(aluno);
        List<AlunosEndereco_Auxiliar> enderecoAux = new ArrayList<AlunosEndereco_Auxiliar>();

        for (int i = 0; i < endereco.size(); i++) {
            AlunosEndereco_Auxiliar aux = new AlunosEndereco_Auxiliar();
            aux = convertion.convertionAlunosEndereco(endereco.get(i));
            enderecoAux.add(i, aux);
        }
        return enderecoAux;
    }

    @Override
    public List<AlunosEndereco> getEnderecoPorAluno_Angular(long id) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AlunosEndereco> getNotaBimestrePorAluno(long id) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public AlunosEndereco saveEndereco(AlunosEndereco_Auxiliar alunosenderecoaux, long alunos) throws MenssageNotFoundException {
        
        Alunos aluno = alunosRepository.findByIdOptional(alunos)
                .orElseThrow(() -> new MenssageNotFoundException("There Aluno doesn't exist"));

        AlunosEndereco endereco = convertion.alunosEnderecoConvertion(alunosenderecoaux, aluno);
        
        enderecoRepository.persistAndFlush(endereco);
        return endereco;
    }

    @Override
    public AlunosEndereco updateEndereco(long id, AlunosEndereco alunosendereco, Alunos alunos)
            throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
