package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.model.Pauta;
import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import com.tallyto.sicred.coopdecisionhub.repository.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SessaoVotacaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;

    @Autowired
    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
    }

    public SessaoVotacao abrirSessaoVotacao(Long pautaId, LocalDateTime dataFechamento) {
        Pauta pauta = pautaService.buscarPautaPorId(pautaId);

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataAbertura(LocalDateTime.now());

        sessaoVotacao.setDataFechamento(Objects.requireNonNullElseGet(dataFechamento,
                () -> sessaoVotacao.getDataAbertura().plusMinutes(1)));

        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    public SessaoVotacao buscarSessaoPorId(Long sessaoVotacaoId) {
        return sessaoVotacaoRepository.findById(sessaoVotacaoId).orElse(null); // Retorna null se não encontrar o ID
    }
}