package com.github.satoshun.example.adapters.epoxy

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.github.satoshun.example.adapters.data.User2

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
