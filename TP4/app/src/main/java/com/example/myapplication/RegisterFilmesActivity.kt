package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RegisterFilmesActivity : AppCompatActivity() {

    lateinit var bt_return: ImageView
    lateinit var bt_send: ImageView

    lateinit var edit_filme: EditText
    lateinit var edit_diretor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerfilmes)

        Components()
    }

    fun Components() {

        bt_return = findViewById(R.id.bt_return)
        bt_send = findViewById(R.id.bt_send)

        edit_filme = findViewById(R.id.edit_filme)
        edit_diretor = findViewById(R.id.edit_diretor)

        bt_return.setOnClickListener {
            onClick(it as ImageView)
        }

        bt_send.setOnClickListener {
            onClick(it as ImageView)
        }
    }

    fun onClick(view: ImageView) {

        if (view.id == R.id.bt_return) {

            finish()

        } else if (view.id == R.id.bt_send) {

            val titulo = edit_filme.text.toString()
            val diretor = edit_diretor.text.toString()

            if (titulo.isNotEmpty() && diretor.isNotEmpty()) {

                val intent = Intent()

                intent.putExtra("titulo", titulo)
                intent.putExtra("diretor", diretor)

                setResult(Activity.RESULT_OK, intent)

                finish()
            }
        }
    }
}
