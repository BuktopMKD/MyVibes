package com.denofdevelopers.android.projectone_kotlin.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application;
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application.applicationContext;
    }
}