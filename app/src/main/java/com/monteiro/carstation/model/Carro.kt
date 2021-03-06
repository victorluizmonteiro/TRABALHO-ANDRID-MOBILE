package com.monteiro.carstation.model

import java.io.Serializable

/**
 * Created by Monteiro on 10/03/18.
 */
data class Carro(var placa: String?,
                 var marca: String,
                 var modelo: String,
                 var anoLancamento: Int,
                 var urlImagem: String,
                 var valor : Double) : Serializable{

}