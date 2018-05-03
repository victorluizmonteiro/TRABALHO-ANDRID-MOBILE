package com.monteiro.carstation.ui.listacarro


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.monteiro.carstation.R
import com.monteiro.carstation.api.CarroAPI
import com.monteiro.carstation.api.RetrofitClient
import com.monteiro.carstation.model.Carro
import kotlinx.android.synthetic.main.item_carro.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.ContextWrapper
import com.monteiro.carstation.ui.editarcarro.EditarCarroActivity


/**
 * Created by Monteiro on 10/03/18.
 */

class ListaCarroAdapter(private val carros: MutableList<Carro>,
                         private val context: Context)
    : RecyclerView.Adapter<ListaCarroAdapter.MeuViewHolder>() {

    override fun onBindViewHolder(holder: MeuViewHolder?, position: Int) {
        val carro: Carro = carros[position]
        holder?.let {
            it.bindView(carro)
        }


        holder?.itemView!!.btnRemover.setOnClickListener {
            removerCarro(carro, position)
        }






        fun getActivity(view: View): Activity? {
            var context = view.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    return context
                }
                context = context.baseContext
            }
            return null
        }


        holder?.itemView!!.btnEditar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val activity = getActivity(v)
                val intent = Intent(context, EditarCarroActivity::class.java)
               intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                intent.putExtra("carro", carro)
                activity!!.finish()
                activity.startActivity(intent)
            }
        })



}



        override fun getItemCount(): Int {
            return carros.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):
                MeuViewHolder {
            val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_carro, parent, false)


            return MeuViewHolder(view)
        }

        fun removerCarro(carro: Carro, position: Int) {
            val api = RetrofitClient.getInstance()
                    .create(CarroAPI::class.java)

            api.remover(carro.placa.toString()).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    Log.e("CARRO", t?.message)
                }

                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    Toast.makeText(context, "Carro deletado com sucesso !", Toast.LENGTH_LONG).show()
                    carros.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, carros.size)
                }

            }

            )


        }

        class MeuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


            fun bindView(carro: Carro) {
                itemView.tvMarca.text = carro.marca
                itemView.tvModelo.text = carro.modelo
                itemView.tvAnoFabricacao.text = carro.anoLancamento.toString()
                itemView.tvPlaca.text = carro.placa
                itemView.tvValor.text = carro.valor.toString()


/*
            if(carro.urlImagem.isNullOrEmpty()) {
                itemView.ivFoto.setImageDrawable(
                        ContextCompat.getDrawable(itemView.context,
                                R.drawable.procurando)
                )
            } else {
                Picasso.get()
                        .load(carro.urlImagem)
                        .placeholder(R.drawable.procurando)
                        .error(R.drawable.procurando)
                        .into(itemView.ivFoto)
            }
*/

            }


        }
    }

