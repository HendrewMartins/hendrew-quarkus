package br.hendrew.quarkus.service;

import java.util.List;

import br.hendrew.quarkus.entity.Avaliacao;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.entity.Nota_Angular;
import br.hendrew.quarkus.entity.Nota_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;

public interface NotaService {

    Nota getNotaById(long id) throws MenssageNotFoundException;

    Nota_Angular getNotaAngById(long id) throws MenssageNotFoundException;

    List<Nota> getNotaPorBimestre(Bimestre bimestre) throws MenssageNotFoundException;

    List<Nota> getAllNota();

    List<Nota_Auxiliar> getAllNotaDescricao();

    List<Nota_Auxiliar> getAllNotaDescricaoPage(int pag, int quant);

    Nota updateNota(long id, Nota_Angular nota_angular, Bimestre bimestre, Avaliacao avaliacao)
            throws MenssageNotFoundException;

    Nota saveNota(Nota_Angular nota_angular, Bimestre bimestre, Avaliacao avaliacao);

    void deleteNota(long id) throws MenssageNotFoundException;

    long countNotas();

}
