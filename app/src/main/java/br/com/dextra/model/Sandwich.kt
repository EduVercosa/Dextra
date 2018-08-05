package br.com.dextra.model

import com.google.gson.annotations.SerializedName

data class Sandwich(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("ingredients") val ingredientsId: List<Int>,
        @SerializedName("image") val image: String
)