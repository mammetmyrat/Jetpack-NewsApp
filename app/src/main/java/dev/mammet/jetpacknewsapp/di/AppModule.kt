package dev.mammet.jetpacknewsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mammet.jetpacknewsapp.data.local.NewsDao
import dev.mammet.jetpacknewsapp.data.local.NewsDatabase
import dev.mammet.jetpacknewsapp.data.local.NewsTypeConverter
import dev.mammet.jetpacknewsapp.data.manger.LocalUserMangerImpl
import dev.mammet.jetpacknewsapp.data.remote.NewsApi
import dev.mammet.jetpacknewsapp.data.repository.NewsRepositoryImpl
import dev.mammet.jetpacknewsapp.domain.manger.LocalUserManger
import dev.mammet.jetpacknewsapp.domain.repository.NewsRepository
import dev.mammet.jetpacknewsapp.domain.usecases.app_entry.AppEntryUseCases
import dev.mammet.jetpacknewsapp.domain.usecases.app_entry.ReadAppEntry
import dev.mammet.jetpacknewsapp.domain.usecases.app_entry.SaveAppEntry
import dev.mammet.jetpacknewsapp.domain.usecases.news.DeleteArticle
import dev.mammet.jetpacknewsapp.domain.usecases.news.GetNews
import dev.mammet.jetpacknewsapp.domain.usecases.news.NewsUseCase
import dev.mammet.jetpacknewsapp.domain.usecases.news.SearchNews
import dev.mammet.jetpacknewsapp.domain.usecases.news.SelectArticles
import dev.mammet.jetpacknewsapp.domain.usecases.news.UpsertArticle
import dev.mammet.jetpacknewsapp.utils.Constants.BASE_URL
import dev.mammet.jetpacknewsapp.utils.Constants.NEWS_DATABASE_NAME
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) = NewsUseCase(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository),
        upsertArticle = UpsertArticle(newsDao),
        deleteArticle = DeleteArticle(newsDao),
        selectArticles = SelectArticles(newsDao),
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao = newsDatabase.newsDao
}