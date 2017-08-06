package com.hr.permissionsmanager.home.presenter

import android.content.Context
import android.content.pm.PackageInfo
import com.hr.permissionsmanager.home.views.PermissionListView
import com.hr.permissionsmanager.service.AppsService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference

class PermissionListPresenterImpl(var view: WeakReference<PermissionListView>, var context: Context, var permissionGroup: String) : PermissionListPresenter {


    var service: AppsService = AppsService()
    var disposables: CompositeDisposable = CompositeDisposable()

    override fun fetchApps() {
        disposables.add(service.getAppsForPermission(context, permissionGroup, getObserver()))
    }

    fun getObserver(): DisposableObserver<PackageInfo> {
        return object : DisposableObserver<PackageInfo>() {
            override fun onNext(t: PackageInfo) {
                view.get()?.addAppToView(t)
            }

            override fun onError(e: Throwable) {
                view.get()?.toggleError(true)
            }

            override fun onComplete() {

            }

        }
    }

    override fun onDestroyed() {
        disposables.clear()
    }
}