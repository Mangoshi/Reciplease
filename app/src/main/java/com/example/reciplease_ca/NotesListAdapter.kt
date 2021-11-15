package com.example.reciplease_ca

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.reciplease_ca.data.NoteEntity
import com.example.reciplease_ca.databinding.ListItemBinding

class NotesListAdapter(private val notesList: List<NoteEntity>) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    // "inner" so it can access the parent class private properties
    // This class receives a reference to the root view
    // This gives it access to all the views
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }
}