package com.github.satoshun.example.adapters.groupie

import com.github.satoshun.example.adapters.data.User
import com.github.satoshun.example.adapters.groupie.databinding.MainItemBinding
import com.xwray.groupie.databinding.BindableItem

class MainItem(
  private val user: User
) : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(binding: MainItemBinding, position: Int) {
    binding.title.text = user.name
  }
}
