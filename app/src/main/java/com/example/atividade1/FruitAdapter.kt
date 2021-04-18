package com.example.atividade1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.fruit.view.*


class FruitAdapter(private val fruitList: List<FruitData>) : RecyclerView.Adapter<FruitAdapter.FruitHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitHolder {
        val fruitView = LayoutInflater.from(parent.context).inflate(R.layout.fruit, parent, false)

        return FruitHolder(fruitView)
    }

    override fun onBindViewHolder(holder: FruitHolder, position: Int) {
        val currentItem = fruitList[position]

        holder.imageView.setImageResource(currentItem.image)
        holder.titleView.text = currentItem.name
        holder.descriptionView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    class FruitHolder(fruitView: View): RecyclerView.ViewHolder(fruitView) {
        val imageView: ImageView = fruitView.fruit_image
        val titleView: TextView = fruitView.fruit_title
        val descriptionView: TextView = fruitView.fruit_description
    }
}