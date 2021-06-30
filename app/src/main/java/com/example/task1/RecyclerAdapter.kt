package com.example.task1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(private val list: List<CountryItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: AppCompatTextView? = null
        var capital: AppCompatTextView? = null
        var languages: AppCompatTextView? = null

        init {
            name = itemView.findViewById(R.id.name)
            capital = itemView.findViewById(R.id.capital)
            languages = itemView.findViewById(R.id.languages)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(v)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = list[position].name
        holder.capital?.text = list[position].capital
        holder.languages?.text = list[position].languages.convertToList()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}