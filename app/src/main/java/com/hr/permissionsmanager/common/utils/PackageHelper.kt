package com.hr.permissionsmanager.common.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager


fun getInstalledApplications(context: Context): io.reactivex.Observable<PackageInfo> {
    return io.reactivex.Observable.fromIterable(context.packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS))
}

fun getPermissionBasedAppMapping(context: Context): List<PackageInfo> {
    return context.packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS.or(PackageManager.GET_PROVIDERS))
}