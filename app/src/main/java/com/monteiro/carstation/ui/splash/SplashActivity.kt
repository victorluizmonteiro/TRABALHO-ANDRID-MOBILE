package com.monteiro.carstation.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.monteiro.carstation.R
import com.monteiro.carstation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        carregar()
    }

    fun carregar() {
        //Carrega a animação
        val animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        //Inicia a animação no elemento referenciado pelo id
        idLogoCarro.startAnimation(animacao);
        /*Após terminar de carregar a animação, criamos uma nova Thread utilizando o postDelayed utilizando Runnable(Lambda)
         Que realiza a troca de activity após 3 segundos
         */
        Handler().postDelayed(({
            startActivity(Intent(this, MainActivity::class.java))
            this.finish();
        }

                ), 3000);

    }
}
