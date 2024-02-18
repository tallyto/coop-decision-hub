package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.model.Pauta;
import com.tallyto.sicred.coopdecisionhub.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    @Autowired
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta cadastrarPauta(String descricao) {
        Pauta pauta = new Pauta();
        pauta.setDescricao(descricao);
        return pautaRepository.save(pauta);
    }

    public Pauta buscarPautaPorId(Long pautaId) {
        return pautaRepository.findById(pautaId).orElse(null);
    }
}
