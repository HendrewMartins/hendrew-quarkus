package br.hendrew.quarkus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.hendrew.quarkus.entity.Avaliacao;
import br.hendrew.quarkus.entity.Avaliacao_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.repository.AvaliacaoRepository;
import br.hendrew.quarkus.service.AvaliacaoService;

@ApplicationScoped
public class DefaultAvaliacaoService implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    @Inject
    public DefaultAvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public Avaliacao getAvaliacaoById(long id) throws MenssageNotFoundException {
        return avaliacaoRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("There Alunos doesn't exist"));
    }

    @Override
    public List<Avaliacao_Auxiliar> getAllAvaliacao() {
        List<Avaliacao> avaliacao = avaliacaoRepository.listAll();
        List<Avaliacao_Auxiliar> listaAvaliacao = new ArrayList<Avaliacao_Auxiliar>();

        for (int i = 0; i < avaliacao.size(); i++) {
            Avaliacao_Auxiliar auxiliar = new Avaliacao_Auxiliar();
            auxiliar.setId(avaliacao.get(i).getId());
            auxiliar.setPeso(avaliacao.get(i).getPeso());
            auxiliar.setDescricao(avaliacao.get(i).getDescricao()+" - "+auxiliar.getPeso()+"%");

            listaAvaliacao.add(i,auxiliar);
        }

        return listaAvaliacao;
    }

    @Transactional
    @Override
    public Avaliacao updateAvaliacao(long id, Avaliacao avaliacao) throws MenssageNotFoundException {
        Avaliacao existingAvaliacao = getAvaliacaoById(id);
        existingAvaliacao.setDescricao(avaliacao.getDescricao());
        existingAvaliacao.setPeso(avaliacao.getPeso());
        return existingAvaliacao;
    }

    @Transactional
    @Override
    public Avaliacao saveAvaliacao(Avaliacao avaliacao) {
        avaliacaoRepository.persistAndFlush(avaliacao);
        return avaliacao;
    }

    @Transactional
    @Override
    public void deleteAvaliacao(long id) throws MenssageNotFoundException {
        avaliacaoRepository.delete(getAvaliacaoById(id));
    }

}
