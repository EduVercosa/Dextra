package br.com.dextra.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.dextra.R
import br.com.dextra.extension.toBRCurrency
import br.com.dextra.extension.withImage
import br.com.dextra.model.SandwichResponse
import kotlinx.android.synthetic.main.item_menu.view.name
import kotlinx.android.synthetic.main.item_menu.view.sandwich_description
import kotlinx.android.synthetic.main.item_menu.view.sandwich_image
import kotlinx.android.synthetic.main.item_menu.view.sandwich_price

class SandwichAdapter(private val sandwich: List<SandwichResponse>
                      , private var listener: OnSandwichSelected)
    : RecyclerView.Adapter<SandwichAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = sandwich.size

    fun getItem(position: Int) = sandwich[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val price: TextView by lazy { itemView.sandwich_price }
        private val image: ImageView by lazy { itemView.sandwich_image }
        private val description: TextView by lazy { itemView.sandwich_description }
        private val name: TextView by lazy { itemView.name }
        lateinit var listener: OnSandwichSelected
        lateinit var sandwich: SandwichResponse

        fun bind(sandwich: SandwichResponse, listener: OnSandwichSelected) {
            this.listener = listener
            this.sandwich = sandwich
            itemView.setOnClickListener(this)
            description.text = sandwich.ingredientsList()
            name.text = sandwich.name
            price.text = sandwich.price?.toBRCurrency()
            sandwich.image?.let { image.withImage(it) }
        }

        override fun onClick(v: View?) {
            listener.onSandwichSelected(sandwich)
        }

    }

    interface OnSandwichSelected {
        fun onSandwichSelected(s: SandwichResponse)
    }
}