package com.monteiro.carstation.ui.listacarro

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monteiro.carstation.R
import com.monteiro.carstation.model.Carro

import kotlinx.android.synthetic.main.item_carro.view.*

/**
 * Created by Monteiro on 10/03/18.
 */

class ListaCarroAdapter(private val carros: List<Carro>, private val context: Context
) : RecyclerView.Adapter<ListaCarroAdapter.CarroViewHolder>() {
    override fun onBindViewHolder(holder: CarroViewHolder?, position: Int) {
        val carro = carros[position]
        holder?.let {
            it.bindView(carro)
        }
    }

    override fun getItemCount(): Int {
        return carros.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CarroViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_carro, parent, false)

        return CarroViewHolder(view)


    }


    class CarroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(carro: Carro) {
            itemView.tvMarca.text = carro.marca
            itemView.tvModelo.text = carro.modelo
        }
    }
}