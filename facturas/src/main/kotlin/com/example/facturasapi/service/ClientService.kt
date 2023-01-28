package com.example.facturasapi.service

import com.example.facturasapi.model.Client
import com.example.facturasapi.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository


    fun list():List<Client>{
        return clientRepository.findAll()
    }


    fun save(client:Client):Client {
      try {
        client.fullname?.takeIf { it.trim().isNotEmpty() }
          ?: throw Exception("Fullname cannot go empty")

          client.nui?.takeIf { it.trim().isNotEmpty() }
              ?: throw Exception("Nui cannot go empty")

          client.addrees?.takeIf { it.trim().isNotEmpty() }
              ?: throw Exception("Adrress cannot go empty")




        return clientRepository.save(client)
      }
      catch(ex:Exception){
        throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
      }
    }

    fun update(client:Client):Client {
        try {
            clientRepository.findById(client.id)
                ?: throw Exception("Id not found")

            return clientRepository.save(client)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(client:Client): Client {
        try{
            val response = clientRepository.findById(client.id)
                ?: throw Exception("ID not found")
            response.apply {
                fullname=client.fullname
            }
            return clientRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
