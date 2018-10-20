package com.github.satoshun.example.adapters.anko

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.adapters.anko.databinding.MainActBinding
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.padding
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class AnkoMainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    with(binding.recycler) {
      layoutManager = LinearLayoutManager(this@AnkoMainActivity)
      adapter = MainAdapter(
          listOf(
              User(name = "tom"),
              User(name = "ken")
          )
      )
    }
  }
}

data class User(
  val name: String
)

class UserUI : AnkoComponent<ViewGroup> {
  override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
    verticalLayout {
      lparams(matchParent, wrapContent)
      padding = dip(16)
      textView {
        id = 10
        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
        textSize = 16f
        textColor = Color.BLACK
      }
    }
  }
}

class MainAdapter(
  private val users: List<User>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MainViewHolder(
        UserUI().createView(AnkoContext.create(parent.context, parent))
    )
  }

  override fun getItemCount(): Int {
    return users.size
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val user = users[position]
    (holder as MainViewHolder).itemView.findViewById<TextView>(10).text = user.name
  }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
