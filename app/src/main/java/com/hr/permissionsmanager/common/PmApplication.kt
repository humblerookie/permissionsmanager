package com.hr.permissionsmanager.common

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import timber.log.Timber


class PmApplication : Application() {

    companion object {
        @JvmStatic lateinit var instance: PmApplication
    }

    init {
        instance = this
        Timber.plant(Timber.DebugTree())
    }
}