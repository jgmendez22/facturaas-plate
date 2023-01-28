package com.example.facturasapi.service

import com.example.facturasapi.model.Product
import com.example.facturasapi.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository



    fun list ():List<Product>{
        return productRepository.findAll()
    }

    fun save (product: Product):Product{
      try {
        product.stock?.takeIf { it > 0}
          ?: throw Exception("invalid stock")

          product.description?.takeIf { it.trim().isNotEmpty() }
              ?: throw Exception("Description cannot go empty")

          product.brand?.takeIf { it.trim().isNotEmpty() }
              ?: throw Exception("Brand cannot go empty")

          product.price?.takeIf { it > 0}
              ?: throw Exception("Price cannot go empty")

          product.licensePlate?.takeIf{validatePlate(it)}
              ?:throw  Exception("License Plate Error")
          product.licensePlate?.takeIf { it.trim().isNotEmpty()}
              ?:throw Exception("License plate cannot go empty")
          product.licensePlate?.takeIf {!it.contains("-")}
              ?:throw Exception("License Plate Error")
          product.licensePlate?.takeIf {it.matches(Regex("\\d{8}"))}
              ?:throw Exception("License Plate Error")
          product.licensePlate?.takeIf {it.matches(Regex("^[A-Z]{3}-[0-9]{4}$"))}
              ?:throw Exception("License Plate Error")
          product.licensePlate?.takeIf {it.matches(Regex("^(?i)[^P][A-Z]{3}-[0-9]{4}$"))}
              ?:throw Exception("License Plate Error")
          product.licensePlate?.takeIf {it.count{c -> c == '-'}==1}
              ?:throw Exception("License Plate Error")



        return productRepository.save(product)
    }
      catch(ex:Exception){
        throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
      }
    }

    fun update(product: Product):Product{
        try {
            productRepository.findById(product.id)
                ?: throw Exception("The id ${product.id} in product does not exist")
            return productRepository.save(product)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTotal(product: Product):Product{
        try{
            val response = productRepository.findById(product.id)
                ?:throw Exception("The ${product.id} in product does not exist")
            return productRepository.save(product)
            response.apply{
                stock = product.stock
                price = product.price
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }

    fun validatePlate(description: String):Boolean{
        return true
    }


}


