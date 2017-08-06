package com.hr.permissionsmanager.home.views

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import com.hr.permissionsmanager.R
import com.hr.permissionsmanager.common.activities.BaseActivity
import com.hr.permissionsmanager.common.widgets.SimpleDividerItemDecoration
import com.hr.permissionsmanager.home.adapters.PermissionGroupAdapter
import com.hr.permissionsmanager.home.presenter.MainPresenter
import com.hr.permissionsmanager.home.presenter.MainPresenterImpl
import java.lang.ref.WeakReference


class MainActivity : BaseActivity(), View.OnClickListener, MainView {


    @BindView(R.id.list) @JvmField var list: RecyclerView? = null
    @BindView(R.id.toolbar) @JvmField var toolbar: Toolbar? = null
    @BindView(R.id.progress) @JvmField var progress: ProgressBar? = null

    var adapter: PermissionGroupAdapter? = null
    var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        toolbar!!.elevation = toolbarElevation
        list!!.addItemDecoration(SimpleDividerItemDecoration(this))
        list!!.layoutManager = LinearLayoutManager(this)
        presenter = MainPresenterImpl(WeakReference(this))
        adapter = PermissionGroupAdapter(ArrayList(), this)
        list!!.adapter = adapter
        presenter!!.fetchPermissions()

    }

    override val layout: Int
        get() = R.layout.activity_list

    override fun onClick(view: View?) {
        var permission = view!!.tag
        presenter?.onPermissionSelected(permission.toString())
    }

    override fun toggleProgress(isVisible: Boolean) {
        progress!!.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun toggleError(isVisible: Boolean) {
    }

    override fun navigateToPermissionList(permission: String, permissionGroup: String) {
        var intent = Intent(this, PermissionListActivity::class.java)
        intent.putExtra("permission_group_title", permission)
        intent.putExtra("permission_group", permissionGroup)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    override fun showData(permissions: List<String>) {
        adapter!!.setData(permissions)
    }
}
