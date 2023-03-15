package com.incava.ex81webservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.incava.ex81webservice.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            btn.setOnClickListener{clickBtn()}
            btn2.setOnClickListener{clickBtn2()}
        }

    }

    private fun clickBtn() {
        Thread{
            kotlin.run {
                val address = "http://incava.dothome.co.kr/"
                val url = URL(address)
                val ins : InputStream = url.openStream()
                val isr = InputStreamReader(ins)
                val reader = BufferedReader(isr)
                val buffer = StringBuilder()
                while (true){
                    var line = reader.readLine() ?: break
                    buffer.append(line+"\n")
                }
                runOnUiThread { binding.tv.text = buffer}
            }
        }.start()
    }

    private fun clickBtn2(){
                val address = "http://incava.dothome.co.kr/newyork.jpg"
                Glide.with(this)
                    .load(address)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.iv)
    }
}