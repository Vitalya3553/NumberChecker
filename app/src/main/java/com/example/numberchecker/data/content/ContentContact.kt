package com.example.numberchecker.data.content

import com.example.numberchecker.data.local.entity.ContactEntity
import com.example.numberchecker.domain.model.Contact

data class ContentContact(
    val name : String,
    val number: String
    ){
    fun toContactEntity(): ContactEntity {
        return ContactEntity(name,number)
    }


}
