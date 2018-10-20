package com.github.satoshun.example.adapters.data

import kotlinx.coroutines.delay

class UserRepository {
  suspend fun getUsers(): List<User> {
    delay(2000)
    return listOf(
        User(name = "tom"),
        User(name = "ken")
    )
  }
}
