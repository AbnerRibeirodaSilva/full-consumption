package com.example.fuelconsumption

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private  lateinit var etKmPorLitro1 : EditText
    private  lateinit var etKmPorLitro2 : EditText
    private  lateinit var etPrecoPorLitro1 : EditText
    private  lateinit var etPrecoPorLitro2 : EditText
    private  lateinit var btCalcular : Button
    private  lateinit var btBuscar1 : Button
    private  lateinit var btBuscar2 : Button
    private  lateinit var tvResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         etKmPorLitro1 = findViewById(R.id.etKm1)
         etKmPorLitro2 = findViewById(R.id.etKm2)
         etPrecoPorLitro1 = findViewById(R.id.etValor1)
         etPrecoPorLitro2 = findViewById(R.id.etValor2)
         btCalcular = findViewById(R.id.btCalcular)
         tvResultado = findViewById(R.id.tvResultado)
         btBuscar1 = findViewById(R.id.btBuscar1)
         btBuscar2 = findViewById(R.id.btBuscar2)

        btBuscar1.setOnClickListener{
            buscar1()
        }

        btBuscar2.setOnClickListener{
            buscar2()
        }

        btCalcular.setOnClickListener{
            btCalcularOnClick()
        }
    }
    private fun buscar2(){
        val intent = Intent(this, ListaDeCombustiveisActivity::class.java)
        getBuscar2.launch(intent)
    }

    private val getBuscar2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            if (it.data != null){
                val retorno = it.data?.getDoubleExtra("combustivel", 15.0)
                etKmPorLitro2.setText(retorno.toString())
            }
        }
    }
    private fun buscar1(){
        val intent = Intent(this, ListaDeCombustiveisActivity::class.java)
        getBuscar1.launch(intent)
    }

   private val getBuscar1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            if (it.data != null){
                val retorno = it.data?.getDoubleExtra("combustivel", 15.0)
                etKmPorLitro1.setText(retorno.toString())
            }
        }
    }

    private fun btCalcularOnClick(){
        val km1 = etKmPorLitro1.text.toString().toDoubleOrNull()
        val km2 = etKmPorLitro2.text.toString().toDoubleOrNull()
        val preco1 = etPrecoPorLitro1.text.toString().toDoubleOrNull()
        val preco2 = etPrecoPorLitro2.text.toString().toDoubleOrNull()
        var isValid = true
        if(km1.toString().isNullOrEmpty()){
            etKmPorLitro1.error="Campo obrigatório"
            isValid = false
        }
        if(km2.toString().isNullOrEmpty()){
            etKmPorLitro2.error = "Campo obrigatório"
            isValid = false
        }
        if(preco1.toString().isNullOrEmpty()){
            etPrecoPorLitro1.error = "Campo obrigatório"
            isValid = false
        }
        if(preco2.toString().isNullOrEmpty()){
            etPrecoPorLitro2.error = "Campo obrigatório"
            isValid = false
        }

        if(isValid){
            if(km1 != null && km2 != null && preco1 != null && preco2 != null ){
                val custo1 = preco1 / km1
                val custo2 = preco2 / km2

                val melhorEscolha = if(custo1 < custo2){
                    "O combustível 1 é a melhor escolha"
                }else if(custo2 < custo1){
                    "O combustível 2 é a melhor escolha"
                }else{
                    "Os dois combustiveis tem o mesmo rendimento"
                }
                tvResultado.text = melhorEscolha

            }else{
                tvResultado.text = "Ocorreu algum problema, Por favor, preencha todos os campos corretamente"
            }


        }

    }
}