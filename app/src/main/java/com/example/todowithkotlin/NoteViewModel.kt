package com.example.todowithkotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application){
     val allNote: LiveData<MutableList<Note>>
     val repository: NoteRepository

    init {
        val dao = NoteDatabase.getInstance(application).getNotesDao()
        repository = NoteRepository(dao)
        allNote = repository.allnotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }
    fun  addNote(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
}