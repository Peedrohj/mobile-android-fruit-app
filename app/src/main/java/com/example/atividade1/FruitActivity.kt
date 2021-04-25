package com.example.atividade1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_fruit.*

class FruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruit: FruitData? = intent.getParcelableExtra<FruitData>(MainActivity.MAIN_ACTIVITY_FRUIT_ID)
        val position: Int? = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_POSITION_ID, 0)

        if (fruit != null) {
            customToolbar.title = fruit.name
            fruit_description.text = fruit.description

            if(fruit.image != null){
                fruit_image.setImageURI(fruit.image)

            }else{
                fruit_image.setImageResource(R.drawable.ic_default_image)
            }
        }

        delete_button.setOnClickListener {
            val returnIntent = Intent()

            println("DEBUG position: $position")
            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_POSITION_ID, position)
            setResult(MainActivity.DETAIL_FRUIT_ACTIVITY, returnIntent)
            finish()
        }

        setSupportActionBar(findViewById(R.id.customToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}