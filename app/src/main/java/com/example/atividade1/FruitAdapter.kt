package com.example.atividade1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.atividade1.data.FruitData
import kotlinx.android.synthetic.main.fruit.view.*


class FruitAdapter(private val fruitList: List<FruitData>, private val listener: OnItemClickListener) : RecyclerView.Adapter<FruitAdapter.FruitHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitHolder {
        val fruitView = LayoutInflater.from(parent.context).inflate(R.layout.fruit, parent, false)

        return FruitHolder(fruitView)
    }

    override fun onBindViewHolder(holder: FruitHolder, position: Int) {
        val currentItem = fruitList[position]

        holder.imageView.setImageURI(currentItem.image)
        holder.titleView.text = currentItem.name
        holder.descriptionView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    inner class FruitHolder(fruitView: View): RecyclerView.ViewHolder(fruitView), View.OnClickListener {
        val imageView: ImageView = fruitView.fruit_image
        val titleView: TextView = fruitView.fruit_title
        val descriptionView: TextView = fruitView.fruit_description

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)

            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}


