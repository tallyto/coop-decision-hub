package com.tallyto.sicred.coopdecisionhub.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import com.tallyto.sicred.coopdecisionhub.producer.SessaoRequestProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessaoFacade {

    private final SessaoRequestProducer producer;

    @Autowired
    public SessaoFacade(SessaoRequestProducer producer) {
        this.producer = producer;
    }

    public void notificarSessaoFechada(SessaoVotacao sessao) {
        try {
            producer.send(sessao);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),"Erro ao processar mensagem", e);
        }
	}

}
