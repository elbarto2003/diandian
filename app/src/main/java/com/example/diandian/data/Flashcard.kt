package com.example.diandian.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// okie why @Entity and why data infront of class .
@Entity(tableName = "flashcards")
data class Flashcard(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val phrase: String,
    val translation: String
)

