package com.monteiro.carstation.ui.novocarro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.monteiro.carstation.R
import com.monteiro.carstation.api.CarroAPI
import com.monteiro.carstation.api.RetrofitClient
import com.monteiro.carstation.model.Carro
import kotlinx.android.synthetic.main.activity_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovoCarro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_carro)

        btnCadastrarCarro.setOnClickListener {
            val api = RetrofitClient
                    .getInstance()
                    .create(CarroAPI::class.java)
            val carro = Carro(txtPlaca.text.toString(),
                    txtMarca.text.toString(),
                    txtModelo.text.toString(),
                    txtAnoFabricacao.text.toString().toInt(),

                    txtUrlCarro.text.toString(),
                    txtValor.text.toString().toDouble())

            api.salvar(carro)
                    .enqueue(object : Callback<Void> {
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("CARRO", t?.message)
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true) {
                                Toast.makeText(applicationContext,
                                        "Sucesso",
                                        Toast.LENGTH_SHORT).show()
                                limparCampos()
                            } else {
                                Toast.makeText(applicationContext,
                                        "Errou coloque aqui o Faustão",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }
    }

    private fun limparCampos() {
        txtMarca?.setText("")
        txtModelo?.setText("")
        txtAnoFabricacao?.setText("")
        txtPlaca?.setText("")
        txtUrlCarro?.setText("")
    }
    }



