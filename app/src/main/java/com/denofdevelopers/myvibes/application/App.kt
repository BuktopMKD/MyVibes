package com.denofdevelopers.myvibes.application

import android.app.Application
import com.denofdevelopers.myvibes.BuildConfig
import com.denofdevelopers.myvibes.R
import com.devs.acr.AutoErrorReporter
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initCrashLibrary()
        initCalligraphyConfig()
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
}