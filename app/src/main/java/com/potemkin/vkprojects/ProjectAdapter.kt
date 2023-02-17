package com.potemkin.vkprojects

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*
import com.squareup.picasso.Picasso

class ProjectAdapter(
    val context: Context,
    val item: ArrayList<ProjectModelClass>,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row,
            parent, false
        )

        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = item.get(position)
        holder.name.text = item.name
        Picasso.get().load(item.icon_url).into(holder.imV);
    }


    override fun getItemCount(): Int {
        return item.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name = itemView.name
        val imV = itemView.imV

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != -1) {
                listener.onItemClick(item,position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: ArrayList<ProjectModelClass>, position: Int)
    }
}