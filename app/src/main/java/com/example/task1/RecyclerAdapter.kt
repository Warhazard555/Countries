package com.example.task1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.base.BaseAdapter
import com.example.task1.data.CountryItem


class RecyclerAdapter :
    BaseAdapter<CountryItem>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: AppCompatTextView? = null
        var capital: AppCompatTextView? = null
        var languages: AppCompatTextView? = null
        var area: AppCompatTextView? = null

        init {
            name = itemView.findViewById(R.id.name)
            capital = itemView.findViewById(R.id.capital)
            languages = itemView.findViewById(R.id.languages)
            area = itemView.findViewById(R.id.area)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            var listItem = dataList[position]
            holder.name?.text = listItem.name
            holder.capital?.text = "capital: " + listItem.capital
            holder.languages?.text = "languages:" + listItem.languages.convertToList()
            holder.area?.text = "area: " + listItem.area.toString()
            holder.itemView.setOnClickListener { mOnItemClickListener?.invoke(listItem) }
        }
    }
}