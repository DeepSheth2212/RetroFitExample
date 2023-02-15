package com.example.retrofitexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(val context : Context, val quoteList : List<Result>) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>(){

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote  = itemView.findViewById<TextView>(R.id.quote)
        val author = itemView.findViewById<TextView>(R.id.author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.quote.text = quoteList[position].content
        holder.author.text = quoteList[position].author
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }
}