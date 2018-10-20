package com.github.satoshun.example.adapters.adapterdelegates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.adapters.adapterdelegates.databinding.MainActBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class AdapterDelegatesMainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = AnimalAdapter(
        listOf(
            Animal(name = "zoo"),
            Animal(name = "cat")
        )
    )
  }
}

class MainDelegate : AdapterDelegate<List<Animal>>() {
  override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
    return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))
  }

  override fun isForViewType(items: List<Animal>, position: Int): Boolean {
    return true
  }

  override fun onBindViewHolder(
    items: List<Animal>,
    position: Int,
    holder: RecyclerView.ViewHolder,
    payloads: MutableList<Any>
  ) {
    val item = items[position]
    (holder as ViewHolder).title.text = item.name
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.title)
  }
}

data class Animal(
  val name: String
)

class AnimalAdapter(items: List<Animal>) : ListDelegationAdapter<List<Animal>>() {
  init {
    // DelegatesManager is a protected Field in ListDelegationAdapter
    delegatesManager.addDelegate(MainDelegate())

    // Set the items from super class.
    setItems(items)
  }
}
