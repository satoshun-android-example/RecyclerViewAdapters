package com.github.satoshun.example.adapters.multiviewadapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahamed.multiviewadapter.ItemDataBinder
import com.ahamed.multiviewadapter.SimpleRecyclerAdapter
import com.github.satoshun.example.adapters.multiviewadapter.databinding.MainActBinding
import com.github.satoshun.example.adapters.multiviewadapter.databinding.MainItemBinding

class MultiViewAdapterMainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)

    val adapter = SimpleRecyclerAdapter<Quote, QuoteBinder>(QuoteBinder())
    adapter.setData(
        listOf(
            Quote(author = "tom", quote = "100"),
            Quote(author = "momo", quote = "200")
        )
    )
    binding.recycler.adapter = adapter
  }
}

class QuoteBinder : ItemDataBinder<Quote, MainItemBinding>() {
  override fun canBindData(item: Any?): Boolean {
    return item is Quote
  }

  override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): MainItemBinding {
    return MainItemBinding.inflate(inflater, parent, false)
  }

  override fun bindModel(item: Quote, binding: MainItemBinding) {
    binding.title.text = item.author
  }
}

class Quote(val author: String, val quote: String)
