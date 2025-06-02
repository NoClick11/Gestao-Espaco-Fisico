# 📋 Gestão de Espaço Físico - Sistema de Reservas

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6%2B-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

</div>

## 🌟 Visão Geral

Sistema completo para gestão de espaços físicos, equipamentos e solicitações de reserva, desenvolvido com arquitetura moderna e interface intuitiva.

**Características principais:**
- Backend robusto em **Spring Boot** (Java)
- Frontend responsivo em **HTML5, CSS3 e JavaScript** puro
- Arquitetura RESTful
- Interface moderna e intuitiva

## 🚀 Funcionalidades Principais

<div align="center">

| Funcionalidade | Status | Descrição |
|---|---|---|
| 🏢 **Gestão de Espaços** | ✅ | Cadastro completo de espaços físicos |
| 🖥️ **Gestão de Equipamentos** | ✅ | CRUD de equipamentos disponíveis |
| 📝 **Sistema de Reservas** | ✅ | Solicitação de reservas com equipamentos |
| ✅ **Aprovação de Solicitações** | ✅ | Workflow de aprovação/reprovação |
| 📊 **Dashboard** | ✅ | Métricas e indicadores em tempo real |
| 👥 **Gestão de Usuários** | ✅ | CRUD completo para usuários |

</div>

## 🔧 Stack Tecnológica

### Backend
```
Java 17+
├── Spring Boot 3.1.5
│   ├── Spring Data JPA
│   ├── Spring Web
│   └── Spring Boot Actuator
├── H2 Database (desenvolvimento)
└── PostgreSQL (produção)
```

### Frontend
```
HTML5 + CSS3 + JavaScript ES6+
├── HTML5 Semântico
├── CSS3 Moderno (Flexbox/Grid)
├── JavaScript Vanilla
└── Fetch API
```

## 📚 Estrutura do Projeto

```
gestao-espaco-fisico/
│
├── 📁 backend/                           # Aplicação Spring Boot
│   ├── 📁 src/
│   │   ├── 📁 main/
│   │   │   ├── 📁 java/com/manuelneto/gestaoespacofisico/
│   │   │   │   ├── 📁 controller/        # Controllers REST
│   │   │   │   │   ├── 📄 UsuarioController.java
│   │   │   │   │   ├── 📄 EspacoController.java
│   │   │   │   │   ├── 📄 EquipamentoController.java
│   │   │   │   │   └── 📄 SolicitacaoController.java
│   │   │   │   │
│   │   │   │   ├── 📁 dto/               # Data Transfer Objects
│   │   │   │   │   ├── 📄 UsuarioDTO.java
│   │   │   │   │   ├── 📄 EspacoDTO.java
│   │   │   │   │   ├── 📄 EquipamentoDTO.java
│   │   │   │   │   └── 📄 SolicitacaoDTO.java
│   │   │   │   │
│   │   │   │   ├── 📁 entity/            # Entidades JPA
│   │   │   │   │   ├── 📄 Usuario.java
│   │   │   │   │   ├── 📄 Espaco.java
│   │   │   │   │   ├── 📄 Equipamento.java
│   │   │   │   │   └── 📄 Solicitacao.java
│   │   │   │   │
│   │   │   │   ├── 📁 mapper/            # Mappers DTO-Entity
│   │   │   │   │   └── 📄 EntityMapper.java
│   │   │   │   │
│   │   │   │   ├── 📁 repository/        # Repositórios JPA
│   │   │   │   │   ├── 📄 UsuarioRepository.java
│   │   │   │   │   ├── 📄 EspacoRepository.java
│   │   │   │   │   ├── 📄 EquipamentoRepository.java
│   │   │   │   │   └── 📄 SolicitacaoRepository.java
│   │   │   │   │
│   │   │   │   ├── 📁 service/           # Lógica de Negócio
│   │   │   │   │   ├── 📄 UsuarioService.java
│   │   │   │   │   ├── 📄 EspacoService.java
│   │   │   │   │   ├── 📄 EquipamentoService.java
│   │   │   │   │   └── 📄 SolicitacaoService.java
│   │   │   │   │
│   │   │   │   └── 📄 GestaoEspacoFisicoApplication.java
│   │   │   │
│   │   │   └── 📁 resources/
│   │   │       ├── 📄 application.properties
│   │   │       └── 📄 data.sql
│   │   │
│   │   └── 📁 test/                      # Testes Unitários
│   │       └── 📁 java/
│   │
│   └── 📄 pom.xml                        # Dependências Maven
│
└── 📁 frontend/                          # Interface Web
    ├── 📁 css/
    │   └── 📄 style.css                  # Estilos Globais
    │
    ├── 📁 js/
    │   ├── 📄 api.js                     # Funções de API
    │   ├── 📄 dashboard.js               # Dashboard Logic
    │   ├── 📄 equipamentos.js            # CRUD Equipamentos
    │   ├── 📄 espacos.js                 # CRUD Espaços
    │   ├── 📄 nova-solicitacao.js        # Nova Solicitação
    │   ├── 📄 solicitacoes.js            # Lista Solicitações
    │   └── 📄 usuarios.js                # CRUD Usuários
    │
    └── 📄 Páginas HTML
        ├── 📄 index.html                 # Dashboard Principal
        ├── 📄 usuarios.html              # Gestão de Usuários
        ├── 📄 espacos.html               # Gestão de Espaços
        ├── 📄 equipamentos.html          # Gestão de Equipamentos
        ├── 📄 nova-solicitacao.html      # Nova Solicitação
        └── 📄 solicitacoes.html          # Lista de Solicitações
```

## 🌐 Documentação da API

### 👥 Usuários `/api/usuarios`

<details>
<summary><strong>Endpoints disponíveis</strong></summary>

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/` | Lista todos os usuários | ✅ |
| `POST` | `/` | Cria novo usuário | ✅ |
| `GET` | `/{id}` | Busca usuário por ID | ✅ |
| `PUT` | `/{id}` | Atualiza usuário | ✅ |
| `DELETE` | `/{id}` | Remove usuário | ✅ |

</details>

### 🏢 Espaços Físicos `/api/espacos`

<details>
<summary><strong>Endpoints disponíveis</strong></summary>

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/` | Lista todos os espaços | ✅ |
| `POST` | `/` | Cria novo espaço | ✅ |
| `GET` | `/{id}` | Busca espaço por ID | ✅ |
| `PUT` | `/{id}` | Atualiza espaço | ✅ |
| `DELETE` | `/{id}` | Remove espaço | ✅ |

</details>

### 🖥️ Equipamentos `/api/equipamentos`

<details>
<summary><strong>Endpoints disponíveis</strong></summary>

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/` | Lista todos os equipamentos | ✅ |
| `POST` | `/` | Cria novo equipamento | ✅ |
| `GET` | `/{id}` | Busca equipamento por ID | ✅ |
| `PUT` | `/{id}` | Atualiza equipamento | ✅ |
| `DELETE` | `/{id}` | Remove equipamento | ✅ |

</details>

### 📝 Solicitações `/api/solicitacoes`

<details>
<summary><strong>Endpoints disponíveis</strong></summary>

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/` | Lista todas solicitações | ✅ |
| `POST` | `/` | Cria nova solicitação | ✅ |
| `GET` | `/pendentes` | Lista solicitações pendentes | ✅ |
| `GET` | `/pendentes/count` | Conta solicitações pendentes | ✅ |
| `GET` | `/{id}` | Busca solicitação por ID | ✅ |
| `GET` | `/usuario/${usuarioId}` | Lista solicitacoes por id | ✅ |
| `GET` | `/historico-por-espaco/{espacoNome}` | Lista espaços por nome | ✅ |
| `PUT` | `/{id}` | Atualiza solicitação | ✅ |
| `PUT` | `/{id}/status` | Atualiza status da solicitação | ✅ |
| `DELETE` | `/{id}` | Remove solicitação | ✅ |

</details>

## 🛠️ Como Executar o Projeto

### 📋 Pré-requisitos

- **Java 17+** - [Download](https://adoptium.net/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **Navegador Web Moderno**

### 🚀 Backend (Spring Boot)

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/gestao-espaco-fisico.git

# Navegue para o diretório do backend
cd gestao-espaco-fisico/backend

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

### 🌐 Frontend

#### Opção 1: Live Server (VSCode)
1. Instale a extensão **Live Server** no VSCode
2. Abra a pasta `frontend/`
3. Clique com botão direito em `index.html`
4. Selecione **"Open with Live Server"**

#### Opção 2: Servidor HTTP Simples

**Python:**
```bash
cd frontend/
python -m http.server 5500
```

**Node.js:**
```bash
cd frontend/
npx serve .
```

**PHP:**
```bash
cd frontend/
php -S localhost:5500
```

O frontend estará disponível em: `http://localhost:5500`

## 🤝 Como Contribuir

Contribuições são muito bem-vindas! Siga estes passos:

1. **Fork** o projeto
2. Crie sua feature branch: `git checkout -b feature/nova-feature`
3. Commit suas mudanças: `git commit -m 'feat: adiciona nova feature'`
4. Push para a branch: `git push origin feature/nova-feature`
5. Abra um **Pull Request**

### 📝 Padrões de Commit

- `add:` nova funcionalidade
- `fix:` correção de bug
- `docs:` documentação
- `style:` formatação
- `refactor:` refatoração
- `test:` testes

## 📄 Licença

<div align="center">

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

</div>
