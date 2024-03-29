package com.example.todowithkotlin.activites

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.view.get
import com.example.todowithkotlin.R
import com.example.todowithkotlin.R.id.save_menu_items
import com.example.todowithkotlin.utils.Constants

class AddEditActivity : AppCompatActivity() {
    private lateinit var editTextTitle: EditText
    private  lateinit var editTextDescription: EditText
    private lateinit var numberPicker: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPicker =findViewById(R.id.number_picker_priority)

        numberPicker.minValue = 0
        numberPicker.maxValue = 10
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }

    fun saveNote(){
        val title = editTextTitle.text.toString()
        val description = editTextDescription.text.toString()
        val priority = numberPicker.value

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this,"Please insert title and description", Toast.LENGTH_LONG).show()
            return
        }

        Intent().apply {
            putExtra(Constants.EXTRA_TITLE, title)
            putExtra(Constants.EXTRA_DESCRIPTION, description)
            putExtra(Constants.EXTRA_PRIORITY, priority)
        }
        finish()
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(save_menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_menu_items -> {
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}