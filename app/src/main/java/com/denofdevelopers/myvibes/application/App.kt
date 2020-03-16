package com.denofdevelopers.myvibes.application

import android.app.Application
import com.denofdevelopers.android.projectone_kotlin.di.modules.AppModule
import com.denofdevelopers.myvibes.BuildConfig
import com.denofdevelopers.myvibes.R
import com.devs.acr.AutoErrorReporter
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

class App : Application() {

    private var appComponent: AppComponent? = null

    companion object {
        lateinit var instance: App private set;
    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
        initTimber()
        initCrashLibrary()
        initCalligraphyConfig()
        initAppComponent()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initCrashLibrary() {
        if (BuildConfig.DEBUG) {
            AutoErrorReporter.get(this)
                .setEmailAddresses("buktopmkd@gmail.com")
                .setEmailSubject("MyVibes Crash Report")
                .start()
        } else {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initCalligraphyConfig() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Ubuntu-Regular.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build();
        appComponent!!.plus(this);
    }
}