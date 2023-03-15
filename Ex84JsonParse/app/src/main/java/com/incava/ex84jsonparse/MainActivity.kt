package com.incava.ex84jsonparse

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.incava.ex84jsonparse.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var list = ArrayList<ItemVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn.setOnClickListener { clickBtn() }
            btn2.setOnClickListener { clickBtn2() }
        }

    }

    private fun clickBtn(){
        val assetManager = assets as AssetManager
        val inputStream = assetManager.open("aaa.json")
        val isr = InputStreamReader(inputStream)
        val reader = BufferedReader(isr)
        val buffer = StringBuffer()
        while(true){
            val line = reader.readLine()?:break
            buffer.append(line+"\n")
        }
        val jsonStr = buffer.toString()
        binding.tv.text = jsonStr
        //json문자열을 분석
        val jo = JSONObject(jsonStr)
        val name = jo.getString("name")
        val message = jo.getString("msg")
        val age = jo.getInt("age")
        val address = jo.getJSONObject("address")
        buffer.append(name)
        buffer.append(message)
        buffer.append(age)
        buffer.append(address)

    }

    fun clickBtn2(){
        val assetManager = assets as AssetManager
        val inputStream = assetManager.open("bbb.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)

        val buffer = StringBuffer()
        while(true){
            val line = bufferedReader.readLine()?:break
            buffer.append(line+"\n")
        }
        binding.tv.text = buffer.toString()

        val jsonArray = JSONArray(buffer.toString())
        for(i in 0 until jsonArray.length()){
             val jo = jsonArray.getJSONObject(i)
             val no = jo.getInt("no")
            val name = jo.getString("name")
            val msg = jo.getString("msg")
            list.add(ItemVO(no,name,msg))
        }
        binding.tv.text = list.size.toString()
    }


}