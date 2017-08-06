package com.hr.permissionsmanager.home.adapters

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.hr.permissionsmanager.R
import java.util.*


class AppListAdapter(apps: ArrayList<PackageInfo>, manager: PackageManager, clickListener: View.OnClickListener) : RecyclerView.Adapter<AppListAdapter.ViewHolder>() {

    internal var appsList: ArrayList<PackageInfo> = apps
    internal var listener: View.OnClickListener = clickListener
    internal var packageManager: PackageManager = manager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false), listener, packageManager)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(appsList[position])
    }

    override fun getItemCount(): Int {
        return appsList.size
    }

    inner class ViewHolder(itemView: View, listener: View.OnClickListener, packageManager: PackageManager) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.icon)
        @JvmField
        var icon: ImageView? = null

        @BindView(R.id.name)
        @JvmField
        var name: TextView? = null

        var container: View? = null

        var manager = packageManager

        init {
            ButterKnife.bind(this, itemView)
            container = itemView
            itemView.setOnClickListener(listener)
        }

        fun bind(packageInfo: PackageInfo) {
            name!!.text = getAppName(packageInfo.packageName)
            icon!!.setImageDrawable(getAppIcon(packageInfo.packageName))
            container!!.tag = packageInfo

        }

        fun getAppName(packageName: String): String {
            return manager.getApplicationLabel(manager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)) as String
        }

        fun getAppIcon(packageName: String): Drawable {
            val matrix = ColorMatrix()
            matrix.setSaturation(0f)

            val filter = ColorMatrixColorFilter(matrix)
            var drawable = manager.getApplicationIcon(packageName)
            drawable.colorFilter = filter
            return drawable
        }
    }

    fun addApp(packageInfo: PackageInfo) {
        appsList.add(packageInfo)
        notifyItemInserted(appsList.size - 1)
    }

}
