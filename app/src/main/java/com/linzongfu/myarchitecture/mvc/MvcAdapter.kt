package com.linzongfu.myarchitecture.mvc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.linzongfu.myarchitecture.databinding.RecyclerItemRecordBinding


/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 17:21
 * Description:
 */
class MvcAdapter(var list: List<MvcRecord>) : RecyclerView.Adapter<MvcAdapter.ViewHolder>() {

    fun replaceList(list: List<MvcRecord>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //使用ViewBinding的写法
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerItemRecordBinding = RecyclerItemRecordBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvDate.text = item.date
        holder.tvEvent.text = item.event
        with(holder){
            binding.tvDate
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

//    class ViewHolder(binding: RecyclerItemRecordBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        var tvDate: TextView = binding.tvDate
//        var tvEvent: TextView = binding.tvEvent
//    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RecyclerItemRecordBinding.bind(view)
//        var tvDate: TextView = binding.tvDate
//        var tvEvent: TextView = binding.tvEvent
    }
}