package br.com.dextra.model

import com.google.gson.annotations.SerializedName

data class Ingredients(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("price") val price: Double,
        @SerializedName("image") val image: String

)