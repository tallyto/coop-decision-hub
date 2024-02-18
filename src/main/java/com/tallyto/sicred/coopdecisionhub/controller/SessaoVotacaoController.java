package com.tallyto.sicred.coopdecisionhub.controller;

import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import com.tallyto.sicred.coopdecisionhub.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoController {
    private final SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping("/abrir")
    public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@RequestParam Long pautaId,
                                                            @RequestParam(required = false) LocalDateTime dataFechamento) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrirSessaoVotacao(pautaId, dataFechamento);
        return new ResponseEntity<>(sessaoVotacao,   HttpStatus.CREATED);
    }
}