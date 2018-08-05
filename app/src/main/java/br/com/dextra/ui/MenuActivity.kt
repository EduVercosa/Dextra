package br.com.dextra.ui

import android.os.Bundle
import br.com.dextra.R
import br.com.dextra.cache.RTCache
import br.com.dextra.extension.invisible
import br.com.dextra.extension.start
import br.com.dextra.model.SandwichResponse
import br.com.dextra.repository.IRepositorySandwiches
import br.com.dextra.ui.adapter.SandwichAdapter
import kotlinx.android.synthetic.main.activity_menu.btnCarrinho
import kotlinx.android.synthetic.main.activity_menu.btnPromocao
import kotlinx.android.synthetic.main.activity_menu.progress
import kotlinx.android.synthetic.main.activity_menu.recyclerView

class MenuActivity : DextraBaseActivity(),
        IRepositorySandwiches.ViewSandwiches, SandwichAdapter.OnSandwichSelected{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        btnPromocao.setOnClickListener { start<PromotionActivity>() }
        btnCarrinho.setOnClickListener {  }
    }

    override fun onResume() {
        super.onResume()
        repository().getSandwiches(this)
    }

    override fun onSuccessSandwiches(result: List<SandwichResponse>) {
        progress.invisible()
        recyclerView.adapter = SandwichAdapter(result, this@MenuActivity)
    }

    override fun onFailSandwiches() {
        showMessageError()
    }

    override fun onSandwichSelected(s: SandwichResponse) {
        RTCache.sandwichSelected = s
        start<PersonalizeActivity>()
    }
}
