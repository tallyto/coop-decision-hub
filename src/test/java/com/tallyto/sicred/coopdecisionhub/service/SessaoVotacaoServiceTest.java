package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.config.BaseTest;
import com.tallyto.sicred.coopdecisionhub.exception.InvalidDateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SessaoVotacaoServiceTest extends BaseTest {

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    PautaService pautaService;

    @Test
    void deveAbrirSessaoVotacao() {
        var pauta = pautaService.cadastrarPauta("Teste");
        var dataFechamento = LocalDateTime.now().plusMinutes(1);
        var sessao = sessaoVotacaoService.abrirSessaoVotacao(pauta.getId(), dataFechamento);
        assertNotNull(sessao);
    }

    @Test
    void deveFecharSessao() {
        var pauta = pautaService.cadastrarPauta("Teste");
        var sessao = sessaoVotacaoService.abrirSessaoVotacao(pauta.getId(), LocalDateTime.now().plusMinutes(5));
        var sessaoFechada = sessaoVotacaoService.fecharSessao(sessao.getId());
        assertTrue(sessaoFechada.isFechada());
    }

    @Test
    void deveAbrirSessaoVotacaoInvalida() {
        var pauta = pautaService.cadastrarPauta("Teste");
        assertThrows(InvalidDateException.class, () -> sessaoVotacaoService.abrirSessaoVotacao(pauta.getId(), LocalDateTime.now().minusDays(1)));
    }

    @Test
    void deveBuscarSessaoPorId() {
        var pauta = pautaService.cadastrarPauta("Teste");
        var sessao = sessaoVotacaoService.abrirSessaoVotacao(pauta.getId(), LocalDateTime.now().plusMinutes(5));
        var sessaoEncontrada = sessaoVotacaoService.buscarSessaoPorId(sessao.getId());
        assertNotNull(sessaoEncontrada);
    }

    @Test
    void deveBuscarSessoesAbertas() {
        var pauta = pautaService.cadastrarPauta("Teste");
        var sessao = sessaoVotacaoService.abrirSessaoVotacao(pauta.getId(), LocalDateTime.now().plusMinutes(5));
        var sessoesAbertas = sessaoVotacaoService.buscarSessoesAbertas();
        assertEquals(1, sessoesAbertas.size());
    }
}