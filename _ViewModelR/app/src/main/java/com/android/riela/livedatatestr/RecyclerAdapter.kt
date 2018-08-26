package com.android.riela.livedatatestr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerAdapter (private val context: Context,
                       private val dataSource: ArrayList<String>) : RecyclerView.Adapter<RecyclerAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        var v = MainViewHolder(view)
        return v
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tv = view.text11
        private val suvTv = view.text22

        fun bind(row: String) {
            tv.text = row
            suvTv.text = row + " sub"
        }
    }
}
