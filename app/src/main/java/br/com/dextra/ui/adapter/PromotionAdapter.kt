package br.com.dextra.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.dextra.R
import br.com.dextra.model.Promotion
import kotlinx.android.synthetic.main.item_promotion.view.promotion_description
import kotlinx.android.synthetic.main.item_promotion.view.promotion_name

class PromotionAdapter(private val sandwich: List<Promotion>) : RecyclerView.Adapter<PromotionAdapter
.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = sandwich.size

    fun getItem(position: Int) = sandwich[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val promotionName: TextView by lazy { itemView.promotion_name }
        private val promotionDescription: TextView by lazy { itemView.promotion_description }

        fun bind(promotion: Promotion) {
            promotionName.text = promotion.name
            promotionDescription.text = promotion.description
        }

    }
}