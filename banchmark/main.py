from locust import HttpUser, task, between
import random
import string

class CoopDecisionHubUser(HttpUser):
    wait_time = between(1, 3)
    user_counter = 0

    def get_next_user_id(self):
        # Incrementa o contador global para obter um ID único
        CoopDecisionHubUser.user_counter += 1
        return CoopDecisionHubUser.user_counter

    @task
    def criar_pauta_e_sessao_e_votar(self):
        # Etapa 1: Criar Pauta
        descricao = ''.join(random.choices(string.ascii_letters, k=10))
        payload = {"descricao": descricao}
        response_pauta = self.client.post("/pautas", json=payload)

        if response_pauta.status_code == 200:
            return

        pauta_id = response_pauta.json()["id"]

        print(pauta_id)

        # Etapa 2: Abrir Sessão
        payload_sessao = {"pautaId": pauta_id}
        response_sessao = self.client.post("/sessoes/abrir", params=payload_sessao)

        if response_sessao.status_code == 200:
            return

        sessao_id = response_sessao.json()["id"]

        # Etapa 3: Votar
        payload_voto = {
            "sessaoVotacaoId": sessao_id,
            "associadoId": self.get_next_user_id(),
            "voto": random.choice([True, False])
        }
        self.client.post("/votos", params=payload_voto)
