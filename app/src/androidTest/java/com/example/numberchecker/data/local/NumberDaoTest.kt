package com.example.numberchecker.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.numberchecker.data.local.entity.ContactEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NumberDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database : NumbersDatabase
    private lateinit var dao : NumberDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NumbersDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao
    }
    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun setContacts() =  runTest{
        val number = ContactEntity("John", "+38096478395",1)

        dao.setContacts(listOf(number))
        val resultList = dao.getContactEntities()

        assertThat(resultList).contains(number)

    }

    @Test
    fun deleteAllNumbers() = runTest {
        val contact1 = ContactEntity("Mather","+48847995834",)
        val contact2 = ContactEntity("Petro","+380464885443",)
        val contact3 = ContactEntity("Girl","+488",)
        dao.setContacts(listOf())
        dao.deleteAllNumbers()
        val resultList = dao.getContactEntities()
        assertThat(resultList.isEmpty()).isTrue()
    }




}