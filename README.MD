# API USUARIO

### SOBRE A API
Api de gerenciameto de usuario.
Foi construido com Java, Spring Boot, JPA é Hibenate.

Projeto construido para estudos.

### CARACTERISTICAS
Esta API fornece endpoints e ferramentas http para o seguinte:
- asdfasd
- asdfasdf
- asfdasdf

### DETALHAMENTO
Rota <i>/usuario</i> metodo <i>`POST`</i>

Objetivo: Criar um usuario

Envio:
```json
{
	"nome":"Nome completo",
	"telefone":"34912345678",
	"email":"email@email.com",
	"username":"username",
	"senha":"password"
}
```
Retorno:
```json
{
  "id": 2,
  "nome": "Nome completo",
  "telefone": "34912345678",
  "email": "email@email.com",
  "username": "username",
  "senha": "password",
  "nivelAccess": "USER",
  "createAt": "2022-10-25T17:41:29.1127358",
  "updateAt": "2022-10-25T17:41:29.1127358"
}
```

Rota <i>/usuario</i> metodo <i>`GET`</i>

Objetivo: Consultar todos usuario

Observacao: nesta esta sendo utilizado o sistema de paginacao

exemplo: <i>/usuario?page=0,size=10,sort=id </i>

Retorno:
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Nome Completo Usuario",
      "telefone": "34912345678",
      "email": "email1@email.com",
      "username": "username2",
      "senha": "password",
      "nivelAccess": "USER",
      "createAt": "2022-10-24T20:57:25.546321",
      "updateAt": "2022-10-24T20:57:25.546321"
    },
    {
      "id": 2,
      "nome": "Nome completo",
      "telefone": "34912345678",
      "email": "email@email.com",
      "username": "username",
      "senha": "password",
      "nivelAccess": "USER",
      "createAt": "2022-10-25T17:41:29.112736",
      "updateAt": "2022-10-25T17:41:29.112736"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageSize": 10,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 2,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 2,
  "empty": false
}
```


