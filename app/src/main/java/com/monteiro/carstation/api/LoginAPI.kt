package com.monteiro.carstation.api

import com.monteiro.carstation.model.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginAPI {

    @GET("user/{username}")
    fun logar(@Path("username")username:String):Call<Usuario>
}