package com.nxt.notepad.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.nxt.notepad.R
import com.nxt.notepad.model.Note
import com.nxt.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class AddActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        tv_add.setOnClickListener {
            val title = edt_title_add.text.toString()
            val content = edt_content_add.text.toString()

            noteViewModel.insertNote(Note(title, content))

            finish()
        }
    }
}