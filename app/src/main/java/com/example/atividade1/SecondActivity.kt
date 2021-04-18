package com.example.atividade1

import android.R.id.message
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.view.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        save_fruit_btn.setOnClickListener{
            val name: String = fruit_name_input.text.toString()
            val description: String = fruit_description_input.text.toString()
            val returnIntent = Intent()

            val fruit = FruitData(name = name, description = description, image = 0)

            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_ID, fruit)
            setResult(MainActivity.MAIN_ACTIVITY_FRUIT_RESULT_CODE, returnIntent)
            finish()
        }
    }

}