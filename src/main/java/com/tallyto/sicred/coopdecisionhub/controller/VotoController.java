package com.tallyto.sicred.coopdecisionhub.controller;

import com.tallyto.sicred.coopdecisionhub.model.Voto;
import com.tallyto.sicred.coopdecisionhub.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
public class VotoController {
    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<Voto> votar(@RequestParam Long sessaoVotacaoId,
                                      @RequestParam Long associadoId,
                                      @RequestParam boolean voto) {
        Voto votoObj = votoService.votar(sessaoVotacaoId, associadoId, voto);
        return new ResponseEntity<>(votoObj, HttpStatus.CREATED);
    }

    @GetMapping
    ("sessao/{id}")
    public Integer contarVotosPorSessao(@PathVariable Long id) {
        return votoService.contarVotos(id);
    }
}