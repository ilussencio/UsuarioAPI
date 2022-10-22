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
	"nome":"Nome do usuario",
	"telefone":"34999999999",
	"email":"email@email.com",
	"usuario":"usuario",
	"senha":"senha"
}
```
Retorno:
```json
{
	"id": 1,
	"nome": "Nome do usuario",
	"telefone": "34999999999",
	"email": "email@email.com",
	"usuario": "usuario",
	"senha": "senha",
	"createAt": "2022-10-19T23:33:20.148684",
	"updateAt": "2022-10-19T23:33:20.148813"
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
      "id": 2,
      "nome": "Nome do usuario",
      "telefone": "34999999999",
      "email": "email@email.com",
      "usuario": "usuario",
      "senha": "senha",
      "createAt": "2022-10-19T23:33:20",
      "updateAt": "2022-10-19T23:33:20"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "numberOfElements": 1,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "first": true,
  "number": 0,
  "size": 10,
  "empty": false
}
```


