package br.com.dextra.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.dextra.R
import br.com.dextra.extension.toBRCurrency
import br.com.dextra.extension.withImage
import br.com.dextra.model.Ingredients
import kotlinx.android.synthetic.main.item_personalize.view.btn_decrease
import kotlinx.android.synthetic.main.item_personalize.view.btn_increase
import kotlinx.android.synthetic.main.item_personalize.view.btn_remove
import kotlinx.android.synthetic.main.item_personalize.view.image
import kotlinx.android.synthetic.main.item_personalize.view.ingredient_description
import kotlinx.android.synthetic.main.item_personalize.view.ingredient_price
import kotlinx.android.synthetic.main.item_personalize.view.label_amount

class PersonalizationAdapter(private val sandwich: List<Ingredients>)
    : RecyclerView.Adapter<PersonalizationAdapter.ViewHolder>() {

    private val max = 5
    private val min = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_personalize, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = sandwich.size

    fun getItem(position: Int) = sandwich[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val image: ImageView by lazy { itemView.image }
        private val ingredientName: TextView by lazy { itemView.ingredient_description }
        private val ingredientPrice: TextView by lazy { itemView.ingredient_price }
        private val btnIncrease: Button by lazy { itemView.btn_increase }
        private val btnDecrease: Button by lazy { itemView.btn_decrease }
        private val btnRemove: Button by lazy { itemView.btn_remove }
        private val amount: TextView by lazy { itemView.label_amount }

        fun bind(ingredient: Ingredients) {
            ingredientName.text = ingredient.name
            ingredientPrice.text = ingredient.price.toBRCurrency()
            image.withImage(ingredient.image)
            btnDecrease.setOnClickListener(this)
            btnIncrease.setOnClickListener(this)
            btnRemove.setOnClickListener(this)
            amount.text = "1"
        }

        override fun onClick(v: View?) {

        }


    }
}