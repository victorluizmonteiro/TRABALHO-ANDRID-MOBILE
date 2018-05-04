package com.monteiro.carstation.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.monteiro.carstation.ui.novoUsuario.CadastroUsuarioActivity
import com.monteiro.carstation.R
import com.monteiro.carstation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_novo_carro.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnExit.setOnClickListener{
            fecharApp()
        }

        btnLogin.setOnClickListener{
           logar()
        }



    }


    fun fecharApp(){
        finishAffinity()
    }

    fun goToCadastro(){
        startActivity(Intent(this, CadastroUsuarioActivity::class.java))
    }

    fun logar(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}
