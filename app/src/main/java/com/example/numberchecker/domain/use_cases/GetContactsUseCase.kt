package com.example.numberchecker.domain.use_cases

import com.example.numberchecker.domain.model.Contact
import com.example.numberchecker.domain.repository.NumberRepository
import kotlinx.coroutines.*

class GetContactsUseCase (private val repository: NumberRepository){
    suspend operator fun invoke(): List<Contact> {
        return withContext(Dispatchers.IO){
            return@withContext repository.getContacts()
        }
    }
}