package com.example.numberchecker.data.local
import androidx.room.*
import com.example.numberchecker.data.local.entity.ContactEntity
import com.example.numberchecker.domain.model.Contact

@Dao
interface NumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setContacts(contacts : List<ContactEntity>)

//    @Delete
//    suspend fun deleteNumber(number : Number)

    @Query("Delete from ContactEntity")
    suspend fun deleteAllNumbers()

    @Query("select * from ContactEntity")
    suspend fun getContactEntities(): List<ContactEntity>
}