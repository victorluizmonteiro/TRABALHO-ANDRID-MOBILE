package com.monteiro.carstation.ui.buscar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.monteiro.carstation.R
import com.monteiro.carstation.R.id.txtPlaca
import com.monteiro.carstation.api.CarroAPI
import com.monteiro.carstation.api.RetrofitClient
import com.monteiro.carstation.model.Carro
import kotlinx.android.synthetic.main.activity_search_for_plate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchForPlateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.activity_search_for_plate, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscar()

    }

    fun setupCarro(carro:Carro?){
    txtPlaca.text =  txtPlaca.text.toString().plus(": "+ carro?.placa.toString())
    txtAno.text =  txtAno.text.toString().plus(": "+carro?.anoLancamento.toString())
    txtModelo.text = txtModelo.text.toString().plus(": "+carro?.modelo.toString())
    txtMarca.text=  txtMarca.text.toString().plus(": "+carro?.marca.toString())
    txtValor.text = txtValor.text.toString().plus(": "+carro?.valor.toString())


}


    fun buscar(){
        btnSearchPlate.setOnClickListener{
            if (inputSearchPlate.text != null) {
                val api = RetrofitClient.getInstance()
                        .create(CarroAPI::class.java)
                api.buscarPelaPlaca(inputSearchPlate.text.toString())
                        .enqueue(object : Callback<Carro> {
                            override fun onFailure(call: Call<Carro>?, t: Throwable?) {

                               if (inputSearchPlate.text.isNotBlank()){
                                   Toast.makeText(context, "Carro não encontrado !", Toast.LENGTH_LONG).show()

                               }else{
                                   Toast.makeText(context, "Campo obrigatório !", Toast.LENGTH_LONG).show()

                               }
                            }

                            override fun onResponse(call: Call<Carro>?, response: Response<Carro>?) {
                                if (response?.isSuccessful == true) {
                                    setupCarro(response.body())

                                }
                            }

                        })
            }else{
                Toast.makeText(context, "Campo placa é Obrigatório", Toast.LENGTH_LONG).show()

            }
        }
    }
    }






