# Coop Decision Hub - README

**Descrição do Projeto:**

O Coop Decision Hub é uma aplicação projetada para facilitar o processo de decisão em uma cooperativa, permitindo que os associados participem ativamente de votações sobre diversas pautas. A aplicação oferece funcionalidades essenciais para gerenciar sessões de votação, cadastrar pautas e registrar os votos dos associados.

**Tecnologias Utilizadas:**

- **Java 17:** Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Spring Boot:** Framework utilizado para criar a aplicação Java de forma rápida e fácil.
- **Spring Data JPA:** Biblioteca que facilita o acesso a dados usando o framework JPA.
- **Spring Web:** Módulo do Spring para desenvolvimento de aplicativos web.
- **H2 Database e PostgreSQL:** Bancos de dados utilizados para armazenar informações da aplicação.
- **Spring Validation:** Módulo Spring para validação de dados.
- **Lombok:** Biblioteca para redução de código boilerplate.
- **Spring RabbitMQ:** Módulo para integração com RabbitMQ.
- **Spring Boot Test:** Framework para testes de unidades e integração.
- **SpringDoc OpenAPI:** Ferramenta para geração automática de documentação OpenAPI.

**Documentação da API:**

A documentação detalhada da API pode ser encontrada em [Documentação da API - Coop Decision Hub](http://localhost:3002/swagger-ui/index.html). Utilize essa documentação para entender os endpoints disponíveis, parâmetros, e exemplos de chamadas.

**Exemplo de Chamada da API:**

1. **Registrar Voto:**

   ```bash
   curl -X POST -H "Content-Type: application/json" -d '{"sessaoVotacaoId": 1, "associadoId": 123, "voto": true}' http://localhost:3002/votos
   ```

2. **Abrir Nova Sessão de Votação:**

   ```bash
   curl -X POST -H "Content-Type: application/json" -d '{"pautaId": 1, "dataFechamento": "2024-02-29T18:00:00"}' http://localhost:3002/sessoes/abrir
   ```

3. **Cadastrar Nova Pauta:**

   ```bash
   curl -X POST -H "Content-Type: application/json" -d '{"descricao": "Nova Pauta"}' http://localhost:3002/pautas
   ```

4. **Contar Votos por Sessão:**

   ```bash
   curl http://localhost:3002/votos/sessao/1
   ```

**Como Iniciar:**

1. Clone o repositório.

   ```bash
   git clone https://github.com/tallyto/coop-decision-hub.git
   ```

2. Instale as dependências.

   ```bash
   cd coop-decision-hub
   mvn clean install
   ```

3. Inicie o servidor.

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação em [http://localhost:3002](http://localhost:3002).

**Configurações Maven:**

- **Versão do Java:** 17
- **Versão do Spring Boot Starter:** 3.3.0-SNAPSHOT

**Licença:**

Este projeto é distribuído sob a [Licença MIT](https://opensource.org/licenses/MIT).

**Contato:**

Para mais informações, entre em contato com Tállyto Rodrigues via rodrigues.tallyto@gmail.com.

**Versão:**

0.0.1-SNAPSHOT