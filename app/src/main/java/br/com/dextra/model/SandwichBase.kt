package br.com.dextra.model

abstract class SandwichBase{
    var id: Int = 0
    lateinit var name: String
    lateinit var ingredients: List<Ingredients>
    lateinit var image: String
}

