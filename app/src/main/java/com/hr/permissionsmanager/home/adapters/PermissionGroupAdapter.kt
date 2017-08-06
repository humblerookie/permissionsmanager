package com.hr.permissionsmanager.home.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.hr.permissionsmanager.R

class PermissionGroupAdapter(permissions: List<String>, listener: View.OnClickListener) : RecyclerView.Adapter<PermissionGroupAdapter.ViewHolder>() {

    internal var keys: List<String> = permissions
    internal var listener: View.OnClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_permission_group, parent, false), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(keys[position])
    }

    override fun getItemCount(): Int {
        return keys.size
    }

    fun getIcon(permission: String): Int {
        when (permission) {
            "CONTACTS" -> return R.drawable.ic_contacts
            "STORAGE" -> return R.drawable.ic_storage
            "LOCATION" -> return R.drawable.ic_location
            "SMS" -> return R.drawable.ic_sms
            "MICROPHONE" -> return R.drawable.ic_mic
            "PHONE" -> return R.drawable.ic_phone
            "CALENDAR" -> return R.drawable.ic_calendar
            "CAMERA" -> return R.drawable.ic_camera
            "SENSORS" -> return R.drawable.ic_sensor
        }
        return R.drawable.ic_unknown
    }

    fun getDescription(permission: String): Int {
        when (permission) {
            "CONTACTS" -> return R.string.description_contacts
            "STORAGE" -> return R.string.description_storage
            "LOCATION" -> return R.string.description_location
            "SMS" -> return R.string.description_sms
            "MICROPHONE" -> return R.string.description_mic
            "PHONE" -> return R.string.description_phone
            "CALENDAR" -> return R.string.description_calendar
            "CAMERA" -> return R.string.description_camera
            "SENSORS" -> return R.string.description_sensor
        }
        return R.drawable.ic_unknown
    }

    inner class ViewHolder(itemView: View, listener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.icon)
        @JvmField
        var icon: ImageView? = null

        @BindView(R.id.name)
        @JvmField
        var name: TextView? = null

        @BindView(R.id.description)
        @JvmField
        var description: TextView? = null

        var container: View? = null

        init {
            ButterKnife.bind(this, itemView)
            container = itemView
            itemView.setOnClickListener(listener)
        }

        fun bind(permissionName: String) {
            name!!.text = permissionName.toLowerCase().capitalize()
            description!!.setText(getDescription(permissionName))
            icon!!.setImageResource(getIcon(permissionName))
            container!!.tag = permissionName

        }
    }

    fun setData(permissions: List<String>) {
        this.keys = permissions
        notifyDataSetChanged()
    }
}
