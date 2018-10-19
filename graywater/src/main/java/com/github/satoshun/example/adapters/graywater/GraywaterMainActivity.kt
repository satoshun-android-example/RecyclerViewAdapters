package com.github.satoshun.example.adapters.graywater

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    val adapter = TextAdapter()
    recycler.adapter = adapter
    adapter.add(ItemModel.Text)
  }
}

class TextAdapter : GraywaterAdapter<ItemModel, RecyclerView.ViewHolder, TextBinder, Class<*>>() {
  init {
    register(TextViewHolderCreator(), TextViewHolder::class.java)
    val textBinder = TextBinder()
    register(ItemModel.Text::class.java, TextItemBinder(textBinder), null)
  }

  override fun getModelType(model: ItemModel): Class<*> {
    return model.javaClass
  }
}

sealed class ItemModel {
  object Text : ItemModel()
}

class TextBinder : GraywaterAdapter.Binder<ItemModel, RecyclerView.ViewHolder, TextViewHolder> {
  override fun prepare(
    model: ItemModel,
    data: MutableList<Provider<GraywaterAdapter.Binder<in ItemModel, RecyclerView.ViewHolder, out RecyclerView.ViewHolder>>>,
    position: Int
  ) {
  }

  override fun bind(
    model: ItemModel,
    holder: TextViewHolder,
    p2: MutableList<Provider<GraywaterAdapter.Binder<in ItemModel, RecyclerView.ViewHolder, out RecyclerView.ViewHolder>>>,
    p3: Int,
    p4: GraywaterAdapter.ActionListener<ItemModel, RecyclerView.ViewHolder, TextViewHolder>?
  ) {
    if (model == ItemModel.Text) {
      holder.textView.text = "TEXT"
    }
  }

  override fun unbind(p0: TextViewHolder) {
  }

  override fun getViewType(model: ItemModel): Int {
    return R.layout.main_item
  }
}

class TextItemBinder(
  private val textBinder: TextBinder
) : GraywaterAdapter.ItemBinder<ItemModel, RecyclerView.ViewHolder, TextBinder> {
  override fun getBinderList(model: ItemModel, position: Int): MutableList<Provider<out TextBinder>> {
    return mutableListOf(
        Provider { textBinder },
        Provider { textBinder }
    )
  }
}

class TextViewHolderCreator : GraywaterAdapter.ViewHolderCreator {
  override fun create(parent: ViewGroup): TextViewHolder {
    return TextViewHolder(GraywaterAdapter.inflate(parent, R.layout.main_item))
  }

  override fun getViewType(): Int {
    return R.layout.main_item
  }
}

class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val textView: TextView = view.findViewById(R.id.text)
}
