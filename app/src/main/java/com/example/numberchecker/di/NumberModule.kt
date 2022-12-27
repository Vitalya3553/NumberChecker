package com.example.numberchecker.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.numberchecker.data.content.ContactContentGetter
import com.example.numberchecker.data.local.NumbersDatabase
import com.example.numberchecker.data.remote.NumberApi
import com.example.numberchecker.data.repository.RepositoryImpl
import com.example.numberchecker.domain.repository.NumberRepository
import com.example.numberchecker.domain.use_cases.CheckNumberUseCase
import com.example.numberchecker.domain.use_cases.GetContactsUseCase
import com.example.numberchecker.domain.use_cases.ReloadContactsUseCase
import com.example.numberchecker.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NumberModule {

    @Provides
    @Singleton
    fun provideRepository(
        db : NumbersDatabase,
        api: NumberApi,
        content : ContactContentGetter
    ): NumberRepository {
        return RepositoryImpl(api,db.dao,content)
    }

    @Provides
    @Singleton
    fun provideGetContactsUseCases(repository: NumberRepository, @ApplicationContext context : Context): UseCases {
        return UseCases(
            CheckNumberUseCase(repository),
            GetContactsUseCase(repository),
            ReloadContactsUseCase(repository,context)
        )
    }

    @Provides
    @Singleton
    fun provideNumbersDatabase(app: Application): NumbersDatabase{
        return Room.databaseBuilder(app,NumbersDatabase::class.java,"numbers_db").build()
    }

    @Provides
    @Singleton
    fun provideNumbersApi():NumberApi{
        return Retrofit.Builder()
            .baseUrl(NumberApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NumberApi::class.java)

    }
    @Provides
    @Singleton
    fun provideContactContentGetter(): ContactContentGetter{
        return ContactContentGetter()
    }
}