package br.com.dextra.extension

import android.view.View
import android.widget.ImageView
import br.com.dextra.App
import com.bumptech.glide.Glide

fun ImageView.withImage(url: String){
    Glide.with(App.context()!!).load(url).into(this)
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.visible(){
    visibility = View.VISIBLE
}