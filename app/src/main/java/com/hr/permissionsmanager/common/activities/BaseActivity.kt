package com.hr.permissionsmanager.common.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import butterknife.BindDimen
import butterknife.ButterKnife
import com.crashlytics.android.Crashlytics
import com.hr.permissionsmanager.R
import io.fabric.sdk.android.Fabric

abstract class BaseActivity : AppCompatActivity() {

    @BindDimen(R.dimen.spacing_small)
    @JvmField
    var toolbarElevation: Float = 0f

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(layout)
        ButterKnife.bind(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}