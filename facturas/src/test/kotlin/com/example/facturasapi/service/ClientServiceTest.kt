package com.example.facturasapi.service

import com.example.facturasapi.model.Client
import com.example.facturasapi.repository.ClientRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ClientServiceTest {

  @InjectMocks
  lateinit var clientService: ClientService

  @Mock
  lateinit var  clientRepository: ClientRepository

  var clientMock = Client().apply{
    id=1
    nui="0106605207"
    fullname="Jonnathan Mendez"
    addrees="Cuenca"
  }

  @Test
  fun saveClientCorrect(){
    Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
    val response = clientService.save(clientMock)
    Assertions.assertEquals(response?.id, clientMock.id)
  }

  @Test
  fun saveClientWhenFullNameIsBlank(){

    Assertions.assertThrows(Exception::class.java) {
      clientMock.apply { fullname=" "}
      Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
      clientService.save(clientMock)
    }

  }

  @Test
  fun saveClientWhenFullNuiIsBlank(){

    Assertions.assertThrows(Exception::class.java) {
      clientMock.apply { nui=" "}
      Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
      clientService.save(clientMock)
    }

  }

  @Test
  fun saveClientWhenAddressIsBlank(){

    Assertions.assertThrows(Exception::class.java) {
      clientMock.apply { addrees=" "}
      Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
      clientService.save(clientMock)
    }

  }
}


