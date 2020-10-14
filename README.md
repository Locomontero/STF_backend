# 1 - BACKEND_STF  

## 1.1 - Objetivo   

> No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução backend para gerenciar essas sessões de votação.   
> Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST: 

*	Cadastrar uma nova pauta
*	Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
*	Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
*	Contabilizar os votos e dar o resultado da votação na pauta

# 2 - Tarefas  

- [x] Cadastrar uma nova pauta
- [x] Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
- [x] Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
- [x] Contabilizar os votos e dar o resultado da votação na pauta

## 2.2 - Tarefa Bônus   

- [x] 1 - Integração com sistemas externos  
- [ ] 2 - Mensageria e filas  
- [ ] 3 - Performance  
- [ ] 4 - Versionamento da API  

# 3 - Tecnologias usadas 

* Postgresql como banco de dados
* Java 8 utilizando Spring boot

# 4 - TODO 
 - [x] 1 - Finalização dos testes da service e controller  
 - [ ] 2 - Adicionar Logs.
 - [ ] 3 - Utlização do kafka para publicar mensagens

# 5 - Swagger  
[Swagger](https://api-voting-sessions.herokuapp.com/swagger-ui.html)
