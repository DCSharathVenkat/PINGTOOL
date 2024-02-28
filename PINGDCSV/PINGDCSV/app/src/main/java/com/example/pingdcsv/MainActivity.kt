package com.example.pingdcsv
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage)

        val pingToolButton = findViewById<Button>(R.id.pingToolButton)
        pingToolButton.setOnClickListener{
            val intent = Intent(this,secondpage::class.java)
            startActivity(intent)

        }
    }
}
