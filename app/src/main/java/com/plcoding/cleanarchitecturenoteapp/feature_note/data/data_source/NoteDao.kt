package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao //Data Access Object. which is responsible for handling database operations.
interface NoteDao {

    // This function gets all the notes from the "note" table in the database.
    // It returns a Flow, which is like a stream that updates when the data changes.
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>
    // Flow is a reactive stream that updates when data changes, used here to observe changes in the notes list.

    // This function gets a single note from the database based on its id.
    // It's a "suspend" function, which means it's designed to work with coroutines and can be paused.
    // It returns the Note if found, or null if the note with the given id doesn't exist.
    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int):  Note?

    // This function inserts a new note into the database.
    // If a note with the same ID already exists, it will be replaced (onConflict = REPLACE).
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(note: Note)

    // This function deletes a specific note from the database.
    @Delete
    suspend fun deleteNode(note: Note)

    /*
    A "suspend" function pauses only the function itself, not the whole app.
    It allows long-running tasks like database queries to happen in the background,
    while the rest of the app (like the UI) keeps running normally.
    The function will resume once the data is ready, but nothing else in the app is blocked.
    This helps keep the app responsive and smooth.
     */

}