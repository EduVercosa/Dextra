package br.com.dextra

import br.com.dextra.model.Ingredients
import br.com.dextra.model.SandwichResponse
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UnitTests {

    /*
    Ingredientes:
    1: {id: 1, name: 'Alface', price: 0.40, image: 'https://goo.gl/9DhCgk'},
	2: {id: 2, name: 'Bacon', price: 2.00, image: 'https://goo.gl/8qkVH0'},
	3: {id: 3, name: 'Hamburguer de Carne', price: 3.00, image: 'https://goo.gl/U01SnT'},
	4: {id: 4, name: 'Ovo', price: 0.80, image: 'https://goo.gl/weL1Rj'},
	5: {id: 5, name: 'Queijo', price: 1.50, image: 'https://goo.gl/D69Ow2'},
	6: {id: 6, name: 'PÃ£o com gergelim', price: 1.00, image: 'https://goo.gl/evgjyj'}

    Hamburgues:
    1: {id: 1, name: 'X-Bacon', ingredients: [2, 3, 5, 6], image: 'https://goo.gl/W9WdaF'},
	2: {id: 2, name: 'X-Burger', ingredients: [3, 5, 6], image: 'https://goo.gl/Cjfxi9'},
	3: {id: 3, name: 'X-Egg', ingredients: [3, 4, 5, 6], image: 'https://goo.gl/x4rNIq'},
	4: {id: 4, name: 'X-Egg Bacon', ingredients: [1, 2, 3, 4, 5, 6], image: 'https://goo.gl/2L0lqg'}
     */

    lateinit var alface: Ingredients
    lateinit var bacon: Ingredients
    lateinit var ovo: Ingredients
    lateinit var pao: Ingredients
    lateinit var queijo: Ingredients
    lateinit var carne: Ingredients

    var ingXBacon: MutableList<Ingredients> = mutableListOf()
    var ingXBurguer: MutableList<Ingredients> = mutableListOf()
    var ingXEgg: MutableList<Ingredients> = mutableListOf()
    var ingXEggBacon: MutableList<Ingredients> = mutableListOf()

    lateinit var xBurguer: SandwichResponse
    lateinit var xBacon: SandwichResponse
    lateinit var xEgg: SandwichResponse
    lateinit var xEggBacon: SandwichResponse

    @Before
    fun setup() {
        alface = Ingredients(id = 1, name = "Alface", price = 0.40, image = "https://goo.gl/9DhCgk")
        bacon = Ingredients(id = 2, name = "Bacon", price = 2.00, image = "https://goo.gl/9DhCgk")
        ovo = Ingredients(id = 4, name = "ovo", price = 0.80, image = "https://goo.gl/9DhCgk")
        queijo = Ingredients(id = 5, name = "Queijo", price = 1.50, image = "https://goo.gl/9DhCgk")
        pao = Ingredients(id = 6, name = "Pao", price = 1.00, image = "https://goo.gl/9DhCgk")
        carne = Ingredients(id = 3, name = "Carne", price = 3.00, image = "https://goo.gl/9DhCgk")
        setupIngredients()

        xBacon = SandwichResponse(id = 1, name = "X-Bacon", ingredients = ingXBacon, image = "")
        xBurguer = SandwichResponse(
                id = 2, name = "X-Burguer", ingredients = ingXBurguer, image =
        ""
        )
        xEgg = SandwichResponse(id = 3, name = "X-Egg", ingredients = ingXEgg, image = "")
        xEggBacon = SandwichResponse(
                id = 4, name = "X-EggBacon", ingredients = ingXEggBacon,
                image =
                ""
        )
    }

    @Test
    fun checkPrice_XBurguer() {
        Assert.assertThat(xBurguer.calcNormalPrice(), CoreMatchers.`is`(5.5))
    }

    @Test
    fun checkPrice_XBacon() {
        Assert.assertThat(xBacon.calcNormalPrice(), CoreMatchers.`is`(7.5))
    }

    @Test
    fun checkPrice_XEgg() {
        Assert.assertThat(xEgg.calcNormalPrice(), CoreMatchers.`is`(6.3))
    }

    @Test
    fun checkPrice_XEggBacon() {
        Assert.assertThat(xEggBacon.calcNormalPrice(), CoreMatchers.`is`(8.7))
    }

    @Test
    fun checkPrice_XBurguer_3_Portion_Cheese() {
        //Calc explanation

        //xBurguer price is 5.5 with 1 cheese = 1.5, 1 meat 3.0, 1 bread = 1.0
        //So this hamburguer have one portion of cheese and we put tow more portion
        //so now we have 3 portion of cheese and the rule is for each 3 portion of cheese
        //just two will be charged

        ingXBurguer.add(queijo)
        ingXBurguer.add(queijo)

        Assert.assertThat(
                xBurguer.calcPriceWithDiscount(queijo.id, queijo.price), CoreMatchers.`is`
        (8.5)
        )
    }

    @Test
    fun checkPrice_XBurguer_4_Portion_Cheese_No_Discount() {

        ingXBurguer.add(queijo)
        ingXBurguer.add(queijo)
        ingXBurguer.add(queijo)

        Assert.assertThat(
                xBurguer.calcPriceWithDiscount(queijo.id, queijo.price), CoreMatchers.`is`(10.0)
        )
    }

    @Test
    fun checkPrice_XBurguer_6_Portion_Cheese() {

        ingXBurguer.add(queijo)
        ingXBurguer.add(queijo)
        ingXBurguer.add(queijo)
        ingXBurguer.add(queijo)
        ingXBurguer.add(queijo)

        Assert.assertThat(xBurguer.calcNormalPrice(), CoreMatchers.`is`(13.0))
    }

    @Test
    fun checkPrice_XBurguer_3_Portion_Meat() {
        //Calc explanation

        //xBurguer price is 5.5 with 1 cheese = 1.5, 1 meat 3.0, 1 bread = 1.0
        //So this hamburguer have one portion of cheese and we put tow more portion
        //so now we have 3 portion of cheese and the rule is for each 3 portion of cheese
        //just two will be charged

        ingXBurguer.add(carne)
        ingXBurguer.add(carne)

        Assert.assertThat(
                xBurguer.calcPriceWithDiscount(carne.id, carne.price), CoreMatchers.`is`(8.5)
        )
    }

    @Test
    fun checkPrice_XBurguer_4_Portion_Meat_No_Discount() {

        ingXBurguer.add(carne)
        ingXBurguer.add(carne)
        ingXBurguer.add(carne)

        Assert.assertThat(
                xBurguer.calcPriceWithDiscount(carne.id, carne.price), CoreMatchers.`is`(14.5)
        )
    }

    @Test
    fun checkPrice_XBurguer_6_Portion_Meat() {

        ingXBurguer.add(carne)
        ingXBurguer.add(carne)
        ingXBurguer.add(carne)
        ingXBurguer.add(carne)
        ingXBurguer.add(carne)

        Assert.assertThat(
                xBurguer.calcPriceWithDiscount(carne.id, carne.price), CoreMatchers.`is`(14.5)
        )
    }

    @Test
    fun checkPrice_XEggBacon_No_Bacon() {
        //XEggBacon price 8,7
        //Without bacon 8,7 - 2 = 6,7
        //10% Discount 6,7 - 0,67 = 6,03
        xEggBacon.ingredients.remove(bacon)
        Assert.assertThat(xEggBacon.calcPriceWithoutBacon(), CoreMatchers.`is`(6.03))
    }

    private fun setupIngredients() {
        ingXEggBacon.add(alface)
        ingXEggBacon.add(ovo)
        ingXEggBacon.add(carne)
        ingXEggBacon.add(queijo)
        ingXEggBacon.add(pao)
        ingXEggBacon.add(bacon)

        ingXEgg.add(carne)
        ingXEgg.add(ovo)
        ingXEgg.add(queijo)
        ingXEgg.add(pao)

        ingXBurguer.add(carne)
        ingXBurguer.add(queijo)
        ingXBurguer.add(pao)

        ingXBacon.add(bacon)
        ingXBacon.add(carne)
        ingXBacon.add(queijo)
        ingXBacon.add(pao)
    }

}