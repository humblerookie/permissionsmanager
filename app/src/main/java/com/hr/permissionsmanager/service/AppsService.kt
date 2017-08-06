package com.hr.permissionsmanager.service

import android.content.Context
import android.content.pm.PackageInfo
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
                .filter({ appInfo -> appInfo.requestedPermissionsFlags != null })
                .filter({ appInfo ->
                    val hasPermissionGranted: Boolean = appInfo.requestedPermissions.indices
                            .any { it ->
                                !isSystem(appInfo.applicationInfo)
                                        && getDangerousGroup(appInfo.requestedPermissions[it], context).equals(permissionGroup)
                                        && isGranted(appInfo.requestedPermissionsFlags[it])
                            }
                    hasPermissionGranted
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)

    }

    fun getPermissionList(observer: DisposableObserver<HashMap<String, String>>): Disposable {

        return Observable.just(1)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap { getPermissionGroups()!! }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)

    }
}