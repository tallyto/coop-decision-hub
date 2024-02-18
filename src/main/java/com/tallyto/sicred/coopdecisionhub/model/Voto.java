package com.tallyto.sicred.coopdecisionhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SessaoVotacao sessaoVotacao;

    private Long associadoId;

    private Boolean voto;
}
