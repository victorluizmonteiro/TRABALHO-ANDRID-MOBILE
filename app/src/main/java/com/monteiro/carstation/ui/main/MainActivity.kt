package com.monteiro.carstation.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.monteiro.carstation.ui.novocarro.NovoCarro
import com.monteiro.carstation.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCadastrar.setOnClickListener {
            val intent = Intent(this, NovoCarro::class.java)
            startActivity(intent)
        }

    }
}