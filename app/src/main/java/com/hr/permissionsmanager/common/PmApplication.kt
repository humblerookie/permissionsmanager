package com.hr.permissionsmanager.common

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.hr.permissionsmanager.BuildConfig
import timber.log.Timber


class PmApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: PmApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        MobileAds.initialize(this, BuildConfig.ADMOB_ID)
    }
}