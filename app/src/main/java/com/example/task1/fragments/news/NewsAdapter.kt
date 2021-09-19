package com.example.task1.fragments.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.dto.NewsDto
import com.example.task1.R
import com.example.task1.ext.loadImageSvg
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_news.view.*

class NewsAdapter  : ListAdapter<NewsDto, NewsAdapter.ListViewHolder>(DifferItemCallback()){

   class DifferItemCallback : DiffUtil.ItemCallback<NewsDto>(){
       @SuppressLint("DiffUtilEquals")
       override fun areItemsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean {
           return oldItem == newItem
       }

       override fun areContentsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean {
          return oldItem == newItem
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
return ListViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.fragment_news,parent,false)
)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListViewHolder(override val containerView:View):RecyclerView.ViewHolder(containerView),LayoutContainer{
        fun bind(item:NewsDto){
            with(containerView){
                tittle_news.text = item.tittle
                news_description.text = item.description

            }
        }
    }

}