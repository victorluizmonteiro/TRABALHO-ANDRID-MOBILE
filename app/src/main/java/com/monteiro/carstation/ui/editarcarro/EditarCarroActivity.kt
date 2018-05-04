package com.monteiro.carstation.ui.editarcarro

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.monteiro.carstation.R
import com.monteiro.carstation.R.id.*
import com.monteiro.carstation.api.CarroAPI
import com.monteiro.carstation.api.RetrofitClient
import com.monteiro.carstation.model.Carro
import kotlinx.android.synthetic.main.activity_editar_carro.*
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarCarroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_carro)

        inputPlacaEditar.isEnabled.not()

        val carro = intent.getSerializableExtra("carro") as Carro


        carregarDadosCarro(carro)

        btnEditarCarro.setOnClickListener{
            val api = RetrofitClient
                    .getInstance()
                    .create(CarroAPI::class.java)
            val carro = Carro(inputPlacaEditar.editText?.text.toString(),
                    inputMarcaEditar.editText?.text.toString(),
                    inputModeloEditar.editText?.text.toString(),
                    inputAnoEditar.editText?.text.toString().toInt(),
                    inputUrlImagemEditar.editText?.text.toString(),
                    inputValorEditar.editText?.text.toString().toDouble()
            )

            api.salvar(carro)
                    .enqueue(object : Callback<Void> {
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("CARRO", t?.message)
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true) {
                                Toast.makeText(baseContext,
                                        "Carro Atualizado com Sucesso",
                                        Toast.LENGTH_SHORT).show()
                                limparCampos()
                            } else {
                                Toast.makeText(baseContext,
                                        "Não foi possivel atualizar o Carro ",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }




    }
    fun carregarDadosCarro(carro: Carro) {
        inputAnoEditar.editText!!.setText(carro.anoLancamento.toString())
        inputMarcaEditar.editText!!.setText(carro.marca.toString())
        inputModeloEditar.editText!!.setText(carro.modelo.toString())
        inputPlacaEditar.editText!!.setText(carro.placa.toString())
        inputUrlImagemEditar.editText!!.setText(carro.urlImagem.toString())
        inputValorEditar.editText!!.setText(carro.valor.toString())
    }

    private fun limparCampos() {
        inputMarcaEditar.editText?.setText("")
        inputModeloEditar.editText?.setText("")
        inputAnoEditar.editText?.setText("")
        inputPlacaEditar.editText?.setText("")
        inputValorEditar.editText?.setText("")
        inputUrlImagemEditar.editText?.setText("")
    }

}


