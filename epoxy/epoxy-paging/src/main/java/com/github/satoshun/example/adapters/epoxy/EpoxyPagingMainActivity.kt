package com.github.satoshun.example.adapters.epoxy

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.adapters.common.BaseActivity
import com.github.satoshun.example.adapters.data.User2
import com.github.satoshun.example.adapters.data.createDatabase
import kotlinx.android.synthetic.main.main_act.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EpoxyPagingMainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    val pagingController = TestPagingController()
    recycler.adapter = pagingController.adapter
    recycler.layoutManager = LinearLayoutManager(this)
    val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
    recycler.layoutAnimation = animation

    val database = createDatabase(this)
    val users = LivePagedListBuilder(database.author().getAuthors(), 10)
      .build()
    users.observe(this, Observer {
      pagingController.submitList(it)
    })
    launch(Dispatchers.IO) {
      var i = 0
      while (true) {
        delay(1000)
        database.author().insert(User2(name = "test${i++}"))
      }
    }
  }
}
