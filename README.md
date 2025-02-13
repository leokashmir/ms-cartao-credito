# ms-cartao-credito

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/ms-cartao-credito-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and
  Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on
  it.
- Flyway ([guide](https://quarkus.io/guides/flyway)): Handle your database schema migrations
- YAML Configuration ([guide](https://quarkus.io/guides/config-yaml)): Use YAML to configure your Quarkus application
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code
  for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - MySQL ([guide](https://quarkus.io/guides/datasource)): Connect to the MySQL database via JDBC

## Roadmap

| Status | Description                            |
|:-------|:---------------------------------------|
|  ✅      | Criaçao da estrutura do Porjeto        |
|  ✅     | Modelagem de Dados                     |
|  ✅       | Criação Serviço de Cadastro do Cliente |
|  ✅       | Criação do Serviço da conta            |
|  ✅      | Criação do Serviço de Cartão           |
|  ✅      | Criação do Webhook                     |
|  ✅      | Teste unitarios                        |
✅ Concluido   🟡 Em andamento  🔴 Não iniciado



# API Reference

## API - Cartao



| Metodo | URL                                 | Retorno HTTP Codigos                                  | Descrição                   |
|:-------|:------------------------------------|:------------------------------------------------------|-----------------------------|
| `POST` | `/bank/cartao `                     | 200 Realizada com Sucesso | Inclusão do Cartão          |
| `GET`  | `/bank/cartao/tracking/{idCartao} ` | 200 Realizada com Sucesso | Busca Cartão Pelo TrakingId |
| `GET`  | `/bank/cartao/{idConta} `           | 200 Realizada com Sucesso | Retorna uma Lista De cartões |



## API - Conta


| Metodo  | URL                        | Retorno HTTP Codigos                                  | Descrição                |
|:--------|:---------------------------|:------------------------------------------------------|--------------------------|
| `PUT`   | `/bank/conta/cancelar `    | 200 Realizada com Sucesso | Cancelar Conta           |
| `GET`   | `/bank/conta/{idCliente} ` | 200 Realizada com Sucesso | Listar Contas do Cliente |
| `DELETE` | `/bank/cartao/{idConta} `  | 200 Realizada com Sucesso | Excluir Conta             |



## API - Cliente


| Metodo   | URL                   | Retorno HTTP Codigos       | Descrição            |
|:---------|:----------------------|:---------------------------|----------------------|
| `POST`   | `/bank/cliente `      | 200 Realizada com Sucesso | Inclusão do Cliente  |
| `GET`    | `/bank/cliente `      | 200 Realizada com Sucesso | Busca de Cliente     |
| `PATCH`  | `/bank/cliente `      | 200 Realizada com Sucesso | Alteração do Cliente |
| `DELETE` | `/bank/cliente/{id} ` | 200 Realizada com Sucesso | Exclusão do Cliente  |


## API - Webhook

 ```
API-KEY : "zdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ik"
```

| Metodo | URL                          | Retorno HTTP Codigos       | Descrição             |
|:-------|:-----------------------------|:---------------------------|-----------------------|
| `POST` | `/bank/webhooks/cvv-change ` | 200 Realizada com Sucesso | Alterar CVV de Cartão |
| `POST` | `/bank/webhooks/delivery `   | 200 Realizada com Sucesso | Validar Cartão        |

### Mais informações 
http://localhost:8080/swagger-ui


## Informações:
- Executar o Dcoker-compose -up para carregar o banco de dados. ( O Docker tem que esta rodando)
- No diretorio raiz, existe um arquivo "Cartoes.postman_collection.json" que possui as collections das apis.





