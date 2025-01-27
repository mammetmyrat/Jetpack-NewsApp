package dev.mammet.jetpacknewsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mammet.jetpacknewsapp.data.manger.LocalUserMangerImpl
import dev.mammet.jetpacknewsapp.data.remote.NewsApi
import dev.mammet.jetpacknewsapp.data.repository.NewsRepositoryImpl
import dev.mammet.jetpacknewsapp.domain.manger.LocalUserManger
import dev.mammet.jetpacknewsapp.domain.repository.NewsRepository
import dev.mammet.jetpacknewsapp.domain.usecases.app_entry.AppEntryUseCases
import dev.mammet.jetpacknewsapp.domain.usecases.app_entry.ReadAppEntry
import dev.mammet.jetpacknewsapp.domain.usecases.app_entry.SaveAppEntry
import dev.mammet.jetpacknewsapp.domain.usecases.news.GetNews
import dev.mammet.jetpacknewsapp.domain.usecases.news.NewsUseCase
import dev.mammet.jetpacknewsapp.domain.usecases.news.SearchNews
import dev.mammet.jetpacknewsapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ):NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideGetNewsUseCase(
        newsRepository: NewsRepository
    ) = NewsUseCase(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository)
    )
}