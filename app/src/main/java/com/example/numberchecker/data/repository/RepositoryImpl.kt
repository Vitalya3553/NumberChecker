package com.example.numberchecker.data.repository

import android.content.Context
import android.util.Log
import com.example.numberchecker.data.content.ContactContentGetter
import com.example.numberchecker.data.content.ContentContact
import com.example.numberchecker.data.local.NumberDao
import com.example.numberchecker.data.remote.NumberApi
import com.example.numberchecker.data.remote.dto.NumberInfoDto
import com.example.numberchecker.domain.model.Contact
import com.example.numberchecker.domain.model.NumberInfo
import com.example.numberchecker.domain.repository.NumberRepository
import kotlinx.coroutines.*

class RepositoryImpl(
    private val api : NumberApi,
    private val dao : NumberDao,
    private val content : ContactContentGetter
    ): NumberRepository {

    override suspend fun getContacts(): List<Contact> {
        Log.e("tag before content","Its need ned be before content " )

        return dao.getContactEntities().map { it.toContact() }
    }



     override suspend fun reloadContacts(context: Context) {

        withContext(Dispatchers.IO){
            dao.deleteAllNumbers()
            Log.e("Reload.. ","Was delete all numbers")
            dao.setContacts(
                content.get(context).map { it.toContactEntity() }
            )
            Log.e("Tag ","reload is finished")
        }



    }

    override suspend fun checkNumber(number: String):NumberInfo {
        return api.checkNumberInfo(number = number)
            .toNumberInfo()

    }

}