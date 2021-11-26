package com.example.reciplease_ca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reciplease_ca.databinding.ListItemBinding
import com.example.reciplease_ca.models.Category

class CategoriesListAdapter(
    private val categoriesList: List<Category>,
    private val listener: ListItemListener
) :

    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

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
        val category = categoriesList[position]
        with(holder.binding) {
            mealText.text = category.strCategory
            root.setOnClickListener {
                listener.onItemClick(category.idCategory, category.strCategory, category.strCategoryDescription, category.strCategoryThumb)
            }
        }
    }

    override fun getItemCount() = categoriesList.size

    interface ListItemListener {
        fun onItemClick(
            categoryId: Int,
            categoryName: String,
            categoryDescription: String,
            strCategoryThumb: String
        )
    }
}