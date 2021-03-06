package com.example.reciplease_ca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reciplease_ca.databinding.ListItemBinding
import com.example.reciplease_ca.models.Meal

// Reference to the Meals List is passed in during initialisation
class MealsListAdapter(
    private val mealsList: List<Meal>,
    private val listener: ListItemListener
) : RecyclerView.Adapter<MealsListAdapter.ViewHolder>() {
    // "inner" so it can access the parent class private properties
    // This class receives a reference to the root view
    // This gives it access to all the views
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealsList[position]
        with(holder.binding) {
            itemText.text = meal.strMeal
            Glide.with(root)
                .load(meal.strMealThumb)
                .into(itemImage)
            root.setOnClickListener {
                listener.onItemClick(
                    meal.idMeal,
                    meal.strMeal
                )
            }
        }
    }

    override fun getItemCount() = mealsList.size

    interface ListItemListener {
        fun onItemClick(
            mealId: Int,
            mealName: String
        )
    }
}