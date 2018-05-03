package com.monteiro.carstation.ui.novocarro


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.monteiro.carstation.R
import  com.monteiro.carstation.api.CarroAPI
import  com.monteiro.carstation.api.RetrofitClient
import  com.monteiro.carstation.model.Carro
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent.getIntent



class NovoCarroFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_novo_carro, container, false)



    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSalvar.setOnClickListener {
            val api = RetrofitClient
                    .getInstance()
                    .create(CarroAPI::class.java)
            val carro = Carro(inputPlaca.editText?.text.toString(),
                    inputMarca.editText?.text.toString(),
                    inputModelo.editText?.text.toString(),
                    inputAno.editText?.text.toString().toInt(),
                    inputUrlImagem.editText?.text.toString(),
                    inputValor.editText?.text.toString().toDouble()
                    )

            api.salvar(carro)
                    .enqueue(object : Callback<Void> {
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("CARRO", t?.message)
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true) {
                                Toast.makeText(context,
                                        "Sucesso",
                                        Toast.LENGTH_SHORT).show()
                                limparCampos()
                            } else {
                                Toast.makeText(context,
                                        "Errou coloque aqui o Faustão",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }

        
    }

    private fun limparCampos() {
        inputMarca.editText?.setText("")
        inputModelo.editText?.setText("")
        inputAno.editText?.setText("")
        inputPlaca.editText?.setText("")
        inputValor.editText?.setText("")
        inputUrlImagem.editText?.setText("")
    }

}// Required empty public constructor