package com.example.diandian.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Insert
    suspend fun insertFlashcard(flashcard: Flashcard)

    @Query("SELECT * FROM flashcards")
    fun getAllFlashcards(): Flow<List<Flashcard>>
}