Task Manager - Gerenciador de Eventos

Uma aplicação REST API desenvolvida em **Spring Boot** para gerenciar eventos de forma simples e eficiente.

## Funcionalidades

- **Criar eventos** com validação automática
- **Listar todos** os eventos cadastrados
- **Buscar evento** por ID
- **Atualizar parcialmente** dados do evento
- **Deletar eventos** do sistema
- **Datas automáticas** de criação e alteração
- **Validação robusta** de dados de entrada

## Tecnologias

- **Java 17**
- **Spring Boot 3.5.10**
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados
- **Maven** - Gerenciamento de dependências
- **Banco de Dados H2** (padrão) - Desenvolvimento local

## Pré-requisitos

- Java 17 ou superior
- Maven 3.8+
- Git (opcional)

## Como Executar

### Passo 1: Clone o Repositório

```bash
git clone https://github.com/juaoo99/taskmanager_java.git
cd taskmanager
```

### Passo 2: Instale as Dependências

O Maven vai baixar automaticamente todas as dependências do `pom.xml`:

```bash
mvn clean install
```

ou

```bash
./mvnw clean install
```

**Isso irá:**

- Baixar todas as dependências do Spring Boot
- Compilar o código-fonte
- Executar os testes
- Criar o arquivo JAR em `target/`

> Primeira execução pode demorar 5-10 minutos (dependendo da internet)

### Passo 3: Execute a Aplicação

**Opção A: Via Maven (Recomendado para desenvolvimento)**

```bash
mvn spring-boot:run
```

ou

```bash
./mvn spring-boot:run
```

**Opção B: Via arquivo JAR (Recomendado para produção)**

```bash
mvn clean package
java -jar target/taskmanager-0.0.1-SNAPSHOT.jar
```

### Passo 4: Verifique se está Rodando

Você verá algo assim no terminal:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_|\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.10)

Started TaskmanagerApplication in 2.456 seconds (JVM running for 2.789s)
```

### Passo 5: Acesse a API

A API estará disponível em:

- **Base URL:** `http://localhost:8080`
- **Endpoints:** `http://localhost:8080/eventos`

- **H2 Console:** `http://localhost:8080/h2-console`

### 🔧 Troubleshooting

#### Erro: "Porta 8080 já está em uso"

```bash

# Altere a porta em src/main/resources/application.properties
server.port=8081
```

#### Erro: "Java not found"

Certifique-se de que Java 17+ está instalado:

```bash
java -version
```

#### Erro: "Maven not found"

Certifique-se de que Maven está instalado:

```bash
mvn -version
```

#### Erro ao compilar

```bash
# Limpe e tente novamente
mvn clean compile
```

## Endpoints

### 1. **Criar Evento** (POST)

```http
POST /eventos
Content-Type: application/json

{
  "nome": "Conferência de Tecnologia 2026",
  "data": "2026-03-15T14:30:00",
  "local": "Centro de Convenções - São Paulo",
  "capacidade": 500
}
```

**Resposta:** `201 CREATED`

### 2. **Listar Todos os Eventos** (GET)

```http
GET /eventos
```

**Resposta:** `200 OK` - Lista de todos os eventos

### 3. **Buscar Evento por ID** (GET)

```http
GET /eventos/{id}
```

**Resposta:** `200 OK` - Evento encontrado ou `404 NOT FOUND`

### 4. **Atualizar Evento** (PATCH)

```http
PATCH /eventos/{id}
Content-Type: application/json

{
  "nome": "Novo nome",
  "capacidade": 600
}
```

**Resposta:** `200 OK` - Evento atualizado ou `404 NOT FOUND`

### 5. **Deletar Evento** (DELETE)

```http
DELETE /eventos/{id}
```

**Resposta:** `204 NO CONTENT` - Evento deletado ou `404 NOT FOUND`

## Modelo de Dados

### Evento

```json
{
  "id": 1,
  "nome": "Conferência de Tecnologia 2026",
  "data": "2026-03-15T14:30:00",
  "local": "Centro de Convenções - São Paulo",
  "capacidade": 500,
  "dataCriacao": "2026-01-30T10:30:45",
  "dataUltimaAlteracao": "2026-01-30T10:30:45"
}
```

### Campos

- **id** - Identificador único (gerado automaticamente)
- **nome** - Nome do evento (obrigatório)
- **data** - Data e hora do evento (obrigatório)
- **local** - Local do evento (obrigatório)
- **capacidade** - Capacidade máxima (obrigatório)
- **dataCriacao** - Data de criação (gerada automaticamente)
- **dataUltimaAlteracao** - Data da última alteração (gerada automaticamente)

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/taskmanager/
│   │   ├── TaskmanagerApplication.java      # Classe principal
│   │   ├── controller/
│   │   │   └── EventoController.java        # Endpoints REST
│   │   ├── model/
│   │   │   └── Evento.java                  # Entidade JPA
│   │   ├── repository/
│   │   │   └── EventoRepository.java        # Acesso a dados
│   │   └── service/
│   │       └── impl/
│   │           └── EventoServiceImpl.java    # Lógica de negócio
│   └── resources/
│       └── application.properties           # Configurações
└── test/
    └── java/com/example/taskmanager/
        └── TaskmanagerApplicationTests.java # Testes
```

## Configuração

### application.properties

```properties
# Configurações padrão do Spring Boot
server.port=8080

# Configuração do banco H2 (padrão)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## Testes

Execute os testes com:

```bash
mvn test
```

## Exemplo de Uso Completo

### 1. Criar um evento

```bash
curl -X POST http://localhost:8080/eventos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Workshop Spring Boot",
    "data": "2026-02-15T09:00:00",
    "local": "São Paulo",
    "capacidade": 50
  }'
```

### 2. Listar todos os eventos

```bash
curl http://localhost:8080/eventos
```

### 3. Buscar evento específico

```bash
curl http://localhost:8080/eventos/1
```

### 4. Atualizar evento

```bash
curl -X PATCH http://localhost:8080/eventos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "capacidade": 60
  }'

```

### 5. Deletar evento

```bash
curl -X DELETE http://localhost:8080/eventos/1
```
