package com.appcessible.gitsearcher.services.module

import android.app.Application
import android.content.Context
import com.appcessible.gitsearcher.BaseSearchActivity
import com.appcessible.gitsearcher.MainActivity
import com.appcessible.gitsearcher.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
        includes = [BusModule::class, GitHubModule::class],
        injects = [
            MyApplication::class,
            BaseSearchActivity::class,
            MainActivity::class
        ],
        library = true
)
class AppModule(private val app: MyApplication) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return app
    }
}