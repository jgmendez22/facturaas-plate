package com.example.facturasapi.service

import com.example.facturasapi.controller.DetailController
import com.example.facturasapi.model.Detail
import com.example.facturasapi.repository.DetailRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class DetailServiceTest {

  @InjectMocks
  lateinit var detailService: DetailService

  @Mock
  lateinit var  detailRepository: DetailRepository

  var detailMock = Detail().apply{
    id=1
    quantity=2
    invoiceId=1
    productId=1
  }

  @Test
  fun saveDetailCorrect(){
    Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(detailMock)
    val response = detailService.save(detailMock)
    Assertions.assertEquals(response?.id, detailMock.id)
  }

  @Test
  fun saveDetailWhenQuantityIsBlank(){

    Assertions.assertThrows(Exception::class.java) {
      detailMock.apply { quantity=0}
      Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(detailMock)
      detailService.save(detailMock)
    }

  }
}


