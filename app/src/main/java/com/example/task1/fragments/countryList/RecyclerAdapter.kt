package com.example.task1.fragments.countryList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.R
import com.example.task1.base.BaseAdapter
import com.example.task1.convertToList
import com.example.task1.data.CountryItem
import com.example.task1.ext.loadImageSvg


class RecyclerAdapter :
    BaseAdapter<CountryItem>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: AppCompatTextView? = null
        var capital: AppCompatTextView? = null
        var languages: AppCompatTextView? = null
        var area: AppCompatTextView? = null
        var flag: AppCompatImageView? = null

        init {
            name = itemView.findViewById(R.id.name)
            capital = itemView.findViewById(R.id.capital)
            languages = itemView.findViewById(R.id.languages)
            area = itemView.findViewById(R.id.area)
            flag = itemView.findViewById(R.id.iv_flag)
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
            val listItem = dataList[position]
            holder.name?.text = listItem.name
            holder.capital?.text = holder.itemView.context.getString(R.string.capital_tittle) + listItem.capital
            holder.languages?.text = holder.itemView.context.getString(R.string.language_tittle)+ listItem.languages.convertToList()
            holder.area?.text = holder.itemView.context.getString(R.string.area_tittle) + listItem.area.toString()
            holder.flag?.loadImageSvg(listItem.flag)
            holder.itemView.setOnClickListener { mOnItemClickListener?.invoke(listItem) }
        }
    }
    fun sortItem() {
        dataList.sortBy { it.area }
        notifyDataSetChanged()
    }

    fun sortDescendingItem() {
        dataList.sortByDescending { it.area }
        notifyDataSetChanged()
    }
}