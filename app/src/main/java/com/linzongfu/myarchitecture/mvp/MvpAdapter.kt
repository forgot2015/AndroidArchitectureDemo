package com.linzongfu.myarchitecture.mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.linzongfu.myarchitecture.R

/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 17:21
 * Description:
 */
class MvpAdapter(var list: List<MvpRecord>) : RecyclerView.Adapter<MvpAdapter.ViewHolder>() {

    fun replaceList(list: List<MvpRecord>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvDate.text = item.date
        holder.tvEvent.text = item.event
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var tvEvent: TextView = itemView.findViewById(R.id.tvEvent)
    }
}