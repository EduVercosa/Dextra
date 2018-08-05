package br.com.dextra.api

import br.com.dextra.model.Ingredients
import br.com.dextra.model.Promotion
import br.com.dextra.model.Sandwich
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Api(private val service: IRoute): IApi {

    override fun getSandwiches(result: ResponseRequest<List<Sandwich>>) {
        service.getSandwiches().enqueue(object : Callback<List<Sandwich>>{

            override fun onFailure(call: Call<List<Sandwich>>?, t: Throwable?) {
                result.fail()
            }

            override fun onResponse(call: Call<List<Sandwich>>, response: Response<List<Sandwich>>) {
                response.body()?.let {
                    result.success(it)
                }?:kotlin.run {
                    result.fail()
                }
            }
        })

    }

    override fun getIngredientsOf(id: Int, result: ResponseRequest<List<Ingredients>>) {
    }

    override fun getSandwich(id: Int, result: ResponseRequest<Sandwich>) {
    }

    override fun addLanche(id: Int, result: ResponseRequest<Sandwich>) {
    }

    override fun addCustomSandwich(id: Int, result: ResponseRequest<Sandwich>){
    }

    override fun getIngredients(result: ResponseRequest<List<Ingredients>>) {
        service.getIngredients().enqueue(object : Callback<List<Ingredients>>{

            override fun onFailure(call: Call<List<Ingredients>>?, t: Throwable?) {
                result.fail()
            }

            override fun onResponse(call: Call<List<Ingredients>>, response: Response<List<Ingredients>>) {
                result.success(response.body()!!)
            }
        })
    }

    override fun getOrder(result: ResponseRequest<List<Ingredients>>) {
    }

    override fun getPromotions(result: ResponseRequest<List<Promotion>>) {
        service.getPromotions().enqueue(object : Callback<List<Promotion>>{

            override fun onFailure(call: Call<List<Promotion>>?, t: Throwable?) {
                result.fail()
            }

            override fun onResponse(call: Call<List<Promotion>>, response: Response<List<Promotion>>) {
                response.body()?.let {
                    result.success(it)
                }?:kotlin.run {
                    result.fail()
                }
            }
        })
    }

}