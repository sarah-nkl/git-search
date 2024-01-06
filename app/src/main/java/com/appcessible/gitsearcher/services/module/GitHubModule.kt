package com.appcessible.gitsearcher.services.module

import com.appcessible.gitsearcher.RepoListDeserializer
import com.appcessible.gitsearcher.models.RepositoryList
import com.appcessible.gitsearcher.services.GitHubService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val GITHUB_SEARCH_URL = "https://api.github.com/"

@Module(library = true, complete = false)
class GitHubModule {
    @Provides
    @Singleton
    fun provideGitHubService(): GitHubService {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(RepositoryList::class.java, RepoListDeserializer())
        val retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_SEARCH_URL)
            .addConverterFactory(GsonConverterFactory.create(builder.create()))
            .build()
        return retrofit.create(GitHubService::class.java)
    }
}