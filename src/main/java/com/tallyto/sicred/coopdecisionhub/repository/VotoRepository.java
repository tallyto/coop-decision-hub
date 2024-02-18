package com.tallyto.sicred.coopdecisionhub.repository;

import com.tallyto.sicred.coopdecisionhub.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findBySessaoVotacaoIdAndAssociadoId(Long sessaoVotacaoId, Long associadoId);

    Integer countBySessaoVotacaoId(Long sessaoId);
}