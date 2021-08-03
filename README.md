![Platform Builders](https://img.shields.io/badge/Platform-Builders-yellow)
![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)

<p align="left">
  <img src="https://platformbuilders.io/assets/images/logo.png" width="340" alt="Platform Builders">
</p>

### _Arquitetura e-commerce_

<p align="center">
  <i>
    <a href="#introdução">Introdução</a> •
    <a href="#instalação">Instalação</a> •
    <a href="#ambiente">Ambiente</a> •
    <a href="#projeto">Projeto</a> •
    <a href="#artefatos">Artefatos</a> •
    <a href="#imagens">Imagens</a>
  <i/>
</p>

## Introdução

Olá pessoal tudo bem? Para esse desafio eu criei um pequeno projeto em Kotlin para adicionar mais um evidência para que possam me avaliar melhor.
O projeto não tem muito haver com os artefatos da arquitetura, mas mostra um pouco como eu programo (eu amo programar).

Como atores principais eu criei um protótipo o qual identifico no texto como wireframe e os desenhos no Draw.io com a arquitetura do e-commerce. 
  
Espero que gostem, muito obrigado pela oportunidade.

## Instalação
  
* [x] instale o [Intellij](https://www.jetbrains.com/pt-br/idea/download).
* [x] instale o [Postman](https://www.postman.com/downloads).
* [x] instale o [Docker](https://docs.docker.com/docker-for-windows/install).
* [x] baixe o projeto do GitHub com `git clone`.
* [x] execute a imagem do Redis e seu dashboard (o dashboard vai rodar no endereço `http://localhost:8001`) `docker-compose -f docker/redis-compose.yml up`.
  
## _Ambiente_

* *[Docker](https://www.docker.com)* - `redis-compose.yml` - criação de contêiners.
* *[Draw.io](https://app.diagrams.net)* - `arquitetura.drawio` - editor gráfico online.
* *[Pencil](https://pencil.evolus.vn)* - `pencil-wireframe.epgz` - ferramenta de **wireframes**.
* *[Postman](https://www.postman.com)* - `ecommerce.postman_collection.json`, `ecommerce-local.postman_environment.json` - ferramenta para serviços REST.
* *[RedisInsight](https://redislabs.cinsightom/redis-enterprise/redis-/)* - dashboard para o Redis.

## _Projeto_

O projeto se encontra na pasta [ecommerce](https://github.com/olbi-wan/platformbuilders-ecommerce/tree/main/ecommerce). O mesmo foi desenvolvido com carinho utilizando: Maven + Kotlin + Spring Boot.

**Testes Unitários**
  
```Java
@Test
fun `Deve poder criar um produto novo`() {
    `when`(repository.save(eq(product))).thenReturn(product)
    assertNotNull(productUseCase.create(product))
}

@Test
fun `Deve lancar IllegalStateException na atualizacao do produto quando o mesmo nao existir`() {
    val exception = assertThrows<IllegalStateException> { productUseCase.update(product) }
    assertEquals("Product not exists in database.", exception.message)
    verify(repository, times(1)).existsById(eq(product.sku!!))
}
```
  
**Postman**
  
* `ecommerce.postman_collection.json` - *chamadas dos serviços e testes integrados.*  
* `ecommerce-local.postman_environment.json` - *váriaveis de ambiente.*
  
_Exemplo dos testes integrados:_  
 
**_{{ecommerce}}/v1/products_**
```javascript
pm.test("Status = 200", () => pm.response.to.have.status(200))
pm.test("Time < 900 ms", () => pm.expect(pm.response.responseTime).to.be.below(900))
pm.test("Content-Type = application/json", () => pm.response.to.be.header("Content-Type", "application/json"))
pm.test("Body is valid", () => pm.expect(pm.response.text()).to.include(`"name":"${pm.variables.get("product.name")}",
    "price":${pm.variables.get("product.price")}`))

// Disponibiliza o ID do produto para os próximos testes. 
pm.collectionVariables.set("product.sku", JSON.parse(responseBody).sku)
```

## _Artefatos_

* *[Wireframes - protótipo do e-commerce (clique para ver).](https://github.com/olbi-wan/platformbuilders-ecommerce/blob/main/wireframes/)*
  * `pencil-wireframe.epgz`
* *[Desenho da arquitetura - esboço da arquitetura de um e-commerce(clique para ver).](https://github.com/olbi-wan/platformbuilders-ecommerce/tree/main/arquitetura)*
  * `arquitetura.drawio`
  
## _Imagens_
<details><summary>arquitetura.drawio</summary>
<p>
<br>
<img src="https://github.com/olbi-wan/platformbuilders-ecommerce/blob/main/arquitetura/arquitetura.png">
</p>
</details>  
<details><summary>pencil-wireframe.epgz</summary>
<p>
<br>
<img src="https://github.com/olbi-wan/platformbuilders-ecommerce/blob/main/wireframes/home.png">
</p>
</details>
<details><summary>Postman</summary>
<p>
<br>
<img src="https://github.com/olbi-wan/platformbuilders-ecommerce/blob/main/postman/documentation/postman-alltests.png"><br>
<img src="https://github.com/olbi-wan/platformbuilders-ecommerce/blob/main/postman/documentation/postman-runner.png">
</p>
</details>
<details><summary>RedisInsight</summary>
<p>
<br>
<img src="https://github.com/olbi-wan/platformbuilders-ecommerce/blob/main/docker/documentation/docker-redisinsight.png"><br>
</p>
</details>