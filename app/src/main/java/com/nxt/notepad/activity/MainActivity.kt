package com.nxt.notepad.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nxt.notepad.R
import com.nxt.notepad.adapter.NoteAdapter
import com.nxt.notepad.model.Note
import com.nxt.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerview()
        clickEvents()

    }

    private fun clickEvents() {
        btn_add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerview() {
        val adapter = NoteAdapter(this, onClick)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        noteViewModel.getAllNote().observe(this, Observer {
            println(it)
            adapter.setNote(it)
        })
    }

    private val onClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("note", it)
        startActivity(intent)
    }
}