package com.example.numberchecker.domain.use_cases

data class UseCases(
    val checkNumberUseCase: CheckNumberUseCase,
    val getContactsUseCase: GetContactsUseCase,
    val reloadContactsUseCase: ReloadContactsUseCase,
)