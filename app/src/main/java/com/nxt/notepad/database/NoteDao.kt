package com.nxt.notepad.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nxt.notepad.model.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAll(): LiveData<List<Note>>

    //tất cả các truy xuất từ roomdatabase phải chạy luồng
    // khác(chạy bất đồng bộ) thì mới có thể lấy dữ liệu
    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}