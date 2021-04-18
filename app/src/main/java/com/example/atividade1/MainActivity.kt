package com.example.atividade1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseList = generateBaseList(20)

        fruit_list.adapter = FruitAdapter(baseList)
        fruit_list.layoutManager = LinearLayoutManager(this)
        fruit_list.setHasFixedSize(true)
    }

    private fun generateBaseList(size: Int): List<Fruit>{
        val list = ArrayList<Fruit>()

        for (i in 0 until size){
            val item = Fruit(0, title="Fruta: $i", description = "Uma descrição qualquer")
            list += item
        }

        return  list
    }
}