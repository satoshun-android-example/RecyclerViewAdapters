package com.github.satoshun.example.adapters.data

import kotlinx.coroutines.delay

class UserRepository {
  suspend fun getUsers(): List<User> {
    delay(2000)
    return (0..100).map {
      User("user$it")
    }
  }
}
