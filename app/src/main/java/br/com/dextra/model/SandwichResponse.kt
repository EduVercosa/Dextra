package br.com.dextra.model

data class SandwichResponse(
        var id: Int? = 0,
        var name: String? = null,
        var ingredients: MutableList<Ingredients> = mutableListOf<Ingredients>(),
        var image: String? = null,
        var price: Double? = 0.0

) {
    fun ingredientsList(): String {
        var list: String = ""
        ingredients.forEach { list += it.name + ", " }
        return list
    }

    fun calcNormalPrice(): Double {
        var temp: Double = 0.0
        ingredients.forEach {
            temp += it.price
        }

        return temp
    }

    fun calcPriceWithoutBacon(): Double {
        val ids = mutableListOf<Int>()

        var p = calcNormalPrice()
        ingredients.forEach {
            ids.add(it.id)
        }

        if (ids.contains(1) && !ids.contains(2)) {
            p -= p * 0.1
        }
        return p
    }

    fun calcPriceWithDiscount(idIngredient: Int, value: Double): Double {
        val normalPrice = calcNormalPrice()
        var finalPrice = 0.0
        var qtde = 0

        ingredients.forEach {
            if (it.id == idIngredient) {
                qtde++
            }
        }

        val mod = qtde % 3
        return if (mod == 0 && qtde > 0) {
            normalPrice + qtde - 2 * qtde / 3 * value
        } else {
            normalPrice
        }
    }
}