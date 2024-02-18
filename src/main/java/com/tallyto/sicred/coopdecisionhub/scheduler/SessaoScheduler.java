package com.tallyto.sicred.coopdecisionhub.scheduler;

import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import com.tallyto.sicred.coopdecisionhub.service.SessaoVotacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class SessaoScheduler {
    private final SessaoVotacaoService sessaoVotacaoService;

    public SessaoScheduler(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @Scheduled(fixedRate = 60000) // Executa a cada minuto, ajuste conforme necessário
    public void verificarEFecharSessoes() {
        log.info("Verificando sessões abertas");

        LocalDateTime agora = LocalDateTime.now();

        List<SessaoVotacao> sessoesAbertas = sessaoVotacaoService.buscarSessoesAbertas();

        for (SessaoVotacao sessao : sessoesAbertas) {
            if (sessao.getDataFechamento().isBefore(agora)) {
                sessaoVotacaoService.fecharSessao(sessao.getId());
            }
        }
    }
}
