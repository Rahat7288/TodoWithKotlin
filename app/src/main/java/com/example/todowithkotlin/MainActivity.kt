package com.example.todowithkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todowithkotlin.activites.AddEditActivity
import com.example.todowithkotlin.adaptors.NoteAdaptor
import com.example.todowithkotlin.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.properties.ReadWriteProperty

class MainActivity : AppCompatActivity() {
    @Suppress("UNCHECKED_CAST")
    private val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NoteViewModel(application) as T
        }
    }

    private var noteViewModel: NoteViewModel by noteViewModel{factory}




    //    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdaptor: NoteAdaptor
    private lateinit var addNoteButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNoteButton = findViewById(R.id.add_note_button)

        recyclerView = findViewById(R.id.recycle_view)
        noteAdaptor = NoteAdaptor()

        recyclerView.adapter = noteAdaptor
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[NoteViewModel :: class.java]

        noteViewModel.allNote.observe(this){
//            here we can add our data to our recycleView
//            list ->
            noteAdaptor.setNotes(it)
        }
        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Constants.REQUEST_CODE){
                val title = it.data?.getStringExtra(Constants.EXTRA_TITLE)
                val  description = it.data?.getStringExtra(Constants.EXTRA_DESCRIPTION)
                val priority = it.data?.getStringExtra(Constants.EXTRA_PRIORITY)

                val note = Note(title!!,description!!,priority!!)
                noteViewModel.addNote(note)
            }
        }
        addNoteButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            getResult.launch(intent)
        }
    }
}








