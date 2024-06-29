package com.example.fuelconsumption

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListaDeCombustiveisActivity : AppCompatActivity() {
    private lateinit var lvCombustiveis : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_combustiveis)
        lvCombustiveis = findViewById(R.id.lvCombustiveis)
        lvCombustiveis.setOnItemClickListener{parent,view,position,id->
            val combustivelSelecionado = position.toDouble() + 10
            intent.putExtra("combustivel", combustivelSelecionado)
            setResult(RESULT_OK,intent)
            finish()
        }

    }
}