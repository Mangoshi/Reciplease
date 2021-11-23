package com.example.reciplease_ca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reciplease_ca.data.MealEntity
import com.example.reciplease_ca.databinding.ListItemBinding

class MealAdaptor(
    private val context: Context,
    private val meals: List<MealEntity>,
    private val listener: ListItemListener
) : RecyclerView.Adapter<MealAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = meals.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = meals[position]
//      holder.bind(meals[position])
        with(holder.binding) {
            mealName.text = meal.name
            root.setOnClickListener {
                listener.onItemClick(meal.id)
            }
        }
    }

    // "inner" so it can access the parent class private properties
    // This class receives a reference to the root view
    // This gives it access to all the views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    interface ListItemListener {
        fun onItemClick(noteId: Int)
    }
}