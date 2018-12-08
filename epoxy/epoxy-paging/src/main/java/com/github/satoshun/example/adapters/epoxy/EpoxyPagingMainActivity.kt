package com.github.satoshun.example.adapters.epoxy

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.github.satoshun.example.adapters.common.BaseActivity
import com.github.satoshun.example.adapters.data.MyDatabase
import com.github.satoshun.example.adapters.data.User2
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

    val database = Room
      .databaseBuilder(this, MyDatabase::class.java, "database")
      .build()
    val users = LivePagedListBuilder(database.author().getAuthors(), 10)
      .build()
    users.observe(this, Observer {
      pagingController.submitList(it)
    })
    launch(Dispatchers.IO) {
      var i = 0
      while (true) {
        delay(3000)
        database.author().insert(User2(name = "${i++}"))
      }
    }
  }
}

class TestPagingController : PagedListEpoxyController<User2>(
  modelBuildingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
  override fun buildItemModel(currentPosition: Int, item: User2?): EpoxyModel<*> {
    return if (item == null) {
      TestViewModel_()
        .id(currentPosition)
    } else {
      TestViewModel_()
        .id(currentPosition)
        .textColor(Color.RED)
        .name(item.name)
    }
  }
}

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TestView(context: Context) : AppCompatTextView(context) {
  @TextProp
  fun name(name: CharSequence) {
    text = name
  }

  @ModelProp
  fun textColor(@ColorInt color: Int) {
    setTextColor(color)
  }
}
