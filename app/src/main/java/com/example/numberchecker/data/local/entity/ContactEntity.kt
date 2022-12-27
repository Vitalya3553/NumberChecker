package com.example.numberchecker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.numberchecker.domain.model.Contact

@Entity
data class ContactEntity(
    val name : String,
    val number : String,
    @PrimaryKey val id: Int? = null
) {
    fun toContact(): Contact{
        return Contact(name, number)
    }
}