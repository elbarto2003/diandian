package com.example.diandian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.diandian.data.Flashcard
import com.example.diandian.data.FlashcardDatabase
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = FlashcardDatabase.getDatabase(this)
        val flashcardDao = db.flashcardDao()

        setContent {
            Text("点点")
            var phrase by remember { mutableStateOf("") }
            var translation by remember { mutableStateOf("") }

            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = phrase,
                        onValueChange = { phrase = it },
                        label = { Text("Phrase") },
                        modifier = Modifier.fillMaxWidth()
                        )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = translation,
                        onValueChange = { translation = it },
                        label = { Text("Translation") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        if (phrase.isNotEmpty() && translation.isNotEmpty()) {
                            lifecycleScope.launch {
                                flashcardDao.insertFlashcard(
                                    Flashcard(phrase = phrase, translation = translation)
                                )
                            }
                            phrase = ""
                            translation = ""
                        }
                        }) {
                        Text("Add Flashcard")}
                    }
                }
            }
        }
    }
