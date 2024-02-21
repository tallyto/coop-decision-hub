# Script Locust para CoopDecisionHub

Este script Locust simula o comportamento do usuário para a aplicação CoopDecisionHub. O script realiza as seguintes tarefas:

## Tarefa: criar_pauta_e_sessao_e_votar

Esta tarefa simula o processo de criar uma pauta, abrir uma sessão e realizar um voto.

### Passos:

1. **Criar Pauta:**
    - Descrição: Gera uma descrição aleatória alfanumérica para a pauta.
    - Endpoint da API: `POST /pautas`
    - Payload: `{"descricao": "[descricao_aleatoria]"}`

2. **Abrir Sessão:**
    - Endpoint da API: `POST /sessoes/abrir`
    - Payload: `{"pautaId": "[pauta_id]"}`

3. **Votar:**
    - Endpoint da API: `POST /votos`
    - Payload:
      ```json
      {
        "sessaoVotacaoId": "[sessao_id]",
        "associadoId": "[id_usuario_unico]",
        "voto": [Verdadeiro/Falso]
      }
      ```

## Parâmetros Globais:

- **Tempo de Espera do Usuário:** Tempo de espera aleatório entre 1 e 3 segundos.

## Contador de Usuários:

Um contador global é utilizado para gerar IDs de usuário únicos para o campo `associadoId` durante a votação.

## Uso:

Para executar o script Locust, utilize o seguinte comando:

```bash
locust -f main.py
```