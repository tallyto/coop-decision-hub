package com.tallyto.sicred.coopdecisionhub.service;

import com.tallyto.sicred.coopdecisionhub.facade.SessaoFacade;
import com.tallyto.sicred.coopdecisionhub.model.Pauta;
import com.tallyto.sicred.coopdecisionhub.model.SessaoVotacao;
import com.tallyto.sicred.coopdecisionhub.repository.SessaoVotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SessaoVotacaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;

    private final SessaoFacade  sessaoFacade;

    @Autowired
    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository,
                                PautaService pautaService,
                                SessaoFacade sessaoFacade) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
        this.sessaoFacade = sessaoFacade;
    }

    public SessaoVotacao abrirSessaoVotacao(Long pautaId, LocalDateTime dataFechamento) {
        Pauta pauta = pautaService.buscarPautaPorId(pautaId);

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataAbertura(LocalDateTime.now());

        SessaoVotacao finalSessaoVotacao = sessaoVotacao;
        sessaoVotacao.setDataFechamento(Objects.requireNonNullElseGet(dataFechamento,
                () -> finalSessaoVotacao.getDataAbertura().plusMinutes(1)));

        sessaoVotacao = sessaoVotacaoRepository.save(sessaoVotacao);

        return sessaoVotacao;
    }

    public void fecharSessao(Long sessaoId) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(sessaoId).orElse(null);
        if (sessaoVotacao != null && !sessaoVotacao.isFechada()) {
            sessaoVotacao.fecharSessao();
            sessaoVotacaoRepository.save(sessaoVotacao);

            log.info("Sessão de votação fechada com sucesso.");
            this.sessaoFacade.fecharSessao(sessaoVotacao);
        }
    }

    public SessaoVotacao buscarSessaoPorId(Long sessaoVotacaoId) {
        return sessaoVotacaoRepository.findById(sessaoVotacaoId).orElse(null); // Retorna null se não encontrar o ID
    }

    public List<SessaoVotacao> buscarSessoesAbertas() {
        return sessaoVotacaoRepository.findByFechadaFalse();
    }
}