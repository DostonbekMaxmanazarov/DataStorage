package com.example.roomkotlin_1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomkotlin_1.R
import com.example.roomkotlin_1.convert.ImageConvert
import com.example.roomkotlin_1.entity.Users
import kotlinx.android.synthetic.main.item_design.view.*

class AdapterView(var users: List<Users>) : RecyclerView.Adapter<AdapterView.VH>() {

    class VH(item: View) : RecyclerView.ViewHolder(item) {
        var textUserName = item.text_userName2
        var textFullName = item.text_fullName2
        var imageCamera2 = item.image_camera2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var viewVh =
            LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return VH(viewVh)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.imageCamera2.setImageBitmap(ImageConvert.convertByteImageArray(users[position].image))
        holder.textFullName.text = users[position].mUserName
        holder.textUserName.text = users[position].mFullName
    }
}