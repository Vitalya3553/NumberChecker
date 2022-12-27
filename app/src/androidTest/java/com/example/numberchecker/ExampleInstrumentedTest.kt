package com.example.numberchecker

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.numberchecker.data.local.entity.ContactEntity
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setContacts(contacts : List<ContactEntity>)

//    @Delete
//    suspend fun deleteNumber(number : Number)

    @Query("Delete from ContactEntity")
    suspend fun deleteAllNumbers()

    @Query("select * from ContactEntity")
    suspend fun getContactEntities(): List<ContactEntity>*/
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.numberchecker", appContext.packageName)
    }


}