package com.example.task1.base


import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<ItemType> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var dataList: MutableList<ItemType> = mutableListOf()


    protected var mOnItemClickListener: ((ItemType) -> Unit?)? = null

    interface OnItemClickListener<ItemType> {
        fun onClick(item: ItemType)
    }

    fun setItemClick(clickListener: (ItemType) -> Unit) {
        mOnItemClickListener = clickListener
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    open fun repopulate(list: MutableList<ItemType>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    open fun addList(list: MutableList<ItemType>) {
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}