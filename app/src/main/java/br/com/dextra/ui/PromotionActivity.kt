package br.com.dextra.ui

import android.os.Bundle
import br.com.dextra.R
import br.com.dextra.model.Promotion
import br.com.dextra.repository.IRepositorySandwiches
import br.com.dextra.ui.adapter.PromotionAdapter
import kotlinx.android.synthetic.main.activity_promotion.recyclerViewPromotion

class PromotionActivity : DextraBaseActivity(),
        IRepositorySandwiches.ViewPromotions{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion)
    }

    override fun onResume() {
        super.onResume()
        repository().getPromotions(this)
    }

    override fun onSuccessPromotion(result: List<Promotion>) {
        recyclerViewPromotion.adapter = PromotionAdapter(result)
    }

    override fun onFailPromotion() {
        showMessageError()
    }
}
