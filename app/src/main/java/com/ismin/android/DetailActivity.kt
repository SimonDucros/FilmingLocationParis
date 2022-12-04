package com.ismin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

val CREATED_BOOK = "CREATED_BOOK";

class CreateBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        findViewById<Button>(R.id.a_create_book_btn_save).setOnClickListener(this::buildBookAndReturn)
    }

    private fun buildBookAndReturn(_v: View) {
        val title = findViewById<EditText>(R.id.a_create_book_edt_title).text.toString()
        val author = findViewById<EditText>(R.id.a_create_book_edt_author).text.toString()
        val date = findViewById<EditText>(R.id.a_create_book_edt_date).text.toString()
        val book = Book(title, author, date)
        val intent = Intent()
        intent.putExtra(CREATED_BOOK, book)
        setResult(RESULT_OK, intent)
        finish()
    }
}