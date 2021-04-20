package com.example.atividade1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity(), FruitAdapter.OnItemClickListener {
    private var baseList: ArrayList<FruitData> = generateBaseList(3)
    private val adapter = FruitAdapter(baseList, this)
    val model: FruitViewModel by viewModels()

    companion object {
        const val MAIN_ACTIVITY_FRUIT_RESULT_CODE = 1
        const val MAIN_ACTIVITY_FRUIT_ID = "fruit"
        const val MAIN_ACTIVITY_POSITION_ID = "position"
        const val FRUIT_STORE = "fruit"
        const val DETAIL_FRUIT_ACTIVITY = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            baseList = savedInstanceState.getParcelableArrayList(FRUIT_STORE)!!
        }

        fruit_list.adapter = adapter
        fruit_list.layoutManager = LinearLayoutManager(this)
        fruit_list.setHasFixedSize(true)

        model.getFruits().observe(this, Observer<List<FruitData>>{ fruits ->
            baseList = fruits as ArrayList<FruitData>
            adapter.notifyDataSetChanged()
        })

    }

    private fun generateBaseList(size: Int): ArrayList<FruitData>{
        val list = ArrayList<FruitData>()

        for (i in 0 until size){
            val item = FruitData(
                image = null,
                name = "Fruta: $i",
                description = "Uma descrição qualquer"
            )
            list += item
        }

        return  list
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == MAIN_ACTIVITY_FRUIT_RESULT_CODE ){
            val fruit: FruitData? = data?.getParcelableExtra<FruitData>(MAIN_ACTIVITY_FRUIT_ID);

            if (fruit != null) {
                addFruit(fruit)
            }
        }else if (requestCode == DETAIL_FRUIT_ACTIVITY){
            val position: Int? = data?.getIntExtra(MAIN_ACTIVITY_POSITION_ID, 0)

            if (position != null){
                removeFruit(position)
            }
        }
    }

    fun goToInsertFruitActivity(view: View){
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun addFruit(fruit: FruitData){
        if (fruit != null) {
            baseList.add(fruit)
        }

        adapter.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putParcelableArrayList(FRUIT_STORE, baseList)
        super.onSaveInstanceState(savedInstanceState)
    }

    private fun removeFruit(index: Int){
        baseList.removeAt(index)

        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: FruitData = baseList[position]

        val intent = Intent(this@MainActivity, FruitActivity::class.java)

        println("DEBUG POSITION IN MAIN: $position")
        intent.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_ID, clickedItem)
        intent.putExtra(MainActivity.MAIN_ACTIVITY_POSITION_ID, position)
        startActivityForResult(intent, DETAIL_FRUIT_ACTIVITY)
    }
}