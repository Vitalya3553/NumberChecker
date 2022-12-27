package com.example.numberchecker.domain.use_cases

import android.content.Context
import com.example.numberchecker.domain.model.Contact
import com.example.numberchecker.domain.repository.NumberRepository
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class ReloadContactsUseCase(
    private val repository: NumberRepository,
    private val context: Context) {
    suspend operator fun invoke(){
        try {
            withContext(Dispatchers.IO) {
                repository.reloadContacts(context)
            }
        }catch (e: CancellationException){
            throw e
        }
        catch (e: Exception){

        }

    }
}