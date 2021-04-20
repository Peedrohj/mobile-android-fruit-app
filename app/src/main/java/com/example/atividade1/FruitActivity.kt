package com.example.atividade1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_fruit.*

class FruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruit: FruitData? = intent.getParcelableExtra<FruitData>(MainActivity.MAIN_ACTIVITY_FRUIT_ID);

        if (fruit != null) {
            fruit_name.text = fruit.name
            fruit_description.text = fruit.description
            fruit_image.setImageURI(fruit.image)
        }
    }
}