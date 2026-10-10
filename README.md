# Avalição Prática: API REST

# Sobre

Projeto desevolvido em Java, Api REST, JPA, MySQL e documentação em Swagger e Javadoc para a diciplina de Backend do SENAI/CentroWEG.


Projeto foi disponibilizado em um arquivo .zip pelo professor, para ser implementado as classes Controller e a documentação.

# Contextualização

Uma empresa está desenvolvendo o backend do projeto API de Telemetria Industrial, responsável pelo monitoramento de motores e emissão de alertas em tempo real.

Ao executar a aplicação e consultar as equipes de Frontend e Suporte/Sustentação, foram relatados alguns problemas.

# Problemas

* Integração bloqueada: A equipe de Frontend não consegue consumir a API nem realizar testes, pois não há uma especificação acessível dos endpoints, parâmetros, formatos de requisição (payloads) ou respostas HTTP esperadas.


* Comportamento inoperante: As requisições enviadas para as rotas da aplicação retornam erros ou respostas vazias/não implementadas.


* Dificuldade de manutenção: A equipe interna não consegue identificar as regras de negócio aplicadas nas camadas de serviço devido à ausência de documentação do código-fonte.

# Objetivo

Assumir a sustentação e finalização da API de Telemetria Industrial, garantindo que a equipe de Frontend consuma a API e realize testes, que as respostas sejam retornadas corretamente e que seja aplicada a documentação necessária.

# Lacunas existentes

* Infuncionalidade dos controllers REST, AlertaMotorController, MotorController, SetorController e TelemetriaController, impossibilitando o funcionamento do sistema;


* Necessidades de documentação externa e interna, Javadoc e Swagger respectivamente;


* Relatório de diagnóstico técnico.

# Divisão de camadas

* Config;
* Controller;
* DTO;
* Entity;
* Exception;
* Mapper;
* Repository;
* Service;
* Classe main;

# Rotas implementadas

### Alertas

* @PostMapping - Cadastrar
* @GetMapping - Listas todos
* @GetMapping("/motores/{motorId}") - Buscar Por Motor
* @PatchMapping("/{alertaId}") - Atualizar Status Resolucao

### Motores

* @PostMapping - Cadastrar
* @GetMapping - Listas todos
* @GetMapping("/{motorId}") - Buscar Por Id
* @GetMapping("/codigoAtivo") - Buscar Por CodigoAtivo
* @PutMapping("/{motorId}") - Atualizar
* @PatchMapping("/{motorId}") - Atualizar Status

### Setores

* @PostMapping - Cadastrar
* @GetMapping - Listar todos
* @GetMapping("/{setorId}") - Buscar Por Id
* @PutMapping("/atualizar/{setorId}") - Atualizar

### Telemetrias

* @GetMapping - Listas Todos
* @GetMapping("/{telemetriaId}") - Buscar Por Id
* @GetMapping("/motores/{motorId}") - Listar Por Motor
* @PostMapping - Registar

# Resultados

### Entrega 1: 
Implementação funcional dos controllers REST (AlertaMotorController, MotorController, SetorController e TelemetriaController), totalmente implementados, garantindo delegação correta para a camada de Service, suporte a validações (@Valid), paginação, manipulação de exceções e emissão adequada de status HTTP e cabeçalhos (como Location no HTTP 201 Created).

### Entrega 2: 
Documentação externa completa dos contratos REST via anotações do Swagger / OpenAPI 3 (@Tag, @Operation, @ApiResponses, @ApiResponse, etc.) em todos os endpoints dos controllers, permitindo a visualização e testes pela interface do Swagger UI (/swagger-ui.html).

### Entrega 3:
Descrição do projeto, relatório de Diagnóstico Técnico e Decisões.

# Documentação

A estratégia de separação entre a documentação de contrato externo e de código interno é simples.


É uma boa prática de mercado separar a documentação de contrato externo, o Swagger, nas classes Controller, permitindo vizualizar a documentação técnica criada externamente, como por exemplo na web, acessando o caminho localhost:(porta)/swagger-ui.html.
Onde ele foi aplicado no projeto

Já a documentação de código interno, o Javadoc, foi aplicado nas outras camadas do projeto, permitindo vizualizar a documentação técnica criada internamente no código, para futuras manutenções, implementações e etc. Explicando os métodos, regras de negócio e etc. Essa não é possível vizualizar externamente.