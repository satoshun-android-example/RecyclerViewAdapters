package com.github.satoshun.example.adapters.epoxy

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import kotlinx.android.synthetic.main.main_act.*

class EpoxyMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    val controller = TestController()
    recycler.layoutManager = LinearLayoutManager(this)
    recycler.setControllerAndBuildModels(controller)
  }
}

class TestController : EpoxyController() {
  override fun buildModels() {
    add(
        (0..10).map {
          TestViewModel_()
              .id(it)
              .name("test")
        }
    )
  }
}

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TestView(context: Context) : AppCompatTextView(context) {
  @TextProp
  fun name(name: CharSequence) {
    text = name
  }
}
