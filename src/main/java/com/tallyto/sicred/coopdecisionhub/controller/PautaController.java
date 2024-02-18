package com.tallyto.sicred.coopdecisionhub.controller;

import com.tallyto.sicred.coopdecisionhub.dto.PautaInput;
import com.tallyto.sicred.coopdecisionhub.model.Pauta;
import com.tallyto.sicred.coopdecisionhub.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody @Valid PautaInput pautaInput) {
        Pauta pauta = pautaService.cadastrarPauta(pautaInput.descricao());
        return new ResponseEntity<>(pauta, HttpStatus.CREATED);
    }
}