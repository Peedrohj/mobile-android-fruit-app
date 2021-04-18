package com.example.atividade1

import android.R.id.message
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_second.view.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun saveFruit(view: View){
        val name: String = view.fruit_name_input.text.toString()
        val description: String = view.fruit_description_input.text.toString()

        println("Fruit name: $name")
        println("Fruit description: $description")

        val fruit = FruitData(name = name, description = description, image = 0)

        intent.putExtra("fruit", fruit)
        setResult(1, intent)
        finish()
    }
}