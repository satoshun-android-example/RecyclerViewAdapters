package com.github.satoshun.example.adapters.epoxy

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.github.satoshun.example.adapters.data.UserRepository
import kotlinx.android.synthetic.main.main_act.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

class EpoxyMainActivity : AppCompatActivity(),
    CoroutineScope {
  private val job = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  private val repository = UserRepository()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    val controller = TestController()
    recycler.layoutManager = LinearLayoutManager(this)
    recycler.setControllerAndBuildModels(controller)

    thread {
      while (true) {
        Thread.sleep(2000)
        recycler.post {
          controller.requestModelBuild()
        }
      }
    }
  }
}

class TestController : EpoxyController() {
  override fun buildModels() {
    add(
        (0..10).map {
          TestViewModel_()
              .id(it)
              .textColor(Color.BLACK)
              .name("test$it")
        }
    )
    add(
        TestViewModel_()
            .id(12)
            .textColor(Color.BLACK)
            .name("test12")
    )

    add(testEpoxyModelGroup())
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
