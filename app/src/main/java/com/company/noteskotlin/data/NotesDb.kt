package com.company.noteskotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.company.noteskotlin.todo.TodoDao
import com.company.noteskotlin.todo.TodoEntity

@Database(
    entities = [
        Notes::class,
        TodoEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class NotesDb : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    abstract fun todoDao(): TodoDao

    companion object {

        @Volatile
        private var INSTANCE: NotesDb? = null

        fun database(context: Context): NotesDb {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDb::class.java,
                    "notes_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}