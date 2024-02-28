package com.example.pingdcsv
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class thirdpage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirdpage)

        val results = intent.getStringArrayListExtra("PING_RESULTS")
        val durations = intent.getLongArrayExtra("PING_DURATIONS")
        displayResultsAndDurations(results, durations)


        val backToHomeButton: Button = findViewById(R.id.mmenu)
        backToHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val backButton: Button = findViewById(R.id.backh)
        backButton.setOnClickListener {
            val intent = Intent(this, secondpage::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun displayResultsAndDurations(results: List<String>?, durations: LongArray?) {
        if (results != null && durations != null && results.size == durations.size) {
            for (i in results.indices) {
                val result = results[i]
                val duration = durations[i]
                val resultTextView: TextView = findViewById(R.id.textv)
                resultTextView.append("Result: $result, Duration: $duration ms\n")
            }
        }
    }
}
