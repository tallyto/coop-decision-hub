package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.exception.InvalidDateException;
import com.tallyto.sicred.coopdecisionhub.model.Pauta;
import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import com.tallyto.sicred.coopdecisionhub.repository.SessaoVotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class SessaoVotacaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;

    @Autowired
    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository,
                                PautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
    }

    public SessaoVotacao abrirSessaoVotacao(Long pautaId, LocalDateTime dataFechamento) {
        Pauta pauta = pautaService.buscarPautaPorId(pautaId);

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        var dataAbertura = LocalDateTime.now();
        sessaoVotacao.setDataAbertura(dataAbertura);

        if (dataFechamento == null) {
            dataFechamento = LocalDateTime.now().plusMinutes(1);
        }

        if (dataFechamento.isBefore(dataAbertura)) {
            throw new InvalidDateException("A data de fechamento deve ser posterior à data de abertura");
        }
        sessaoVotacao.setDataFechamento(dataFechamento);

        sessaoVotacao = sessaoVotacaoRepository.save(sessaoVotacao);

        return sessaoVotacao;
    }

    public SessaoVotacao fecharSessao(Long sessaoId) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(sessaoId).orElse(null);
        if (sessaoVotacao != null && !sessaoVotacao.isFechada()) {
            sessaoVotacao.fecharSessao();
            log.info("Sessão de votação fechada com sucesso.");
            return sessaoVotacaoRepository.save(sessaoVotacao);
        }
        return null; // Retorna null se não encontrar a sessão ou já estiver fechada.
    }

    public SessaoVotacao buscarSessaoPorId(Long sessaoVotacaoId) {
        return sessaoVotacaoRepository.findById(sessaoVotacaoId).orElse(null); // Retorna null se não encontrar o ID
    }

    public List<SessaoVotacao> buscarSessoesAbertas() {
        return sessaoVotacaoRepository.findByFechadaFalse();
    }
}