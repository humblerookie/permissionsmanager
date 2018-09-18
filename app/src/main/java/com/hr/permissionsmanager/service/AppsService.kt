package com.hr.permissionsmanager.service

import android.content.Context
import android.content.pm.PackageInfo
import android.util.Log
import com.hr.permissionsmanager.common.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class AppsService {

    fun getAppsForPermission(context: Context, permissionGroup: String, observer: DisposableObserver<PackageInfo>): Disposable {

        return getInstalledApplications(context)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .filter { appInfo -> appInfo.requestedPermissionsFlags != null }
                .filter { appInfo ->
                    if (appInfo.packageName == "com.instagram.android") {
                        Log.d("Abc", "Abc")
                    }
                    if (!isSystem(appInfo.applicationInfo)) {
                        appInfo.requestedPermissions.indices
                                .any { it ->
                                    getDangerousGroup(appInfo.requestedPermissions[it], context) == permissionGroup
                                            && isGranted(appInfo.requestedPermissionsFlags[it])
                                }
                    } else {
                        false
                    }

                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)

    }

    fun getPermissionList(observer: DisposableObserver<HashMap<String, String>>): Disposable {

        return Observable.just(1)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap { getPermissionGroups() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)

    }
}