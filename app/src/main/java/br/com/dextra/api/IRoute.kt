package br.com.dextra.api

import br.com.dextra.model.Ingredients
import br.com.dextra.model.Order
import br.com.dextra.model.Promotion
import br.com.dextra.model.Sandwich
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface IRoute {

    @GET("lanche")
    fun getSandwiches(): Call<List<Sandwich>>

    @GET("ingrediente/de/{lanche}")
    fun getIngredientsOf(@Path("lanche") id: Int): Call<List<Ingredients>>

    @GET("lanche/{lanche}")
    fun getSandwich(@Path("lanche") id: Int): Call<Sandwich>

    @PUT("pedido/{lanche}")
    fun addLanche(@Path("lanche") id: Int): Call<Sandwich>

    @PUT("pedido/{lanche}")
    fun addCustomSandwich(@Path("lanche") id: Int, @Body extras: RequestBody): Call<Sandwich>

    @GET("ingrediente")
    fun getIngredients(): Call<List<Ingredients>>

    @GET("pedido")
    fun getOrder(): Call<List<Order>>

    @GET("promocao")
    fun getPromotions(): Call<List<Promotion>>
}