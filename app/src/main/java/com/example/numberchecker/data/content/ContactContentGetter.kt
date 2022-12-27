package com.example.numberchecker.data.content;

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import kotlinx.coroutines.coroutineScope

class ContactContentGetter {
    suspend fun get(context: Context): List<ContentContact> {
        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        val cursor = context.contentResolver.query(uri,null,null,null,sort)
        val list:MutableList<ContentContact> = mutableListOf()
        coroutineScope {
            if (cursor!= null ){
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    list.add(ContentContact(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME).toInt()),cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER).toInt())))
                    cursor.moveToNext()
                }
            }
        }
        return list
    }
}
