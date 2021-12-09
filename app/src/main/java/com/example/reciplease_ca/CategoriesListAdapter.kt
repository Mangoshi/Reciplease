package com.example.reciplease_ca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reciplease_ca.databinding.ListItemBinding
import com.example.reciplease_ca.models.Category
import com.bumptech.glide.Glide

// Reference to the Categories List is passed in during initialisation
class CategoriesListAdapter(
    private val categoriesList: List<Category>,
    private val listener: ListItemListener,
) :

    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    // "inner" so it can access the parent class private properties
    // This class receives a reference to the root view
    // This gives it access to all the views
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    // Once the ViewHolder is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate data over layoutInflater
        val inflater = LayoutInflater.from(parent.context)
        // Inflate using the list item container from res/layout
        val view = inflater.inflate(R.layout.list_item, parent, false)
        // Return the altered ViewHolder
        return ViewHolder(view)
    }


    // onBindViewHolder is called by RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Defining variable for category from list
        val category = categoriesList[position]
        with(holder.binding) {
            // Set itemText to the strCategory (Category Name) string from the response
            itemText.text = category.strCategory
            // Using glide to set the images
            Glide.with(root)
                // .load to set the URL to load from
                .load(category.strCategoryThumb)
                // .into to set where it goes in the layout
                .into(itemImage)
            // Listener for clicks
            root.setOnClickListener {
                // run onItemClick and pass ID and name
                listener.onItemClick(category.idCategory, category.strCategory)
            }
        }
    }

    // Function for getting the size of the list in individual categories
    override fun getItemCount() = categoriesList.size

    // Listener for the list items
    interface ListItemListener {
        // onItemClick takes ID and name
        fun onItemClick(
            categoryId: Int,
            categoryName: String
        )
    }
}