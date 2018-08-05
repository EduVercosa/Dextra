package br.com.dextra.extension

import java.text.NumberFormat
import java.util.*

fun Double.toBRCurrency(): String{
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return format.format(this)
}