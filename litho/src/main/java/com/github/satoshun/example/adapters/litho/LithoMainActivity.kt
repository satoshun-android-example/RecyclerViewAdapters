package com.github.satoshun.example.adapters.litho

import android.os.Bundle
import com.github.satoshun.example.adapters.data.UserRepository
import com.github.satoshun.example.adapters.epoxy.BaseActivity

class LithoMainActivity : BaseActivity() {
  private val repository = UserRepository()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)
  }
}
