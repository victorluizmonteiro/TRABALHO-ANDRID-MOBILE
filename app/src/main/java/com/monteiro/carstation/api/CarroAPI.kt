package com.monteiro.carstation.api

import com.monteiro.carstation.model.Carro
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Monteiro on 10/03/18.
 */
interface CarroAPI {

    @GET("carro")
    fun buscarTodos(): Call<MutableList<Carro>>

    @POST("carro")
    fun salvar(@Body carro: Carro): Call<Void>

    @DELETE("carro/deletar/{placa}")
    fun remover(@Path("placa")placa:String):Call<Void>

}