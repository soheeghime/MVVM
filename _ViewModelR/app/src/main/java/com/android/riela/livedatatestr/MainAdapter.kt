package com.android.riela.livedatatestr

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class MainAdapter (private val context: Context,
                   private val dataSource: ArrayList<String>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init{

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.e("R","--- getView ")
        val rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        val row = getItem(position) as String
        val tv = rowView.findViewById(android.R.id.text1) as TextView
        tv.text = row
        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

}
