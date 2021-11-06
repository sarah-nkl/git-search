package com.appcessible.githubrepo.services.module

import android.app.Application
import android.content.Context
import com.appcessible.githubrepo.BaseSearchActivity
import com.appcessible.githubrepo.MainActivity
import com.appcessible.githubrepo.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
        includes = [BusModule::class],
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