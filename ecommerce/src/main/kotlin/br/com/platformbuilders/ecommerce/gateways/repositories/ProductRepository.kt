package br.com.platformbuilders.ecommerce.gateways.repositories

import br.com.platformbuilders.ecommerce.domain.entities.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, String>