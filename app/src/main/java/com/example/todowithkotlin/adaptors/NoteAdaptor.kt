package com.example.todowithkotlin.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todowithkotlin.Note
import com.example.todowithkotlin.R

class NoteAdaptor() : RecyclerView.Adapter<NoteAdaptor.NoteViewHolder>(){

    private val noteList: MutableList<Note> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
    }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.textTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         val textTitle = itemView.findViewById(R.id.text_view_title) as TextView
          val textViewDescription = itemView.findViewById(R.id.text_view_description) as TextView
         val textViewPriority = itemView.findViewById(R.id.text_view_priority) as TextView
    }

}