package com.hr.permissionsmanager.home.presenter

interface MainPresenter {

    fun fetchPermissions()

    fun onPermissionSelected(permission:String)

    fun onDestroyed()
}