package com.example.okuyamarina.todoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.dialog_todo_input.view.*

class MyAdapter(private var myDataset: ArrayList<String>) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    var onClick : (text : String, position : Int) -> Unit = {_ , _ ->}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as TextView
        var viewholder = ViewHolder(textView)
        textView.setOnClickListener { onClick(textView.text.toString(),viewholder.adapterPosition) }

        // set the view's size, margins, paddings and layout parameters
        // ...
        return viewholder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}