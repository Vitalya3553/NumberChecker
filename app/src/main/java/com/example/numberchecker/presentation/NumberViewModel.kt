package com.example.numberchecker.presentation


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numberchecker.data.local.NumbersDatabase
import com.example.numberchecker.domain.model.Contact
import com.example.numberchecker.domain.model.NumberInfo
import com.example.numberchecker.domain.use_cases.CheckNumberUseCase
import com.example.numberchecker.domain.use_cases.GetContactsUseCase
import com.example.numberchecker.domain.use_cases.ReloadContactsUseCase
import com.example.numberchecker.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject


@HiltViewModel
class NumberViewModel @Inject constructor(
   private val useCases: UseCases
) : ViewModel() {

    private var _stateFlow = MutableStateFlow<List<Contact>>(emptyList())
    val stateFlow = _stateFlow.asStateFlow()

    suspend fun reloadContacts(){
        useCases.reloadContactsUseCase()
        _stateFlow.value = useCases.getContactsUseCase()
    }

    suspend fun getSavedContacts(): List<Contact> {
        return useCases.getContactsUseCase()
    }
    suspend fun checkContact(number : String): NumberInfo{
        return useCases.checkNumberUseCase(number)
    }


}