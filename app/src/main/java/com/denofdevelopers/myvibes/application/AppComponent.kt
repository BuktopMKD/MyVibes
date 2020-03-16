package com.denofdevelopers.myvibes.application

import com.denofdevelopers.android.projectone_kotlin.di.modules.ApiModule
import com.denofdevelopers.android.projectone_kotlin.di.modules.AppModule
import com.denofdevelopers.android.projectone_kotlin.di.modules.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        ApiModule::class]
)
interface AppComponent {
    fun plus(app: App);
}