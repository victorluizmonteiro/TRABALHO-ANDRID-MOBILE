package com.monteiro.carstation.api

import com.monteiro.carstation.model.Carro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Monteiro on 10/03/18.
 */
interface CarroAPI {

    @GET("carro")
    fun buscarTodos(): Call<List<Carro>>

    @POST("carro")
    fun salvar(@Body carro: Carro): Call<Void>

}