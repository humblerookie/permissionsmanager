package com.hr.permissionsmanager.common.utils

import android.Manifest
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import io.reactivex.Observable
import java.lang.reflect.Modifier


fun getPermissionGroups(): Observable<HashMap<String, String>> {
    val fields = Manifest.permission_group::class.java.declaredFields
    var groups = HashMap<String, String>()

    fields
            .filter { it -> Modifier.isStatic(it.modifiers) }
            .forEach { it -> groups.put(it.name, it.get(null) as String) }
    return Observable.just(groups)
}


fun isGranted(p: Int): Boolean {
    return p.and(PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0
}

fun isSystem(info: ApplicationInfo): Boolean {
    return info.flags.and(ApplicationInfo.FLAG_SYSTEM) != 0
}

fun getDangerousGroup(permission: String, context: Context): String {

    val permInfo: PermissionInfo
    try {
        permInfo = context.packageManager.getPermissionInfo(permission, 0)

        return if (permInfo.group != null) permInfo.group else ""
    } catch (e: PackageManager.NameNotFoundException) {
    }
    return ""
}