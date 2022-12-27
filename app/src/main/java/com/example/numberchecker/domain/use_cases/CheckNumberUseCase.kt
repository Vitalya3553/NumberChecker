package com.example.numberchecker.domain.use_cases

import com.example.numberchecker.domain.model.NumberInfo
import com.example.numberchecker.domain.repository.NumberRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext

class CheckNumberUseCase(
    private val repository: NumberRepository
) {
    suspend operator fun invoke(number: String): NumberInfo{
        return withContext(Dispatchers.IO ) {
            return@withContext repository.checkNumber(number)
        }
    }
}