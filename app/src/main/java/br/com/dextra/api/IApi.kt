package br.com.dextra.api

import br.com.dextra.model.Ingredients
import br.com.dextra.model.Promotion
import br.com.dextra.model.Sandwich

interface IApi {

    fun getSandwiches(result: ResponseRequest<List<Sandwich>>)

    fun getIngredientsOf(id: Int, result: ResponseRequest<List<Ingredients>>)

    fun getSandwich(id: Int, result: ResponseRequest<Sandwich>)

    fun addLanche(id: Int, result: ResponseRequest<Sandwich>)

    fun addCustomSandwich(id: Int, result: ResponseRequest<Sandwich>)

    fun getIngredients(result: ResponseRequest<List<Ingredients>>)

    fun getOrder(result: ResponseRequest<List<Ingredients>>)

    fun getPromotions(result: ResponseRequest<List<Promotion>>)
}