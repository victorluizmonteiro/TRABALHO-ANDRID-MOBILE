package com.monteiro.carstation.ui.listacarro

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat.finishAffinity
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.monteiro.carstation.R
import com.monteiro.carstation.api.RetrofitClient

import com.monteiro.carstation.api.CarroAPI
import com.monteiro.carstation.model.Carro
import com.monteiro.carstation.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_lista_carro.*
import kotlinx.android.synthetic.main.item_carro.*
import kotlinx.android.synthetic.main.item_carro.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Duration


class ListaCarroFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        carregarDados()




        // Inflate the layout for this fragment


        return inflater!!.inflate(R.layout.fragment_lista_carro, container, false)


    }




    fun carregarDados() {
        val api = RetrofitClient.getInstance()
                .create(CarroAPI::class.java)

        api.buscarTodos()
                .enqueue(object : Callback<MutableList<Carro>> {
                    override fun onResponse(call: Call<MutableList<Carro>>?, response: Response<MutableList<Carro>>?) {
                        if (response?.isSuccessful == true) {
                            setupLista(response?.body())

                        }
                    }

                    override fun onFailure(call: Call<MutableList<Carro>>?, t: Throwable?) {

                    }

                })


    }



    fun setupLista(carros: MutableList<Carro>?) {
        carros.let {
            rvCarros.adapter = ListaCarroAdapter(carros!!, context)
            val layoutManager = LinearLayoutManager(context)
            rvCarros.layoutManager = layoutManager


        }

    }


}// Required empty public constructor
