package br.com.dextra.repository

import br.com.dextra.api.IApi
import br.com.dextra.api.ResponseRequest
import br.com.dextra.model.Ingredients
import br.com.dextra.model.Promotion
import br.com.dextra.model.Sandwich
import br.com.dextra.model.SandwichResponse

class RepositorySandwiches(private val api: IApi) :
        IRepositorySandwiches.Presenter{

    private var listSandwich = mutableListOf<Sandwich>()
    private var listSandwichResponse = mutableListOf<SandwichResponse>()
    private var listPromotions = mutableListOf<Promotion>()

    override fun getPromotions(view: IRepositorySandwiches.ViewPromotions) {
        if(listPromotions.isEmpty()){
            loadPromotion(view)
        }else{
            view.onSuccessPromotion(listPromotions)
        }
    }

    override fun getSandwiches(view: IRepositorySandwiches.ViewSandwiches) {
        if(listSandwichResponse.isEmpty()) {
            loadSandwiches(view)
        }else{
            view.onSuccessSandwiches(listSandwichResponse)
        }
    }

    private fun loadSandwiches(view: IRepositorySandwiches.ViewSandwiches) {
        api.getSandwiches(object : ResponseRequest<List<Sandwich>> {
            override fun success(result: List<Sandwich>) {
                listSandwich.addAll(result)
                loadIngredients(view)
            }

            override fun fail(message: String?) {
                view.onFailSandwiches()
            }
        })
    }

    private fun loadIngredients(view: IRepositorySandwiches.ViewSandwiches) {
        api.getIngredients(object : ResponseRequest<List<Ingredients>> {
            override fun success(result: List<Ingredients>) {
                listSandwich.forEach { sandwich ->
                    var tempPrice = 0.0
                    val sandwichResponse = SandwichResponse(
                             id = sandwich.id
                            ,image = sandwich.image
                            ,name = sandwich.name
                    )

                    for ((index, value) in result.withIndex()) {
                        if (sandwich.ingredientsId.contains(value.id)) {
                            sandwichResponse.ingredients.add(value)
                            tempPrice += value.price
                            sandwichResponse.price = tempPrice
                        }
                    }
                    listSandwichResponse.add(sandwichResponse)
                }
                view.onSuccessSandwiches(listSandwichResponse)
            }

            override fun fail(message: String?) {
                view.onFailSandwiches()
            }
        })
    }

    private fun loadPromotion(view: IRepositorySandwiches.ViewPromotions){
        api.getPromotions(object : ResponseRequest<List<Promotion>>{
            override fun success(result: List<Promotion>) {
                view.onSuccessPromotion(result)
            }

            override fun fail(message: String?) {
                view.onFailPromotion()
            }
        })
    }
}