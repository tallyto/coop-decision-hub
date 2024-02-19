package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.config.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class VotoServiceTest extends BaseTest {

    @Autowired
    VotoService votoService;

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    PautaService pautaService;

    @Test
    void votar() {
       var pauta = pautaService.cadastrarPauta("Teste");
       var sessao = sessaoVotacaoService.abrirSessaoVotacao(pauta.getId(), null);
       var voto = votoService.votar(sessao.getId(), 1L, true);
       assertNotNull(voto);
    }

    @Test
    void contarVotos() {
        var pauta = pautaService.cadastrarPauta("Teste");
        var sessao = sessaoVotacaoService.abrirSessaoVotacao(pauta.getId(), null);
        var voto = votoService.votar(sessao.getId(), 1L, true);
        var votos = votoService.contarVotos(sessao.getId());
        assertEquals(1, votos);
    }
}