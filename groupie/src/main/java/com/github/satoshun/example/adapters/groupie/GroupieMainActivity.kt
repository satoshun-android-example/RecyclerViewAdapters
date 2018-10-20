package com.github.satoshun.example.adapters.groupie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.adapters.groupie.databinding.MainActBinding
import com.github.satoshun.example.adapters.groupie.databinding.MainItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem

class GroupieMainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = MainAdapter()
  }
}

class MainAdapter : GroupAdapter<ViewHolder>() {
  init {
    add(MainItem(User(name = "tom")))
    add(MainItem(User(name = "momo")))
  }
}

class MainItem(
  private val user: User
) : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(binding: MainItemBinding, position: Int) {
    binding.title.text = user.name
  }
}

data class User(
  val name: String
)
