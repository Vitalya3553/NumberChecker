package com.example.numberchecker.domain.repository

import android.content.Context
import com.example.numberchecker.domain.model.Contact
import com.example.numberchecker.domain.model.NumberInfo

interface NumberRepository {

    //suspend fun getNumberInfo(number : String):NumberInfo

    suspend fun getContacts(): List<Contact>

    suspend fun reloadContacts(context: Context)

    suspend fun checkNumber(number: String):NumberInfo

}