package com.valdizz.stack.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valdizz.stack.R
import kotlinx.android.synthetic.main.recyclerview_stack_item.view.*

class StackRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<StackRecyclerViewAdapter.ViewHolder>() {

    private lateinit var stack: List<String?>

    fun setStack(stack: List<String?>) {
        this.stack = stack;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.recyclerview_stack_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = if(::stack.isInitialized) stack.size else 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(stack.get(position) ?: "")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(text: String) {
            itemView.tvStackItem.text = text
        }
    }
}