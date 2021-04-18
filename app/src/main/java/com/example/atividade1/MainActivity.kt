package com.example.atividade1

import android.content.Intent
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {
    private val baseList = generateBaseList(20)
    private val adapter = FruitAdapter(baseList)


    companion object {
        const val MAIN_ACTIVITY_FRUIT_RESULT_CODE = 1
        const val MAIN_ACTIVITY_FRUIT_ID = "fruit"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fruit_list.adapter = adapter
        fruit_list.layoutManager = LinearLayoutManager(this)
        fruit_list.setHasFixedSize(true)
    }

    private fun generateBaseList(size: Int): ArrayList<FruitData>{
        val list = ArrayList<FruitData>()

        for (i in 0 until size){
            val item = FruitData(image=0, name = "Fruta: $i", description = "Uma descrição qualquer")
            list += item
        }

        return  list
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(MAIN_ACTIVITY_FRUIT_RESULT_CODE == requestCode){
            val fruit = data?.getParcelableExtra<FruitData>(MAIN_ACTIVITY_FRUIT_ID);


            if (fruit != null) {
                baseList.add(fruit)
            }

            adapter.notifyDataSetChanged()
        }

    }

    fun goToInsertFruitActivity(view: View){
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun removeItem(view: View){}
}