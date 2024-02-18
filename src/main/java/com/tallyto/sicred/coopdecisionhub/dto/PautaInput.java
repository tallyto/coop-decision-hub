package com.tallyto.sicred.coopdecisionhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PautaInput(
        @NotBlank
        @NotNull
        String descricao
) {
}
