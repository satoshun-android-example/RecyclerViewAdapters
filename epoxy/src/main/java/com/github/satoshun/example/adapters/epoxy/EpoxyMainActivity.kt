package com.github.satoshun.example.adapters.epoxy

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.github.satoshun.example.adapters.common.BaseActivity
import com.github.satoshun.example.adapters.data.MyDatabase
import com.github.satoshun.example.adapters.data.User
import com.github.satoshun.example.adapters.data.User2
import com.github.satoshun.example.adapters.data.UserRepository
import kotlinx.android.synthetic.main.main_act.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EpoxyMainActivity : BaseActivity() {
  private val repository = UserRepository()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    val controller = TestController()
    recycler.layoutManager = LinearLayoutManager(this)
    recycler.setControllerAndBuildModels(controller)

    launch {
      val users = repository.getUsers()
      controller.models = users
      controller.requestModelBuild()
    }

    val pagingController = TestPagingController()
    with(recycler2) {
      this.adapter = pagingController.adapter
      this.layoutManager = LinearLayoutManager(this@EpoxyMainActivity)
    }

    val database = Room
        .databaseBuilder(this, MyDatabase::class.java, "database")
        .build()
    val user2 = LivePagedListBuilder(database.author().getAuthors(), 10)
        .build()
    user2.observe(this, Observer {
      pagingController.submitList(it)
    })
    var i = 0
    launch(Dispatchers.IO) {
      while (true) {
        delay(3000)
        database.author().insert(User2(name = "${i++}"))
      }
    }
  }
}

class TestController : EpoxyController() {
  var models: List<User> = emptyList()

  override fun buildModels() {
    add(
        models.map {
          TestViewModel_()
              .id(it.hashCode())
              .textColor(Color.BLACK)
              .name("test${it.name}")
        }
    )
    add(testEpoxyModelGroup())
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

fun testEpoxyModelGroup(): EpoxyModelGroup {
  val models = (100..110)
      .shuffled()
      .map {
        TestViewModel_()
            .id(it)
            .textColor(Color.RED)
            .name("test$it")
      }
  return SectionEpoxyModelGroup(R.layout.item_test, models)
}

class SectionEpoxyModelGroup(
  @LayoutRes layoutRes: Int,
  models: Collection<EpoxyModel<*>>
) : EpoxyModelGroup(layoutRes, models)
