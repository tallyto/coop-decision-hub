package com.tallyto.sicred.coopdecisionhub.repository;

import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
    List<SessaoVotacao> findByFechadaFalse();
}