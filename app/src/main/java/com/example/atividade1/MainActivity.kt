package com.example.atividade1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Switch

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import java.nio.channels.Selector
import java.util.zip.Inflater


open class MainActivity : AppCompatActivity(), FruitAdapter.OnItemClickListener {
    private var baseList: ArrayList<FruitData> = generateBaseList(5)
    private var originalList: ArrayList<FruitData> = generateBaseList(0)

    private var adapter = FruitAdapter(baseList, this)
    private var fruitsWithSameName: Boolean = true
    private var selectedOption = 0

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

        fruit_list.adapter = adapter
        fruit_list.layoutManager = LinearLayoutManager(this)
        fruit_list.setHasFixedSize(true)

        originalList.addAll(baseList)

        setSupportActionBar(findViewById(R.id.customToolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    private fun generateBaseList(size: Int): ArrayList<FruitData> {
        val list = ArrayList<FruitData>()

        for (i in 0 until size) {
            val item = FruitData(
                image = null,
                name = "Fruta: ",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec finibus orci non orci fermentum, sed molestie neque tempor. Aliquam condimentum nulla non congue sollicitudin \n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Donec finibus orci non orci fermentum, sed molestie neque tempor. Aliquam condimentum nulla non congue sollicitudin"
            )
            list += item
        }

        return list
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MAIN_ACTIVITY_FRUIT_RESULT_CODE) {
            val fruit: FruitData? = data?.getParcelableExtra<FruitData>(MAIN_ACTIVITY_FRUIT_ID)

            if (fruit != null) {
                addFruit(fruit)
            }
        } else if (requestCode == DETAIL_FRUIT_ACTIVITY) {
            val position: Int? = data?.getIntExtra(MAIN_ACTIVITY_POSITION_ID, 0)

            if (position != null) {
                removeFruit(position)
            }
        }
    }

    fun goToInsertFruitActivity(view: View) {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun addFruit(fruit: FruitData) {
        if (fruit != null) {
            baseList.add(fruit)
            originalList.add(fruit)
        }


        adapter.notifyDataSetChanged()
    }

    private fun removeFruit(index: Int) {
        baseList.removeAt(index)
        originalList.removeAt(index)

        adapter.notifyDataSetChanged()
    }

    private fun filterRepeatedName(){
        println("DEBUG $originalList")

        val notRepetedList = originalList.toSet().toList()

        baseList.removeAll(baseList)
        baseList.addAll(notRepetedList)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        val clickedItem: FruitData = baseList[position]
        val intent = Intent(this@MainActivity, FruitActivity::class.java)

        intent.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_ID, clickedItem)
        intent.putExtra(MainActivity.MAIN_ACTIVITY_POSITION_ID, position)
        startActivityForResult(intent, DETAIL_FRUIT_ACTIVITY)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_filter -> {
            val builder = AlertDialog.Builder(this)
            val options = arrayOf(getString(R.string.filter_lexical_order), getString(R.string.filter_insertion_order))

            builder.setTitle(getString(R.string.filter_fruits))
            builder.setSingleChoiceItems(options, selectedOption, DialogInterface.OnClickListener { dialog, which ->
                selectedOption = which
            })

            val customDialog: View = layoutInflater.inflate(
                R.layout.custom_dialog, // Custom view/ layout
                null, // Root layout to attach the view
                false // Attach with root layout or not
            )

            var switch = customDialog.findViewById<Switch>(R.id.fruits_same_name)
            switch.isChecked = fruitsWithSameName

            builder.setView(customDialog)

            builder.setPositiveButton(
                getString(R.string.ok),
                DialogInterface.OnClickListener { dialog, which ->
                    fruitsWithSameName = switch.isChecked()

                    if (!fruitsWithSameName){
                        filterRepeatedName()
                    }else
                    {
                        baseList.removeAll(baseList)
                        baseList.addAll(originalList)
                        adapter.notifyDataSetChanged()
                    }
                })

            builder.show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}