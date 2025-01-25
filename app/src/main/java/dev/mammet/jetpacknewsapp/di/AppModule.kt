package dev.mammet.jetpacknewsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mammet.jetpacknewsapp.data.manger.LocalUserMangerImpl
import dev.mammet.jetpacknewsapp.domain.manger.LocalUserManger
import dev.mammet.jetpacknewsapp.domain.usecases.AppEntryUseCases
import dev.mammet.jetpacknewsapp.domain.usecases.ReadAppEntry
import dev.mammet.jetpacknewsapp.domain.usecases.SaveAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        appEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
}