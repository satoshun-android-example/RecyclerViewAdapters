object Vers {
  const val compile_sdk = 28
  const val min_sdk = 25
  const val target_sdk = 28

  const val kotlin = "1.3.11"
  const val couroutine = "1.1.0"
}

object Libs {
  const val android_plugin = "com.android.tools.build:gradle:3.4.0-alpha10"
  const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"

  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"
  const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Vers.couroutine}"
  const val ui_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.couroutine}"

  const val ktx = "androidx.core:core-ktx:1.0.0"

  const val appcompat = "androidx.appcompat:appcompat:1.0.0"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
  const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha3"
  const val cardview = "androidx.cardview:cardview:1.0.0"

  private const val vepoxy = "3.1.0"
  const val epoxy = "com.airbnb.android:epoxy:$vepoxy"
  const val epoxy_processor = "com.airbnb.android:epoxy-processor:$vepoxy"
  const val epoxy_paging = "com.airbnb.android:epoxy-paging:$vepoxy"

  const val multiviewadapter = "com.github.devahamed:multi-view-adapter:1.3.0"
  const val multiviewadapter_databinding = "com.github.devahamed:multi-view-adapter-databinding:1.3.0"

  const val groupie = "com.xwray:groupie:2.3.0"
  const val groupie_databinding = "com.xwray:groupie-databinding:2.3.0"

  const val rendererrecyclerviewadapter = "com.github.vivchar:RendererRecyclerViewAdapter:2.6.0"

  const val adapterdelegates = "com.hannesdorfmann:adapterdelegates4:4.0.0"

  const val anko = "org.jetbrains.anko:anko:0.10.7"
  const val anko_recyclerview = "org.jetbrains.anko:anko-recyclerview-v7:0.10.7"
  const val anko_coroutine = "org.jetbrains.anko:anko-recyclerview-v7-coroutines:0.10.7"

  const val litho = "com.facebook.litho:litho-core:0.20.0"
  const val litho_widget = "com.facebook.litho:litho-widget:0.20.0"
  const val litho_annotations = "com.facebook.litho:litho-annotations:0.20.0"
  const val litho_processor = "com.facebook.litho:litho-processor:0.20.0"
  const val soloader = "com.facebook.soloader:soloader:0.5.1"

  const val material = "com.google.android.material:material:1.0.0"
  const val paging = "androidx.paging:paging-runtime:2.0.0"

  const val room_common = "androidx.room:room-common:2.1.0-alpha02"
  const val room_runtime = "androidx.room:room-runtime:2.1.0-alpha02"
  const val room_compiler = "androidx.room:room-compiler:2.1.0-alpha02"

  const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0"
  const val livedata = "androidx.lifecycle:lifecycle-livedata:2.0.0"
  const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:2.0.0"

  const val emoji = "androidx.emoji:emoji:1.0.0"
  const val emoji_compat = "androidx.emoji:emoji-appcompat:1.0.0"
  const val emoji_bundled = "androidx.emoji:emoji-bundled:1.0.0"

  const val javax = "javax.inject:javax.inject:1"

  const val multidex = "androidx.multidex:multidex:2.0.0"

  const val rxwebview = "com.github.satoshun.RxWebView:rxwebview-kotlin:2.3.0"

  const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.0"
  const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.0"

  const val coroutine_auto_dispose = "com.github.satoshun.coroutine.autodispose:autodispose:0.1.1"

  const val android_annotation = "androidx.annotation:annotation:1.0.0"

  const val junit = "junit:junit:4.12"
  const val truth = "com.google.truth:truth:0.39"
  const val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  const val test_runner = "androidx.test:runner:1.1.0-beta01"
  const val test_rule = "androidx.test:rules:1.1.0-beta01"
  const val espresso = "androidx.test.espresso:espresso-core:3.1.0-beta01"
}
