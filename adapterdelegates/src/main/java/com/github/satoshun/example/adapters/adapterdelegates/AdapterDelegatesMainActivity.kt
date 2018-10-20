package com.github.satoshun.example.adapters.adapterdelegates

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.adapters.adapterdelegates.databinding.MainActBinding

class AdapterDelegatesMainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)
  }
}
