package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.exception.ExpiredSessionException;
import com.tallyto.sicred.coopdecisionhub.exception.VoterAlreadyVotedException;
import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import com.tallyto.sicred.coopdecisionhub.model.Voto;
import com.tallyto.sicred.coopdecisionhub.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotoService {
    private final VotoRepository votoRepository;
    private final SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    public VotoService(VotoRepository votoRepository, SessaoVotacaoService sessaoVotacaoService) {
        this.votoRepository = votoRepository;
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    public Voto votar(Long sessaoVotacaoId, Long associadoId, boolean voto) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoPorId(sessaoVotacaoId);

        // Verificar se o associado já votou nessa sessão
        List<Voto> votosExistente = votoRepository.findBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId);
        if (!votosExistente.isEmpty()) {
            throw new VoterAlreadyVotedException("Associado já votou nessa sessão.");
        }

        // Verificar se a sessão ainda está aberta
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isAfter(sessaoVotacao.getDataFechamento())) {
            throw new ExpiredSessionException("A sessão de votação está fechada.");
        }

        Voto votoObj = new Voto();
        votoObj.setSessaoVotacao(sessaoVotacao);
        votoObj.setAssociadoId(associadoId);
        votoObj.setVoto(voto);

        return votoRepository.save(votoObj);
    }

    public Integer contarVotos(Long sessaoVotacaoId) {
        return votoRepository.countBySessaoVotacaoId(sessaoVotacaoId);
    }

}