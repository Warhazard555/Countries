package com.example.task1.fragments.capitalFragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.dto.CapitalDto
import com.example.task1.R
import com.example.task1.base.BaseAdapter


class CapitalAdapter : BaseAdapter<CapitalDto>() {

    class CapitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       var   capital: AppCompatTextView? = itemView.findViewById(R.id.capital_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.capital_list, parent, false)
        return CapitalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CapitalViewHolder){
            val listItem = dataList[position]
            holder.capital?.text   = listItem.capital
        }
    }
}