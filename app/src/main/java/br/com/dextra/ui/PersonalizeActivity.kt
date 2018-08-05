package br.com.dextra.ui

import android.os.Bundle
import br.com.dextra.R
import br.com.dextra.cache.RTCache
import br.com.dextra.extension.toBRCurrency
import br.com.dextra.extension.withImage
import br.com.dextra.model.SandwichResponse
import br.com.dextra.ui.adapter.PersonalizationAdapter
import kotlinx.android.synthetic.main.activity_personalize.btnCancel
import kotlinx.android.synthetic.main.activity_personalize.recyclerViewPersonalize
import kotlinx.android.synthetic.main.item_menu.name
import kotlinx.android.synthetic.main.item_menu.sandwich_description
import kotlinx.android.synthetic.main.item_menu.sandwich_image
import kotlinx.android.synthetic.main.item_menu.sandwich_price

class PersonalizeActivity : DextraBaseActivity(){

    private lateinit var adapter: PersonalizationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalize)
        RTCache.sandwichSelected?.let {
            fillData(it)
            adapter = PersonalizationAdapter(it.ingredients)
            recyclerViewPersonalize.adapter = adapter
        }
        btnCancel.setOnClickListener { onBackPressed() }
    }

    private fun fillData(sandwichResponse: SandwichResponse){
        sandwich_price.text = sandwichResponse.price?.toBRCurrency()
        sandwichResponse.image?.let { sandwich_image.withImage(it) }
        sandwich_description.text = sandwichResponse.ingredientsList()
        name.text = sandwichResponse.name
    }

}
