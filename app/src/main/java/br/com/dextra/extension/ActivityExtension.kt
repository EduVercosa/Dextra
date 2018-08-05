package br.com.dextra.extension

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import br.com.dextra.App

inline fun <reified T: Any> Activity.start(){
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun Any.toast(message:String){
    Toast.makeText(App.context(), message, Toast.LENGTH_SHORT).show()
}