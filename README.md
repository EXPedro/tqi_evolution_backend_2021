# 💸 Controle de Empréstimos 🤑

###  💰 API Rest

💲 Backend de um sistema de controle de empréstimos, com  cadastro de clientes e de empréstimos, e que lista os empréstimos ou o detalhe de um empréstimo realizado por algum cliente.  
🔒 Possui sistema de login com encriptação/decriptação de senha, para autenticação de cliente cadastrado, criação de novos empréstimos, 
e visualização destes empréstimos pelo cliente- inclusive com detalhamento dos mesmos💰

### Sumário  

[Ferramentas utilizadas](#ferramentas-utilizadas)     
[O problema](#o-problema)     
[A solução](#a-soluo)     
[Constraints](#constraints)     
[Banco de dados](#banco-de-dados)     
[Spring Security](#spring-security)     
[Bugs](#bugs)              
[Postman](#postman)     
[Modelos de Requisição](#modelos-de-requisio)     
[Melhoria Contínua](#melhoria-contnua)  

### 👨‍🔧 Ferramentas utilizadas    

- **Banco de dados H2:** Banco de dados completo, com a vantagem de rodar direto na JVM
- **Spring Web:** Dependência que adiciona Servlets e Portles na aplicação
- **Spring JPA:** Dependência que adiciona funcionalidades do padrão JPA. Implementação com hibernate
- **Lombok:** Auxilia o desenvolvimento de aplicações, como na utilização de annotations para gerar getters, setters e construtores
- **Mapstruct:** Realiza o mapeamento de objetos em entidades, e vice-versa. Bastante útil quando se trabalha com banco de dados

[volta ao topo](#sumrio)

### 🤯 O problema

Uma empresa de empréstimo precisa criar um sistema de análise de crédito para fornecer aos seus clientes as seguintes funcionalidades:  
**i. Cadastro de clientes**  
O cliente pode cadastrar: nome, e-mail, CPF, RG, endereço completo, renda e senha.  
**ii. Login**  
A autenticação será realizada por e-mail e senha.  
**iii. Solicitação de empréstimo**  
Para solicitar um empréstimo, precisamos do valor do empréstimo, data da primeira parcela e quantidade de parcelas. 
O máximo de parcelas será 60 e a data da primeira parcela deve ser no máximo 3 meses após o dia atual.  
**iv. Acompanhamento das solicitações de empréstimo**  
O cliente pode visualizar a lista de empréstimos solicitados por ele mesmo e também os detalhes de um de seus empréstimos. 
Na listagem, devemos retornar no mínimo o código do empréstimo, o valor e a quantidade de parcelas.  
No detalhe do empréstimo, devemos retornar: 
código do empréstimo, valor, quantidade de parcelas, data da primeira parcela, e-mail do cliente e renda do cliente.

[volta ao topo](#sumrio)

### 🧠 A solução

Foi criado um banco de dados com 3 tabelas: Clientes, Emprestimos e Login. A tabela Login é "abastecida" pela tabela Clientes. Bem como a tabela Emprestimos (apenas Clientes cadastrados podem criar entidades empréstimo).  
Foram mapeadas estas tabelas no backend, utilizando o ***mapstruct***. Com ele, cada entidade do banco de dados é refletida num **objeto DTO**, numa demonstração de como deve ser feita a manipulação dos dados pelo backend.
Foi definida a porta 8087 para o servidor do Tomcat, logo esta API em modo de desenvolvimento estará localizada em "http://localhost:8087/"
Foram definidos os ***endpoints*** para cada requisição feita ao banco de dados. São eles:
- Requisição de **POST**  em "api/v1/cadastro" Para o cadastro de clientes, ponto de entrada desta API
- Requisição de **POST**  em "api/v1/emprestimos/{idCliente}" Para o cadastro de empréstimos pela id do cliente
- Requisição de **GET** em "api/v1/emprestimos/{idCliente}" Para listar os empréstimos pela id do cliente
- Requisição de **GET** em "api/v1/detalhes/{idEmprestimo}" Para listar os detalhes de cada empréstimo   
Por fim, foi instalada a dependência Spring Security, que adiciona uma camada de segurança nos dados, e permite que as senhas da API 
trafeguem na rede devidamente codificadas.

[volta ao topo](#sumrio)

### 🛑 Constraints

O projeto possui algumas regras para a validação dos seus campos. Quase todas "cobertas" pelas javax.validation.constraints, incluindo o prazo máximo de 60 meses para o pagamento. Essa dependência foi adicionada ao pom.
Foi utilizado também o value unique, da annotation Column, para que o email persistido no banco de dados também seja único.
No caso da data inicial do empréstimo, ela não pode ser 3 meses depois da data atual. Para essa demonstração, foi criada uma 
annotation para implementar essa constraint, utilizando a data de agora da JVM.

[volta ao topo](#sumrio)
   
### 🗄️ Banco de Dados   

O spring security está configurado para ignorar requisições de senha para acessar o console do banco de dados, 
que está no *endpoint* **/h2**. A senha é 123. Esta API cria uma pasta para "guardar" o banco de dados, em "~/tqi/tqi-backend-db".     

![Console H2](src/main/resources/assets/H2.png?raw=true)    

O banco de dados criado está estruturado da seguinte maneira:  

![Estrutura BD](src/main/resources/assets/estruturaBD.png?raw=true)

![Estrutura Login](src/main/resources/assets/login.png?raw=true)   

\* A tabela "Login" é preenchida automaticamente ao se cadastrar um novo cliente. Tirando **email** e **password**, todos os outros
parâmetros são criados com valores **default**, pois esta tabela é apenas a título de demonstração.

[volta ao topo](#sumrio)

### 🛡️ Spring Security    

Esta API implementa uma camada de segurança ao utilizar o Spring Security. Para esta demonstração, foi utilizada a codificação/
decodificação da senha do cliente, evitando que ela fique exposta durante as requisições da API. Desta forma, as senhas são
armazenadas já no banco de dados "encriptadas", e apenas o cliente que a cadastrou é quem sabe qual é a senha. Utilizando a mesma 
senha durante a "criação" de Cliente/Login ("123456") pode-se perceber como ela fica armazenada nas diferentes tabelas (Cliente e 
Login) do banco de dados, demonstrando a praticidade com que se implementa a segurança com o auxílio do spring security como pode 
ser visto na figura abaixo:    

![Senha Criptografada](src/main/resources/assets/senhas.png?raw=true)   
\*senha "123456" armazenada de forma codificada no banco de dados        

Como este projeto é uma demonstração, não foram implementados todos os potenciais recursos disponibilizados pelo *framework*, embora 
no código encontre-se comentadas algumas configurações para uma futura implementação.

[volta ao topo](#sumrio)

### 🐛 Bugs     

Conforme relatado [aqui](https://github.com/mapstruct/mapstruct/releases/tag/1.4.2.Final), o mapstruct pode fazer
um mapeamento errado (cfme #2251 e #2301). De qualquer jeito, uma "solução" paliativa, aparentemente, é editar os
arquivos de mapeamento (ClienteMapper.java, EmprestimoMapper.java, ...), alterando até espaçamentos... e então a
criação de entidades (criaCliente e criaEmprestimo) torna-se novamente funcional.

[volta ao topo](#sumrio)

### 📬 Postman

Pode-se utilizar o postman para fazer as requisições em 'http://localhost:8087/'
- Requisição de POST em "api/v1/cadastro" Para o cadastro de clientes, ponto de entrada desta API

  ![POST cadastroCliente](src/main/resources/assets/cria-c.png?raw=true)

- Requisição de POST em "api/v1/emprestimos/{idCliente}" Para o cadastro de empréstimos pela id do cliente

  ![POST cadastroEmpréstimo](src/main/resources/assets/cria-e.png?raw=true)

- Requisição de GET em "api/v1/emprestimos/{idCliente}" Para listar os empréstimos pela id do cliente

  ![GET emprestimosCliente](src/main/resources/assets/lista-e.png?raw=true)

- Requisição de GET em "api/v1/detalhes/{idEmprestimo}" Para listar os detalhes de cada empréstimo

  ![GET detalheEmprestimo](src/main/resources/assets/detalha-e.png?raw=true)  

[volta ao topo](#sumrio)  

### 🤔 Modelos de requisição

Alguns dados já formatados para inserção (um por vez) via **postman**, 
em 'http://localhost:8087/api/v1/cadastro/' :    

```json 
{
  "nome": "Gilberto Passos Gil Moreira",
  "email": "gilberto_gil@mpb.com.br",
  "password": "123456",
  "cpf": "51878971018",
  "rg": "43521673",
  "endereco": "Rua Emiliano Borba, 376 ap32",
  "renda": 35200.00
}

{
    "nome": "Odair José de Araújo", 
    "email": "odairjose@mpb.com.br",
    "password": "123456",
    "cpf": "99385159089",
    "rg": "69954784",
    "endereco": "Rua Barão Cerro Azul, 44",
    "renda": 12400.00
}   

{
    "nome": "Raul Santos Seixas", 
    "email": "raulzito@rock.com.br",
    "password": "123456",
    "cpf": "42243924021",
    "rg": "33542435",
    "endereco": "Rua Augusta, 120",
    "renda": 18730.33
}   
```   

Depois de cadastrados os clientes, podem ser cadastrados os empréstimos para cada um. Alguns exemplos de inserção 
aceitos em em 'http://localhost:8087/api/v1/emprestimos/{idCliente}':

```json   

{
    "valorTotal": 370000.00,
    "dataInicial": "2022-03-27",
    "prazo": 48
}

{
    "valorTotal": 52000.00,
    "dataInicial": "2022-01-28",
    "prazo": 48
}  

{
    "valorTotal": 500000.00,
    "dataInicial": "2022-03-08",
    "prazo": 60
}  

```      

O formato dos dados pode ser visto acima. Caso algum dos valores "bata" numa constraint definida, o registro não é salvo 
e retorna um erro. Para fins desta demonstração, não foram criados tratamentos para todos os erros, o que pode ser 
implementado posteriormente. Apenas alguns erros de validação são tratados nesta API.  
Para retornar os empréstimos de cada cliente, no endereço 'http://localhost:8087/api/v1/emprestimos/{idCliente}'  
Por fim, para requisitar o detalhamento de cada empréstimo, deve-se usar o *endpoint* 'http://localhost:8087/api/v1/detalhes/{idEmprestimo}'    
\* os valores entre chaves { } nos *endpoints* devem ser substituídas pelas respectivas id's.

[volta ao topo](#sumrio)    

### 🌞 Melhoria contínua    

Apesar desta API ser apenas uma demonstração, durante o seu desenvolvimento foram observadas diversas oportunidades de melhoria,
tanto para o código como para o funcionamento seguro da API.   
O envio automático de email para a confirmação do cadastro pode ser implementada. Também pode ser criada uma página customizada
para o "login" (como indicado em "WebSecurityConfig.java"), e definida uma "home.html" para onde a API redireciona após um login 
realizado com sucesso.   
Algumas configurações do Spring Security podem ser feitas para aumentar a segurança da aplicação, restringindo o acesso aos usuários
não logados a determinados endpoints, através dos "antMatchers". Também podem ser implementadas facilmente, como indicado no código,
a função "rememberMe()" para que o browser do cliente se "lembre" que este cliente já está logado.   
A resposta das requisições de GET relacionadas aos empréstimos também pode ser melhorada, criando objetos que mapeiem cada uma das 
requisições, para uma resposta mais limpa. Isso foi feito na classe "DetalheEmprestimo.java", por exemplo.   
Todo o processo de desenvolvimento implica sempre na busca pela melhoria contínua, seja de métodos, mapeamento de objetos, 
segurança, visando a otimização do código.  
As necessidades do cliente, principalmente, devem ser ouvidas e, na medida do possível, implementadas.  

#### 🟡 Projeto feito em 05/01/2022.




