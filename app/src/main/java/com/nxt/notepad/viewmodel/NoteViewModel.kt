package com.nxt.notepad.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nxt.notepad.database.NoteRepository
import com.nxt.notepad.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {

    private var repository: NoteRepository = NoteRepository(application)

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = repository.getAllNote()

    class NoteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                return NoteViewModel(application) as T
            }
            throw  IllegalArgumentException("unable construct viewModel")
        }

    }
}