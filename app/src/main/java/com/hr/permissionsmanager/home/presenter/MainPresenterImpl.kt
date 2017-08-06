package com.hr.permissionsmanager.home.presenter

import com.hr.permissionsmanager.home.views.MainView
import com.hr.permissionsmanager.service.AppsService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference

class MainPresenterImpl(var view: WeakReference<MainView>) : MainPresenter {


    var permissionsInfo: HashMap<String, String> = HashMap()
    var disposables: CompositeDisposable = CompositeDisposable()
    var service: AppsService = AppsService()

    override fun fetchPermissions() {
        disposables.add(service.getPermissionList(getObserver()))
    }

    override fun onPermissionSelected(permission: String) {
        view.get()?.navigateToPermissionList(permission.toLowerCase().capitalize(), permissionsInfo!![permission] as String)
    }

    fun getObserver(): DisposableObserver<HashMap<String, String>> {
        return object : DisposableObserver<HashMap<String, String>>() {
            override fun onNext(t: HashMap<String, String>) {
                permissionsInfo = t
                view.get()?.showData(ArrayList(t.keys))
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