package br.com.platformbuilders.ecommerce.gateways.controllers.v1

import br.com.platformbuilders.ecommerce.domain.entities.Product
import br.com.platformbuilders.ecommerce.domain.usecases.ProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.*

/**
 * Cadastro de produtos: baseado no simples e famoso CRUD.
 */
@RestController
@RequestMapping("/v1/products")
class ProductsController(private val productUseCase: ProductUseCase) {

    /**
     * Cria um produto.
     *
     * _{{ecommerce}}/v1/products_
     * @param product dados do produto
     * @return Status **200** - com os dados do [Product]
     */
    @PutMapping
    fun create(@RequestBody product: Product) = productUseCase.create(product)

    /**
     * Procura o produto pelo SKU.
     *
     * _{{ecommerce}}/v1/products/{{product.sku}}_
     * @param sku ID do produto
     * @return Status **200** - com os dados do [Product],
     *                **204** - SKU do produto nao encontrado
     */
    @GetMapping("/{sku}")
    fun read(@PathVariable sku: String) = productUseCase.read(sku).map { ResponseEntity.ok(it) }
                                                                  .orElseGet { noContent().build<Product>() }

    /**
     * Atualiza os dados do produto.
     *
     * _{{ecommerce}}/v1/products/_
     * @param product dados do produto
     * @return Status **200** - com os dados do [Product] atualizados
     */
    @PostMapping
    fun update(@RequestBody product: Product) = productUseCase.update(product)

    /**
     * Deleta o produto. obs.: em um caso real seria uma exclusao logica.
     *
     * _{{ecommerce}}/v1/products/{{product.sku}}_
     * @param sku ID do produto
     */
    @DeleteMapping("/{sku}")
    fun delete(@PathVariable sku: String) = productUseCase.delete(sku)

}