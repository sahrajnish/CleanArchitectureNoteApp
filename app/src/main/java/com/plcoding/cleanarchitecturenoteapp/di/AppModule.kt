package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImp
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.DeleteNote
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotes
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository)
        )
    }
}

/*
* SingletonComponent means the objects created by this module will live for the entire
* life of the app (like a singleton). Hilt will create these objects only once,
* and they will be shared across the whole app.
*
* @InstallIn(SingletonComponent::class) ensures that the objects provided by
* AppModule are available as single instances throughout the whole app.
*
* You need @Module when Hilt doesn’t know how to create an object by itself.
* Some objects require special setup or need to be built in a specific way.

For example:

Objects from external libraries: If you need something from an external library
* (like a Retrofit instance for networking or Room database), Hilt doesn’t know how
* to create those by itself.

Complex objects: If an object needs special parameters or complex logic to create,
* you use @Module to tell Hilt how to build it.
*/