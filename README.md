# CRUD com Spring Boot + JWT + Controle de Acesso (Admin/User)
Projeto desenvolvido como **prova prática** para a disciplina de Programação Avançada — ADS5S-A.  
A aplicação consiste em um sistema backend com autenticação via JWT, controle de usuários e permissões baseadas em roles.

## Funcionalidades
- Cadastro de usuários
- Login com geração de JWT
- Controle de acesso por tipo de usuário (`ROLE_USER` e `ROLE_ADMIN`)
- Endpoints protegidos por autenticação
- Admin pode visualizar, editar e excluir qualquer usuário
- Usuário comum pode visualizar e editar o próprio perfil  

---

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.5
- Spring Security
- JWT (via jjwt)
- Maven
- H2 Database (banco em memória)
- Lombok

---

## ⚙️ Como Rodar o Projeto
### Pré-requisitos
- Java JDK 17+
- Maven 3.8+
- Git

### Passos
1. **Clone o repositório**  
   ```bash
   git clone https://github.com/seu-usuario/seu-repo.git
   cd seu-repo
   ```
Compile o projeto
   ```bash
  mvn clean install
   ```
Rode a aplicação
  ```bash
mvn spring-boot:run
   ```
Acesse a aplicação
API REST disponível em: http://localhost:8080

Autenticação
O login gera um token JWT que deve ser enviado no header das próximas requisições:
   ```css
Authorization: Bearer {seu_token}
   ```

Rotas da API
1. Cadastro
POST /auth/register
Body:
 ```json
{
  "nome": "Pedro Toscano",
  "email": "pedro@email.com",
  "senha": "123456",
  "role": "ADMIN"
}
 ```
2. Login
POST /auth/login
Body:

 ```json
{
  "email": "pedro@email.com",
  "senha": "123456"
}
 ```
Retorno:
 ```json
{
  "token": "JWT_TOKEN_AQUI"
}
 ```
3. Ver perfil (USER ou ADMIN)
GET /usuarios/me
Header: Authorization: Bearer {token}

4. Editar perfil (USER ou ADMIN)
PUT /usuarios/me
Body:
 ```json
{
  "nome": "Novo Nome"
}
 ```
5. Listar todos os usuários (ADMIN)
GET /usuarios
Acesso restrito a ADMIN

6. Atualizar qualquer usuário (ADMIN)
PUT /usuarios/{id}
Body:

 ```json
{
  "nome": "Atualizado",
  "email": "novo@email.com",
  "role": "USER"
}
 ```
7. Deletar usuário (ADMIN)
DELETE /usuarios/{id}
Acesso restrito a ADMIN

Testes

Para testar:
- Crie 2 usuários: 1 com role=ADMIN e outro com role=USER
- Faça login com ambos e teste os acessos com o token
- Teste chamadas sem token e com role errada para garantir segurança

Estrutura do Projeto
 ```bash
src/main/java/com/crud/eliel
├── config              # Configuração de segurança (Spring Security)
├── controller          # Controladores REST
├── dto                # Objetos de transferência de dados
├── entity              # Entidades JPA
├── repository          # Interface de persistência
├── security            # Filtro JWT e utilitários de token
├── service             # Lógica de negócio
└── CrudEliel1Application.java  # Classe principal
 ```

Desenvolvido por Pedro Toscano

RA: 23202879-2

Curso: Análise e Desenvolvimento de Sistemas

Turma: ADSIS 5ªA
