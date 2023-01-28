package com.example.facturasapi.service

import com.example.facturasapi.controller.ProductController
import com.example.facturasapi.model.Product
import com.example.facturasapi.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ProductServiceTest {

  @InjectMocks
  lateinit var productService: ProductService

  @Mock
  lateinit var productRepository: ProductRepository

  var productMock = Product().apply {
    id = 1
    description = "Aveo Family"
    brand = "Chevrolet"
    stock = 991
    price = 10.12
    licensePlate = "PTU-043"

  }

  @Test
  fun saveProductCorrect() {
    Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
    val response = productService.save(productMock)
    Assertions.assertEquals(response?.id, productMock.id)
  }

  @Test
  fun saveProductWhenStockIsZero() {

    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { stock = 0 }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }

  @Test
  fun saveProductWhenDescriptionIsBlank() {

    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { description = "" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }

  @Test
  fun saveProductWhenBrandIsBlank() {

    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { brand = "" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }

  @Test
  fun saveProductWhenPriceIsBlank() {

    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { price = 0.0 }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }

  @Test
  fun saveProductWhenLicensePlateIsBlank() {

    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { licensePlate = "" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }

  @Test
  fun saveProductWhenLicensePlateIsEight() {
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { licensePlate = "12345678" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }
  }

  @Test
  fun saveProductWhenOneScriptLicensePlate() {
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { licensePlate = "CBA123" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }
  }

  @Test
  fun saveProductWhenTwoScriptLicensePlate() {
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { licensePlate = "CD--123" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }
  }

  @Test
  fun saveProductWhenLicensePlateIsWithA() {
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { licensePlate = "ABD-531" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }


  }

  @Test
  fun saveProductWhenLicensePlateIsWithG() {
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { licensePlate = "GBC-123" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }


  }

  @Test
  fun saveProductWhenLicensePlateIsWithP() {
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { licensePlate = "PTU-043" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }
  }
}


