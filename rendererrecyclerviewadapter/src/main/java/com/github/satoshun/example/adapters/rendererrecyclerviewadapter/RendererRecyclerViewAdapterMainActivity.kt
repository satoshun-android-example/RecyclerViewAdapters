package com.github.satoshun.example.adapters.rendererrecyclerviewadapter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.adapters.rendererrecyclerviewadapter.databinding.MainActBinding
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder

class RendererRecyclerViewAdapterMainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding
  private lateinit var adapter: RendererRecyclerViewAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)
    adapter = RendererRecyclerViewAdapter()
    adapter.registerRenderer(MainViewBinder())
    binding.recycler.adapter = adapter
    adapter.setItems(
        listOf(
            MainViewModel(name = "tom"),
            MainViewModel(name = "momo")
        )
    )
    adapter.notifyDataSetChanged()
  }
}

@Suppress("functionname")
fun MainViewBinder() = ViewBinder<MainViewModel>(
    R.layout.main_item,
    MainViewModel::class.java,
    ViewBinder.Binder { model, finder, _ ->
      finder.find<TextView>(R.id.title).text = model.name
    }
)

class MainViewModel(
  val name: String
) : ViewModel
