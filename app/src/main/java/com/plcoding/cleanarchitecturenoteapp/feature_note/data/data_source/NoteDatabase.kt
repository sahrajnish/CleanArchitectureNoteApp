package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note

// You need this class to set up the database and tell Room what to do.
// Interfaces can only define actions, but can’t set up or manage databases.
@Database(
    entities = [Note::class], // We're telling Room to create a table that will hold "Note" objects (your notes).
    version = 1 // The version of your database. Start with version 1. If you change
                // the database structure later, you’ll need to update this.
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao // This gives us access to NoteDao, which has the commands
                            // to add, delete, or get notes from the database.

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}