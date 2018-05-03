package com.monteiro.carstation.ui.editarcarro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.monteiro.carstation.R
import com.monteiro.carstation.model.Carro
import kotlinx.android.synthetic.main.activity_editar_carro.*

class EditarCarroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_carro)

        val carro = intent.getSerializableExtra("carro") as Carro
        carregarDadosCarro(carro)

    }


    fun carregarDadosCarro(carro:Carro){
        inputAnoEditar.editText!!.setText(carro.anoLancamento.toString())
        inputMarcaEditar.editText!!.setText(carro.marca.toString())
        inputModeloEditar.editText!!.setText(carro.modelo.toString())
        inputPlacaEditar.editText!!.setText(carro.placa.toString())
        inputUrlImagemEditar.editText!!.setText(carro.urlImagem.toString())
        inputValorEditar.editText!!.setText(carro.valor.toString())
    }
}
