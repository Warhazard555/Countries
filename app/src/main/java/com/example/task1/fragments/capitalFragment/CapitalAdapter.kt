package com.example.task1.fragments.capitalFragment


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.dto.CapitalDto
import com.example.task1.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.capital_list.view.*

class CapitalAdapter : ListAdapter<CapitalDto, CapitalAdapter.ListViewHolder>(DifferItemCallback()){

    class DifferItemCallback : DiffUtil.ItemCallback<CapitalDto>(){
        @SuppressLint("DiffUtilEquals")
        override fun areItemsTheSame(oldItem: CapitalDto, newItem: CapitalDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CapitalDto, newItem: CapitalDto): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        return ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.capital_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: CapitalAdapter.ListViewHolder, position: Int) {
      holder.bind(getItem(position))
    }

    inner class ListViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: CapitalDto) {
            with(containerView) {
                capital_text.text = item.capital
                itemView.setOnClickListener { mOnItemClickListener?.invoke(item) }
            }
        }
    }

    var mOnItemClickListener: ((CapitalDto) -> Unit?)? = null

    fun setItemClick(clickListener: (CapitalDto) -> Unit) {
        mOnItemClickListener = clickListener
    }
}


//class CapitalAdapter : BaseAdapter<CapitalDto>() {
//
//    class CapitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var capital: AppCompatTextView? = itemView.findViewById(R.id.capital_text)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.capital_list, parent, false)
//        return CapitalViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is CapitalViewHolder) {
//            val listItem = dataList[position]
//            holder.capital?.text = listItem.capital
//        }
//    }
//}