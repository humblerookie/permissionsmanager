package com.hr.permissionsmanager.home.views

interface MainView {

    fun toggleProgress(isVisible: Boolean)

    fun toggleError(isVisible: Boolean)

    fun navigateToPermissionList(permission: String, permissionGroup: String)

    fun showData(permissions: List<String>)
}