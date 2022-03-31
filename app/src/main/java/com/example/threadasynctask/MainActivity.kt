package com.example.threadasynctask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btnStart)
        val edNumber = findViewById<EditText>(R.id.edNumber)
        val edMaxNumber = findViewById<EditText>(R.id.edMaxNumber)

        btn.setOnClickListener {
            if ((edNumber.text.isEmpty() || edNumber.text.toString().toInt()  > 40)
                || ( edMaxNumber.text.isEmpty() || edMaxNumber.text.toString().toInt() > 50)) {
                edNumber.error = ("The number should not exceed 40")
                edMaxNumber.error = ("The number should not exceed 50")

            }  else {
                val i = Intent(this, Res::class.java)
                i.putExtra("num", edNumber.text.toString().toInt())
                i.putExtra("maxNum", edMaxNumber.text.toString().toInt())
                startActivity(i)
            }
        }

        btn.setOnClickListener {
            if (edNumber.text.isEmpty() || edNumber.text.toString().toInt()  > 40) {
                edNumber.error = ("The number should not exceed 40")
            }
            else if ( edMaxNumber.text.isEmpty() || edMaxNumber.text.toString().toInt() > 50)
                { edMaxNumber.error = ("The number should not exceed 50") }
            else {
                val i = Intent(this, Res::class.java)
                i.putExtra("num", edNumber.text.toString().toInt())
                i.putExtra("maxNum", edMaxNumber.text.toString().toInt())
                startActivity(i)
            }

        }
    }
}