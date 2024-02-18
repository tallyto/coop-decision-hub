package com.tallyto.sicred.coopdecisionhub.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessaoRequestProducer {
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public SessaoRequestProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(SessaoVotacao sessao) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        amqpTemplate.convertAndSend("sessao-fechada-exchange", "sessao-fechada-rout-key", mapper.writeValueAsString(sessao));
    }


}
