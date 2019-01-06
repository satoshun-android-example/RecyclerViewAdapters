package com.github.satoshun.example.adapters.common

import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.coroutine.autodispose.lifecycle.autoDisposeInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {
  final override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + autoDisposeInterceptor()
}
