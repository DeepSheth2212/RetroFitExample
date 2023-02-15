package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var adapter : QuoteAdapter
    var quoteList = mutableListOf<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java) //creating retrofit object according to our interface methods

        adapter = QuoteAdapter(this@MainActivity , quoteList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)


        GlobalScope.launch(Dispatchers.Main) {
            val result = quotesApi.getQuotes(1)
            if(result!=null){
                val list = result.body() //returns object of QuoteList
                if (list != null) {
                    quoteList.addAll(list.results) // updates the quoteList data
                    adapter.notifyDataSetChanged() // to tell the adapter that data is updated and it must be reload
                }
            }
        }

    }
}