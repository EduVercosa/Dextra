package br.com.dextra.repository

import br.com.dextra.model.Promotion
import br.com.dextra.model.SandwichResponse

interface IRepositorySandwiches {

    interface Presenter{
        fun getSandwiches(view: ViewSandwiches)
        fun getPromotions(view: ViewPromotions)
    }

    interface ViewSandwiches{
        fun onSuccessSandwiches(result : List<SandwichResponse>)
        fun onFailSandwiches()
    }
    interface ViewPromotions{
        fun onSuccessPromotion(result : List<Promotion>)
        fun onFailPromotion()
    }

}