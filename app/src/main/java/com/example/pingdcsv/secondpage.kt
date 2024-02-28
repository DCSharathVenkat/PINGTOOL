@file:Suppress("DEPRECATION")

package com.example.pingdcsv
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.os.AsyncTask
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import android.widget.EditText
import android.content.Intent


@Suppress("DEPRECATION")
class secondpage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondpage)
        val ipEditText: EditText = findViewById(R.id.Input)
        val pingButton: Button = findViewById(R.id.PING)
        pingButton.setOnClickListener {
            val ipAddress = ipEditText.text.toString()
            PingTask { results, durations ->
                val intent = Intent(this, thirdpage::class.java)
                intent.putStringArrayListExtra("PING_RESULTS", ArrayList(results))
                intent.putExtra("PING_DURATIONS", durations.toLongArray())
                startActivity(intent)
            }.execute(ipAddress)
        }

        val backToHomeButton: Button = findViewById(R.id.BackButton)
        backToHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        }
    }
@Suppress("DEPRECATION")
class PingTask(private val callback: (List<String>, List<Long>) -> Unit) : AsyncTask<String, Void, Pair<List<String>, List<Long>>>() {
    override fun doInBackground(vararg params: String): Pair<List<String>, List<Long>> {
        val ipAddress = params[0]
        val pingResults = mutableListOf<String>()
        val durations = mutableListOf<Long>()

        try {
            for (i in 1..5) {
                val startTime = System.currentTimeMillis()
                val socket = Socket()
                socket.connect(InetSocketAddress(ipAddress, 80), 5000)
                socket.close()
                val endTime = System.currentTimeMillis()
                val duration = endTime - startTime
                pingResults.add("Ping $ipAddress")
                durations.add(duration)
            }
            return Pair(pingResults, durations)
        } catch (e: IOException) {
            return Pair(listOf("Error: ${e.message}"), emptyList())
        }
    }
    override fun onPostExecute(result: Pair<List<String>, List<Long>>) {
        callback(result.first, result.second)
    }
}

