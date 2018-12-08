package com.github.satoshun.example.adapters.groupie

import com.github.satoshun.example.adapters.data.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class MainAdapter : GroupAdapter<ViewHolder>() {
  fun addUsers(users: List<User>) {
    addAll(
      users.map {
        MainItem(it)
      }
    )
  }
}
