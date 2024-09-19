package com.example.diandian.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Flashcard::class], version = 1, exportSchema = false)
abstract class FlashcardDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao

    companion object {
        @Volatile
        private var Instance: FlashcardDatabase? = null

        fun getDatabase(context: Context):FlashcardDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashcardDatabase::class.java,
                    "flashcard_database"
                ).build()
                Instance = instance
                instance
            }
        }
    }
}