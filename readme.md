# Coop Decision Hub - README

**Descrição do Projeto:**

O Coop Decision Hub é uma aplicação desenvolvida para simplificar o processo de decisão em cooperativas, permitindo que os associados participem ativamente de votações sobre diversas pautas. A aplicação oferece funcionalidades essenciais para gerenciar sessões de votação, cadastrar pautas e registrar os votos dos associados.

**Tecnologias Utilizadas:**

- **Java 17:** Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Spring Boot:** Framework utilizado para criar a aplicação Java de forma rápida e eficiente.
- **Spring Data JPA:** Biblioteca que facilita o acesso a dados usando o framework JPA.
- **Spring Web:** Módulo do Spring para desenvolvimento de aplicativos web.
- **H2 Database e PostgreSQL:** Bancos de dados utilizados para armazenar informações da aplicação.
- **Spring Validation:** Módulo Spring para validação de dados.
- **Lombok:** Biblioteca para redução de código boilerplate.
- **Spring RabbitMQ:** Módulo para integração com RabbitMQ.
- **Spring Boot Test:** Framework para testes de unidades e integração.
- **SpringDoc OpenAPI:** Ferramenta para geração automática de documentação OpenAPI.

**Documentação da API:**

A documentação detalhada da API pode ser encontrada em [Documentação da API - Coop Decision Hub](http://localhost:3002/swagger-ui/index.html). Consulte essa documentação para entender os endpoints disponíveis, parâmetros e exemplos de chamadas.

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

5. Utilize o Docker Compose (ou similar, como Podman) para iniciar o banco de dados e o RabbitMQ:

   ```bash
   docker-compose up
   ```

   *Nota: Certifique-se de ter o Docker (ou Podman) instalado para usar essa opção.*

**Configurações Maven:**

- **Versão do Java:** 17
- **Versão do Spring Boot Starter:** 3.3.0-SNAPSHOT

**Configuração do Banco de Dados:**

A aplicação utiliza dois perfis de banco de dados: um para desenvolvimento (H2) e outro para produção (PostgreSQL).

- **application-dev.properties (H2 - Desenvolvimento):**

   - Configurações padrão para o ambiente de desenvolvimento.

- **application.properties (PostgreSQL - Default):**

   - Configurações específicas para o ambiente para testes.

**Configuração do Ambiente:**

A aplicação utiliza perfis de ambiente para configurar o banco de dados. Por padrão, o perfil "default" (PostgreSQL) é ativado. Para alterar para o perfil de desenvolvimento (H2), você pode definir a variável de ambiente `SPRING_PROFILES_ACTIVE`. Exemplo:

```bash
export SPRING_PROFILES_ACTIVE=dev
```

ou

```bash
set SPRING_PROFILES_ACTIVE=dev
```

**Licença:**

Este projeto é distribuído sob a [Licença MIT](https://opensource.org/licenses/MIT).

**Contato:**

Para mais informações, entre em contato com Tállyto Rodrigues via rodrigues.tallyto@gmail.com.

**Versão:**

0.0.1-SNAPSHOT