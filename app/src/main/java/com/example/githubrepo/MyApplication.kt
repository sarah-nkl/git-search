package com.example.githubrepo

import android.app.Application
import com.example.githubrepo.services.module.AppModule
import dagger.ObjectGraph

/**
 * Created by sarahneo on 12/8/17.
 */

class MyApplication: Application() {
    private lateinit var objectGraph: ObjectGraph

    override fun onCreate() {
        super.onCreate()
        objectGraph = ObjectGraph.create(AppModule(this))
        objectGraph.inject(this)
    }

    fun inject(obj: Any) {
        objectGraph.inject(obj)
    }
}