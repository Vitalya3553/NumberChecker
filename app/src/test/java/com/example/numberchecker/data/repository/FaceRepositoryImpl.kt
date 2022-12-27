package com.example.numberchecker.data.repository

import android.content.Context
import com.example.numberchecker.domain.model.Contact
import com.example.numberchecker.domain.model.NumberInfo
import com.example.numberchecker.domain.repository.NumberRepository

class FaceRepositoryImpl : NumberRepository{

    var localContacts: MutableList<Contact> = mutableListOf()

    private var contacts: List<Contact> = emptyList()
    private var shouldReturnNetworkError = false
    private var shouldReturnValidNumberInfo = true

    fun setShouldReturnNetworkError(value : Boolean){
        shouldReturnNetworkError = value
    }
    fun setShouldReturnValidNumberInfo(value: Boolean){
        shouldReturnValidNumberInfo = value
    }

    override suspend fun checkNumber(number: String): NumberInfo {
        //val valid  = if(shouldReturnValidNumberInfo) "Valid" else ""
        return NumberInfo("","","","","","","","","", shouldReturnValidNumberInfo)
    }

    override suspend fun getContacts(): List<Contact> {
        return contacts
    }

    override suspend fun reloadContacts(context: Context) {
        contacts = localContacts
    }
}