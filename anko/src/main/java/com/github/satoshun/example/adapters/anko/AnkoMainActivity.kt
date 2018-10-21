package com.github.satoshun.example.adapters.anko

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.adapters.anko.databinding.MainActBinding
import com.github.satoshun.example.adapters.data.User
import com.github.satoshun.example.adapters.data.UserRepository
import com.github.satoshun.example.adapters.common.BaseActivity
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.padding
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class AnkoMainActivity : BaseActivity() {
  private val repository = UserRepository()

  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val adapter = MainAdapter()
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    with(binding.recycler) {
      layoutManager = LinearLayoutManager(this@AnkoMainActivity)
      this.adapter = adapter
    }

    launch {
      val users = repository.getUsers()
      adapter.users = users
      adapter.notifyDataSetChanged()
    }
  }
}

class UserUI(viewType: Int) : AnkoComponent<ViewGroup> {
  override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
    userItem()
  }

  private fun ViewManager.userItem() = verticalLayout {
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

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  var users: List<User> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MainViewHolder(parent, UserUI(viewType))
  }

  override fun getItemCount(): Int {
    return users.size
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val user = users[position]
    (holder as MainViewHolder).itemView.findViewById<TextView>(10).text = user.name
  }
}

class MainViewHolder(
  parent: ViewGroup,
  component: AnkoComponent<ViewGroup>
) : RecyclerView.ViewHolder(component.createView(AnkoContext.create(parent.context, parent)))
