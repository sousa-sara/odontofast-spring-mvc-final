# Odontofast

**Aplicação Web com Spring MVC para Gestão Odontológica**

## Objetivo

O Odontofast é uma aplicação web MVC desenvolvida como parte do Challenge da Odontoprev, com o objetivo de digitalizar e otimizar a gestão da rotina de dentistas com seus pacientes. A proposta busca resolver problemas comuns enfrentados pelos profissionais odontológicos, oferecendo:

- Gerenciamento centralizado e digital de agendamentos
- Monitoramento eficiente de pacientes e consultas
- Acesso rápido a informações de perfil profissional
- Assistência na elaboração de anamnese personalizada para pacientes
- Sistema de notificações em tempo real

A solução foi projetada para ser integrada posteriormente com um aplicativo mobile utilizado pelos pacientes, criando um ecossistema completo de gestão odontológica.

# Arquitetura da Solução Odontofast

## Padrão Arquitetural
* **Model-View-Controller (MVC)**: Arquitetura multicamada que separa a aplicação em componentes lógicos distintos

## Camadas Principais

### Frontend (View)
* **Thymeleaf**: Engine de templates para renderização server-side
* **Bootstrap 5**: Framework CSS responsivo para interface de usuário
* **JavaScript**: Para interatividade do lado do cliente

### Backend (Controller e Model)
* **Java 21**: Linguagem de programação base
* **Spring Boot 3.4.3**: Framework para desenvolvimento simplificado
* **Spring MVC**: Implementação do padrão Model-View-Controller
* **Spring Data JPA**: Camada de acesso a dados utilizando JPA/Hibernate
* **Spring Security**: Autenticação e autorização baseada em roles (DENTISTA/ADMIN)

### Persistência de Dados
* **Oracle Database**: Armazenamento persistente de dados
* **JPA/Hibernate**: ORM para mapeamento objeto-relacional

## Componentes Adicionais
* **RabbitMQ**: Sistema de mensageria para notificações assíncronas
* **Spring AI**: Integração com Ollama para recursos de IA na assistência de anamnese
* **Spring Actuator**: Monitoramento de métricas e saúde da aplicação

## Estrutura Organizacional do Código
* **Controllers**: Gerenciam as requisições HTTP e direcionam o fluxo da aplicação
* **Models**: Entidades de domínio que representam objetos de negócio (JPA)
* **Repositories**: Interfaces para acesso aos dados
* **Services**: Encapsulam a lógica de negócios
* **Views**: Templates Thymeleaf que renderizam a interface do usuário

## Fluxo de Dados
1. **Requisição HTTP**: O cliente faz uma requisição que é recebida pelo controlador
2. **Processamento**: O controlador processa a requisição e aciona serviços apropriados
3. **Acesso a Dados**: Os serviços utilizam repositórios para acessar e manipular dados
4. **Resposta**: O controlador seleciona a view apropriada e envia os dados para renderização
5. **Página HTML**: A view renderiza os dados em HTML que é retornado ao cliente

Esta arquitetura proporciona uma separação clara de responsabilidades, facilitando a manutenção, testabilidade e escalabilidade da aplicação. A combinação de Spring Boot com Spring MVC oferece uma estrutura robusta para desenvolvimento web, enquanto componentes como RabbitMQ e Spring AI adicionam capacidades avançadas de comunicação assíncrona e inteligência artificial.

## Estrutura do Projeto

```
odontofast/
├── src/main/java/com/example/odontofast/
│   ├── config/          # Configurações (Security, RabbitMQ, Actuator)
│   ├── controller/      # Controladores MVC e REST
│   ├── model/           # Entidades de domínio (JPA)
│   ├── repository/      # Interfaces de repositório (Spring Data)
│   └── service/         # Lógica de negócios e serviços
└── src/main/resources/
    ├── static/          # Recursos estáticos (CSS, JS, imagens)
    ├── templates/       # Templates Thymeleaf
    └── application.properties  # Configurações da aplicação
```

## Funcionamento da Aplicação

### Autenticação e Autorização

- O sistema implementa autenticação baseada em formulário com Spring Security
- Dois perfis de acesso: Dentista (padrão) e Administrador (acesso a monitoramento)
- Login realizado com CRO (número de registro odontológico) e senha

### Fluxo Principal

1. **Login/Cadastro**: O dentista acessa o sistema com suas credenciais
2. **Dashboard**: Exibe um resumo da agenda diária, consultas pendentes e emergenciais
3. **Agendamentos**: Permite visualizar e gerenciar consultas (confirmar, cancelar, adicionar)
4. **Perfil**: O usuário pode atualizar suas informações cadastrais

## Funcionalidades Implementadas

### 1. Gerenciamento de Usuários
- Cadastro de novos dentistas
- Atualização de perfil (nome, CRO, email, telefone)
- Autenticação segura

### 2. Painel Principal (Dashboard)
- Visualização rápida das consultas do dia
- Indicadores de desempenho (pacientes, emergências, pendências)

### 3. Gestão de Agendamentos
- Visualização da agenda por status (confirmado, pendente, cancelado)
- Adição de novos agendamentos via modal

### 4. Assistente de Anamnese com IA
- Geração de perguntas complementares para anamnese baseadas nas informações iniciais do paciente
- Integração com modelo de IA para personalização das questões
- Exportação das perguntas geradas (impressão/área de transferência)

### 5. Sistema de Notificações
- Implementação de mensageria assíncrona com RabbitMQ
- Notificações em tempo real para eventos do sistema (cadastros, atualizações)

### 6. Monitoramento (Área Administrativa)
- Visualização de métricas do sistema via Spring Actuator
- Monitoramento de saúde dos componentes (banco de dados, mensageria)
- Informações detalhadas sobre a aplicação

### 7. Internacionalização
- Suporte a idiomas (Português e Inglês)
- Tradução de interfaces e mensagens via arquivos de propriedades

## Viabilidade da Solução

A solução apresentada atende diretamente às necessidades dos dentistas no contexto da Odontoprev, oferecendo:

- **Eficiência operacional**: Reduz o tempo gasto com tarefas administrativas
- **Qualidade no atendimento**: O assistente de anamnese com IA promove uma avaliação mais completa do paciente
- **Gestão centralizada**: Unifica em uma única plataforma todas as informações necessárias
- **Integração futura**: Arquitetura preparada para integração com o app mobile de pacientes
- **Escalabilidade**: Uso de tecnologias robustas que permitem crescimento

A aplicação demonstra viabilidade comercial e técnica por:
- Utilizar tecnologias modernas e bem estabelecidas no mercado
- Seguir padrões de desenvolvimento que facilitam manutenção
- Implementar recursos de valor agregado (IA, mensageria)
- Possibilitar expansão com novos módulos

## Como Executar o Projeto

### Pré-requisitos

- Java JDK 21
- Maven 3.9+
- Oracle Database (ou usar H2 para testes locais)
- RabbitMQ Server (para o sistema de mensageria)
- Ollama com modelo local (para recursos de IA)


### Passos para Execução

1. Clone o repositório:
```bash
git clone https://github.com/sousa-sara/odontofast-spring-mvc.git
```

2. Navegue até o diretório do projeto:
```bash
cd odontofast-spring-mvc
cd odontofast
```

3. Configure as propriedades do banco de dados e RabbitMQ no arquivo `application.properties`

4. Execute o seguinte comando para iniciar a aplicação:
```bash
mvn spring-boot:run
```

5. Acesse a aplicação em: http://localhost:8080

### Credenciais de Acesso

Para autenticar-se como dentista:
- **CRO**: CRO12345
- **Senha**: senha123

Para autenticar-se como administrador:
- **CRO**: ADMIN9998
- **Senha**: admin123

## Conclusão

### Resumo do Projeto
O Odontofast apresenta uma solução robusta para a gestão odontológica, implementando com sucesso o padrão MVC em uma arquitetura distribuída. A utilização do Spring Boot permitiu o desenvolvimento rápido e consistente da aplicação, enquanto a integração com tecnologias avançadas como RabbitMQ e inteligência artificial agregou valor significativo à solução.

### Desafios e Aprendizados

**Desafios Enfrentados:**
- Implementação do Spring Security com múltiplos perfis (Dentista/Admin)
- Integração com o Oracle Database em ambientes de desenvolvimento distribuídos
- Configuração do RabbitMQ para mensageria assíncrona
- Implementação da internacionalização com troca dinâmica de idiomas

**Aprendizados:**
- A importância de uma arquitetura bem definida desde o início
- A vantagem da modularização para evolução incremental do projeto
- O valor de implementar testes automatizados para garantir a qualidade
- A necessidade de documentação clara para facilitar o desenvolvimento colaborativo

### Melhorias e Projetos Futuros

Para as próximas iterações do projeto, planejamos:
- **Integração Mobile**: Desenvolvimento da API REST para comunicação com o app de pacientes
- **Prontuário Eletrônico**: Implementação de módulo para registro e histórico de procedimentos
- **BI e Analytics**: Dashboard avançado com métricas de negócio e previsões
- **Expansão da IA**: Utilização de IA para sugerir tratamentos e identificar padrões
- **Integração com Planos**: API para verificação de cobertura de planos odontológicos em tempo real

O Odontofast demonstra o potencial da tecnologia para transformar a gestão odontológica, criando um diferencial competitivo para a Odontoprev no mercado.

## Instruções Adicionais para Execução

### Configuração do RabbitMQ

Para utilizar o sistema de notificações, é necessário instalar e configurar o RabbitMQ:

**Instalação com Docker:**
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
```

**Configuração:**
* O container Docker do RabbitMQ já estará rodando com as configurações necessárias
* O aplicativo se conectará automaticamente nas configurações padrão (localhost:5672)

## Configuração do Ollama para IA
Para utilizar o assistente de anamnese com IA:

**Instalação do Ollama:**
* Siga as instruções em https://ollama.ai/download

**Configuração do modelo:**
* Execute: `ollama pull gemma:2b`
* Verifique se o serviço Ollama está rodando na porta padrão (11434)

**Para Oracle (produção):**
- Certifique-se de que as credenciais e URL do banco Oracle estejam configuradas corretamente

### Observações Importantes

- O sistema utiliza Java 21, certifique-se de que esta versão está instalada
- Algumas funcionalidades como o assistente de anamnese necessitam de conexão à internet para funcionamento pleno
- Em caso de problemas com dependências, execute `mvn clean install` para reconstruir o projeto

## Integrantes Odontofast
- Felipe Amador - RM553528
- Leonardo Oliveira - RM554024
- Sara Sousa - RM552656
