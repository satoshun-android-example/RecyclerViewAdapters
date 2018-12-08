package com.github.satoshun.example.adapters.data

import kotlinx.coroutines.delay

class UserRepository {
  private var index = 0

  suspend fun getUsers(): List<User> {
    delay(2000)
    return (0..10).map {
      User("user${index++}")
    }
  }
}
