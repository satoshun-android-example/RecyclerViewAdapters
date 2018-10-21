package com.github.satoshun.example.adapters.litho

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.LinearLayoutInfo
import com.facebook.litho.widget.Recycler
import com.facebook.litho.widget.RecyclerBinder
import com.facebook.litho.widget.Text
import com.github.satoshun.example.adapters.data.UserRepository
import com.github.satoshun.example.adapters.common.BaseActivity
import kotlinx.coroutines.launch

class LithoMainActivity : BaseActivity() {
  private val repository = UserRepository()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val componentContext = ComponentContext(this)

    val recyclerBinder = RecyclerBinder
        .Builder()
        .layoutInfo(LinearLayoutInfo(LinearLayoutManager(this)))
        .build(componentContext)
    val component = Recycler.create(componentContext)
        .binder(recyclerBinder)
        .build()
    val view = LithoView.create(this, component)
    setContentView(view)

    launch {
      val users = repository.getUsers()
      users.forEach {
        recyclerBinder.appendItem(
            Text.create(componentContext)
                .text(it.name)
                .textColor(Color.BLACK)
                .textSizeDip(28f)
                .build()
        )
      }
    }
  }
}
