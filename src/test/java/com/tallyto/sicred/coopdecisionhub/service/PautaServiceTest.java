package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.config.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class PautaServiceTest extends BaseTest {

    @Autowired
    PautaService pautaService;

    @BeforeEach
    void setUp() {
        pautaService.cadastrarPauta("Default Pauta");
    }

    @Test
    void DeveCadastrarPauta() {
        var pauta = pautaService.cadastrarPauta("Pauta Teste");
        assertNotNull(pauta.getId());
    }

    @Test
    void DeveBuscarPautaPorId() {
        var pauta = pautaService.buscarPautaPorId(1L);
        assertNotNull(pauta);
    }

    @Test
    void DeveRetornarNullAoBuscarPautaPorId() {
        var pauta = pautaService.buscarPautaPorId(100L);
        assertNull(pauta);
    }
}