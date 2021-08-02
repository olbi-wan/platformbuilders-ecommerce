package br.com.platformbuilders.ecommerce.domain.usecases

import br.com.platformbuilders.ecommerce.domain.entities.Product
import br.com.platformbuilders.ecommerce.gateways.repositories.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductUseCase(private val repository: ProductRepository) {

    /**
     * Cria um [product]. obs.: o SKU eh dinamico.
     */
    fun create(product: Product) = repository.save(product)

    fun read(sku: String) = repository.findById(sku)

    /**
     * Atualiza os dados do [product].
     * @throws IllegalStateException se o [product] nao existir
     */
    fun update(product: Product) = with(repository.existsById(product.sku!!)) {
        // Verifica se o SKU do produto existe.
        if (!this) throw IllegalStateException("Product not exists in database.")
        repository.save(product)
    }

    fun delete(sku: String) = repository.deleteById(sku)

}