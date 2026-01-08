# 🦊 Toca

> **"Um refúgio para quem vive de livros, gibis e filmes."**

Bem-vindo à **Toca**, uma plataforma dedicada ao debate e compartilhamento de experiências sobre cultura pop. Este repositório centraliza tanto a interface do usuário (Frontend) quanto o motor que sustenta tudo: o **Toca CMS** (Backend).

---

## Estrutura do Projeto

O projeto é organizado para separar as responsabilidades de interface e de gerenciamento de dados:

### 1. Frontend (`/toca`)

A interface web focada na experiência do usuário, organizada de forma semântica para facilitar a manutenção:
* **assets/**: 
    * `images/`: Identidade visual, ícones e mídias.
    * `css/`: Estilização e layouts.
    * `js/`: Scripts globais e interatividade.
* **pages/**: Estrutura das páginas HTML e templates da plataforma.
* **src/**: Lógica de componentes e integração direta com a API.

### 2. Backend (`/toca-cms`)

A espinha dorsal do sistema, desenvolvida em **Java**, atuando como a API oficial do projeto.
* **API REST**: Fornece os endpoints de dados para o frontend.
* **Gestão de Conteúdo**: Administração centralizada de metadados (livros, gibis, filmes).
* **Moderação**: Ferramentas de controle de comunidade e comentários.

---

## Tecnologias Utilizadas

* **Frontend:** HTML5, CSS3, JavaScript (Vanilla).
* **Backend:** Java (API Toca CMS).
* **Banco de Dados:** MySQL/PostgreSQL.
* **Gerenciamento:** Toca CMS para controle total de posts e usuários.

---

## Como Executar

### Pré-requisitos

* Java JDK 17+ instalado.
* Um servidor web simples para o frontend (ou extensão Live Server no VS Code).

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Capyvara13/Toca.git
    ```
2.  **Inicie o Backend (Toca CMS):**
    Navegue até a pasta `toca-cms` e execute o projeto Java através da sua IDE ou via terminal (Maven).
3.  **Inicie o Frontend (Toca):**
    Abra a pasta `toca` no seu navegador. Certifique-se de que as chamadas de API nos arquivos JavaScript em `src/` ou `assets/js/` estejam apontando para a porta correta do Toca CMS (ex: `http://localhost:8080`).

---

## Descrição Técnica: Toca CMS

O **Toca CMS** é a solução de back-end para gerenciar o conteúdo e a comunidade do site. A partir de uma interface intuitiva, permite administrar posts sobre livros, gibis e filmes, gerenciar perfis de usuário e moderar comentários, garantindo que a Toca seja sempre um ambiente seguro e iluminado para novas ideias.

---

**Desenvolvido com ❤️ por Rapovara.** 🦊