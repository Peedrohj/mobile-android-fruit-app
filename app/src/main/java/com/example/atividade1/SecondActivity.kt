package com.example.atividade1

import android.Manifest
import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore

import androidx.appcompat.app.AppCompatActivity
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private var imageUri: Uri? = null

    companion object {
        private val RESULT_CODE = 100;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        save_fruit_btn.setOnClickListener{
            val name: String = fruit_name_input.text.toString()
            val description: String = fruit_description_input.text.toString()
            val returnIntent = Intent()

            val fruit = FruitData(name = name, description = description, image = imageUri)

            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_ID, fruit)
            setResult(MainActivity.MAIN_ACTIVITY_FRUIT_RESULT_CODE, returnIntent)
            finish()
        }

        save_fruit_image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, RESULT_CODE)
        }
        setSupportActionBar(findViewById(R.id.customToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == RESULT_CODE) {
            val contentResolver = applicationContext.contentResolver
            val modelFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION

            // Save uri and store permissions
            imageUri = data?.data
            imageUri?.let{contentResolver.takePersistableUriPermission(it, modelFlags)}

        }
    }
}