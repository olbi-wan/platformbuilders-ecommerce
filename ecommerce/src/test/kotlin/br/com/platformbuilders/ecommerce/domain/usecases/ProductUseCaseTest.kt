package br.com.platformbuilders.ecommerce.domain.usecases

import br.com.platformbuilders.ecommerce.domain.entities.Product
import br.com.platformbuilders.ecommerce.gateways.repositories.ProductRepository
import net.bytebuddy.utility.RandomString.make
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal.TEN

@ExtendWith(MockitoExtension::class)
class ProductUseCaseTest {

    @Mock private lateinit var repository: ProductRepository
    @InjectMocks private lateinit var productUseCase: ProductUseCase

    private val product: Product = Product(make()!!, make()!!, TEN)

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

}