package com.linzongfu.myarchitecture.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.linzongfu.myarchitecture.databinding.RecyclerItemRecordMvvmBinding


/**
 * @author zongfulin
 * Date: 4/25/21
 * Time: 17:21
 * Description:
 */
class MvvmAdapter(var list: List<MvvmRecord>) : RecyclerView.Adapter<MvvmAdapter.ViewHolder>() {

    fun replaceList(list: List<MvvmRecord>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerItemRecordMvvmBinding = RecyclerItemRecordMvvmBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val binding: RecyclerItemRecordMvvmBinding? = DataBindingUtil.getBinding(holder.itemView)
        binding?.record = item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public class ViewHolder(binding: RecyclerItemRecordMvvmBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}