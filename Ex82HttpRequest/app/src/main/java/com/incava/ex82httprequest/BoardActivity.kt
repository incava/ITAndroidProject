package com.incava.ex82httprequest

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.incava.ex82httprequest.databinding.ActivityBoardBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class BoardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardBinding
    private var items = ArrayList<ItemVO>()
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadDB()
        myAdapter = MyAdapter(items)
        binding.recycler.apply {
            adapter = myAdapter
        }

    }

    private fun loadDB() {
        thread { // 람다로 사용되어 start가 필요없음. 객체로 만들지 않았으니까.
            kotlin.run {
                val serverAddr = "http://incava.dothome.co.kr/Android/loadDB.php"
                val url = URL(serverAddr)
                val connection = url.openConnection() as HttpURLConnection
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
//                runOnUiThread{ // 잘 나왔는지 확인용.
//                    AlertDialog.Builder(this).setMessage(buffer.toString()).show()
//                }
                var rows = buffer.split('&')
                Log.i("TAG","${rows.size}")
                for(row in rows){
                    val item = row.split(',')
                    if(item.size!=4) continue
                    val itemVO = ItemVO(item[0].toInt(),item[1],item[2],item[3])
                    items.add(itemVO)
                }
                runOnUiThread{myAdapter.notifyDataSetChanged()}
            }
        }
    }



}