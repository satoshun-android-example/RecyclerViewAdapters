package com.github.satoshun.example.adapters.graywater

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tumblr.graywater.GraywaterAdapter
import kotlinx.android.synthetic.main.main_act.*
import javax.inject.Provider

class GraywaterMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    recycler.layoutManager = LinearLayoutManager(this)
    recycler.adapter = TextAdapter()
  }
}

class TextAdapter : GraywaterAdapter<ItemModel, RecyclerView.ViewHolder, TextBinder, Class<*>>() {
  override fun getModelType(model: ItemModel): Class<*> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

sealed class ItemModel {
  object Text : ItemModel()
}

class TextBinder : GraywaterAdapter.Binder<ItemModel, RecyclerView.ViewHolder, RecyclerView.ViewHolder> {
  override fun prepare(
    model: ItemModel,
    p1: MutableList<Provider<GraywaterAdapter.Binder<in ItemModel, RecyclerView.ViewHolder, out RecyclerView.ViewHolder>>>?,
    p2: Int
  ) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun bind(
    model: ItemModel,
    p1: RecyclerView.ViewHolder,
    p2: MutableList<Provider<GraywaterAdapter.Binder<in ItemModel, RecyclerView.ViewHolder, out RecyclerView.ViewHolder>>>,
    p3: Int,
    p4: GraywaterAdapter.ActionListener<ItemModel, RecyclerView.ViewHolder, RecyclerView.ViewHolder>?
  ) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getViewType(model: ItemModel): Int {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun unbind(p0: RecyclerView.ViewHolder) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
