package com.example.atividade1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        save_fruit_btn.setOnClickListener{
            val name: String = fruit_name_input.text.toString()
            val description: String = fruit_description_input.text.toString()
            val returnIntent = Intent()

            val fruit = FruitData(name = name, description = description, image =imageUri)

            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_ID, fruit)
            setResult(MainActivity.MAIN_ACTIVITY_FRUIT_RESULT_CODE, returnIntent)
            finish()
        }

        save_fruit_image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
        }
    }

}