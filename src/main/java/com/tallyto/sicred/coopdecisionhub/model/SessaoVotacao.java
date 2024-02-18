package com.tallyto.sicred.coopdecisionhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pauta pauta;

    @Getter
    private boolean fechada;

    private LocalDateTime dataFechamentoReal;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;

    public void fecharSessao() {
        if (!fechada) {
            fechada = true;
            dataFechamentoReal = LocalDateTime.now();
        }
    }

}
