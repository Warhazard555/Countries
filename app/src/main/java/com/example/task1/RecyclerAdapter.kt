package com.example.task1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
    private var list = listOf<String>("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

         var itemList: AppCompatTextView
         init {
             itemList = itemView.findViewById(R.id.textView)
         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(v)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemList.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}