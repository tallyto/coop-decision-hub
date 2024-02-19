package com.tallyto.sicred.coopdecisionhub.scheduler;

import com.tallyto.sicred.coopdecisionhub.facade.SessaoFacade;
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

    private final SessaoFacade sessaoFacade;

    public SessaoScheduler(SessaoVotacaoService sessaoVotacaoService, SessaoFacade sessaoFacade) {
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.sessaoFacade = sessaoFacade;
    }

    @Scheduled(fixedRate = 60000) // Executa a cada minuto, ajuste conforme necessário
    public void verificarEFecharSessoes() {
        log.info("Verificando sessões abertas");

        LocalDateTime agora = LocalDateTime.now();

        List<SessaoVotacao> sessoesAbertas = sessaoVotacaoService.buscarSessoesAbertas();

        for (SessaoVotacao sessao : sessoesAbertas) {
            if (sessao.getDataFechamento().isBefore(agora)) {
                var result = sessaoVotacaoService.fecharSessao(sessao.getId());
                if (result != null) {
                    sessaoFacade.notificarSessaoFechada(result);
                }
            }
        }
    }
}
