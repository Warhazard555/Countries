package com.example.task1.fragments.countryDetails

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.R
import com.example.task1.base.BaseAdapter
import com.example.task1.data.Language

class LanguagesAdapter : BaseAdapter<Language>(){

    class LanguageViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvLanguage: AppCompatTextView = itemView.findViewById(R.id.tv_languages)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_languages, parent,false)
        Log.d(ContentValues.TAG, "ON CREATE VIEW HOLDER STAGE")
        return LanguageViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LanguageViewHolder){
            val item = dataList[position]
            holder.tvLanguage.text = item.name
        }

    }
}