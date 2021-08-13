package com.nxt.notepad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nxt.notepad.R
import com.nxt.notepad.model.Note
import kotlinx.android.synthetic.main.item_layout.view.*

class NoteAdapter(
    private val context: Context,
    private val onClick: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes: List<Note> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNote(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTitle = itemView.tv_title
        private var tvContent = itemView.tv_content
        private var lnItem = itemView.ln_item

        fun onBind(note: Note) {
            tvTitle.text = note.title
            tvContent.text = note.content

            lnItem.setOnClickListener {
                onClick(note)
            }
        }
    }
}