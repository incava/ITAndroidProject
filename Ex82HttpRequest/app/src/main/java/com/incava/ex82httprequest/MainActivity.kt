package com.incava.ex82httprequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.incava.ex82httprequest.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnGet.setOnClickListener { clickGet() }
            btnPost.setOnClickListener { clickPost() }
            btnLoad.setOnClickListener { clickLoad() }
        }
    }
    private fun clickLoad(){
        var intent = Intent(this,BoardActivity::class.java)
        startActivity(intent)
    }

    private fun clickGet() {
        thread {
            run {
                var name: String = binding.etName.text.toString()
                name = URLEncoder.encode(name, "utf-8")
                var message: String = binding.etMsg.text.toString()
                message = URLEncoder.encode(message, "utf-8")
                val serverAddress = "http://incava.dothome.co.kr/Android/getText.php"
                var getAddress = "$serverAddress?name=$name&msg=$message"// 한글 사용을 위해 암호화.
                val url = URL(getAddress)
                var connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.doInput = true
                connection.useCaches = false
                val inputStream = connection.inputStream
                val isr = InputStreamReader(inputStream)
                var reader = BufferedReader(isr)
                var buffer = StringBuffer()
                while (true) {
                    var line: String? = reader.readLine() ?: break
                    buffer.append(line + "\n")
                }
                runOnUiThread { binding.tv.text = buffer.toString() }
                //connection.disconnect()
            }
        }
    }

    private fun clickPost() {
        thread {
            run {
                var name: String = binding.etName.text.toString()
                name = URLEncoder.encode(name, "utf-8")
                var message: String = binding.etMsg.text.toString()
                message = URLEncoder.encode(message, "utf-8")
                val serverAddress = "http://incava.dothome.co.kr/Android/postText.php"
                var data = "name=$name&msg=$message"
                val url = URL(serverAddress)
                var connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doInput = true
                connection.useCaches = false
                var stream = connection.outputStream
                var writer = OutputStreamWriter(stream)
                writer.write(data, 0, data.length)
                writer.flush()
                writer.close()

                val inputStream = connection.inputStream
                val isr = InputStreamReader(inputStream)
                val reader = BufferedReader(isr)
                var buffer = StringBuffer()
                while (true) {
                    var line: String? = reader.readLine() ?: break
                    buffer.append(line + "\n")
                }
                runOnUiThread { binding.tv.text = buffer.toString() }
            }
        }
    }

}