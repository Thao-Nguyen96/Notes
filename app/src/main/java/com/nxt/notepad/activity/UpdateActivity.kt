package com.nxt.notepad.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.nxt.notepad.R
import com.nxt.notepad.model.Note
import com.nxt.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val note = intent.getSerializableExtra("note") as Note

        edt_title_update.setText(note.title)
        edt_content_update.setText(note.content)

        tv_update_save.setOnClickListener {
            note.title = edt_title_update.text.toString()
            note.content = edt_content_update.text.toString()

            noteViewModel.updateNote(note)
            finish()
        }

        tv_update_delete.setOnClickListener {

            val builder = AlertDialog.Builder(this)

            builder.setTitle("Delete Note")
            builder.setMessage("Ban co muon xoa khong ?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("yes") { dialog, which ->
                noteViewModel.deleteNote(note)
                dialog.dismiss()
                finish()
            }
            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}