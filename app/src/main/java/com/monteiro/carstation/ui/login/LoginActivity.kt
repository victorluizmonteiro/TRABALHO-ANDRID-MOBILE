package com.monteiro.carstation.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.monteiro.carstation.ui.novoUsuario.CadastroUsuarioActivity
import com.monteiro.carstation.R
import com.monteiro.carstation.api.CarroAPI
import com.monteiro.carstation.api.LoginAPI
import com.monteiro.carstation.api.RetrofitClient
import com.monteiro.carstation.model.Usuario
import com.monteiro.carstation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        btnLogin.setOnClickListener{
           logar()
        }



    }






    fun logar(){

        val api = RetrofitClient.getInstance()
                .create(LoginAPI::class.java)
        api.logar(inputUsername.text.toString())
                .enqueue(object : Callback<Usuario>{
                    override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                       // val user : Usuario = response as Usuario

                        if(response?.isSuccessful == true && response?.body()?.password.toString().equals(inputPassword.text.toString())) {
                            startActivity(Intent(applicationContext, MainActivity::class.java))

                        }else{
                            Toast.makeText(applicationContext,"Username ou Password inválidos ! ",Toast.LENGTH_LONG).show()

                        }

                    }

                    override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                        Toast.makeText(applicationContext,"Username ou Password inválidos ! ",Toast.LENGTH_LONG).show()
                    }

                })

    }
}
