package com.hr.permissionsmanager.home.views

import android.content.pm.PackageInfo

interface PermissionListView {

    fun toggleProgress(isVisible: Boolean)

    fun toggleError(isVisible: Boolean)

    fun addAppToView(app: PackageInfo)
}