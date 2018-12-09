package com.github.satoshun.example.adapters.groupie

import com.github.satoshun.example.adapters.data.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class MainAdapter : GroupAdapter<ViewHolder>() {
  private val currentItems = mutableListOf<MainItem>()

  fun addUsers(users: List<User>) {
    val items = users.map { MainItem(it) }
    addAll(items)
    currentItems += items
  }

  fun shuffle() {
    currentItems.shuffle()
    updateAsync(currentItems)
  }
}
