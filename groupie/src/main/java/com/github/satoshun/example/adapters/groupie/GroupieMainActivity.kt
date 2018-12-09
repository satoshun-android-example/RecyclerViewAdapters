package com.github.satoshun.example.adapters.groupie

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.adapters.common.BaseActivity
import com.github.satoshun.example.adapters.data.UserRepository
import com.github.satoshun.example.adapters.groupie.databinding.MainActBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GroupieMainActivity : BaseActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val adapter = MainAdapter()
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = adapter

    launch {
      val repository = UserRepository()
      while (true) {
        val users = repository.getUsers()
        adapter.addUsers(users)

        delay(2000)
        adapter.shuffle()
      }
    }
  }
}
