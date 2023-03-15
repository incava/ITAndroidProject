package com.incava.ex85gson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.incava.ex85gson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn.setOnClickListener { clickBtn() }
            btn2.setOnClickListener { clickBtn2() }
            btn3.setOnClickListener { clickBtn3() }
        }


    }

    private fun clickBtn(){
        var jsonStr = "{'name' : 'sam', 'age' : 20}"
        var gson = Gson()
        var person = gson.fromJson(jsonStr,Person::class.java)
        binding.tv.text = person.name
    }


    private fun clickBtn2(){
        val person = Person("Robin",25)
        val gson = Gson()
        val jsonStr = gson.toJson(person)
        binding.tv.text = jsonStr
    }

    private fun clickBtn3(){
        val jsonStr = "[{'name':'sam','age':20},{'name':'robin','age': 25}]"
        val gson = Gson()
        //val type = object : TypeToken<List<Person>>(){}.type
        val people : Array<Person> = gson.fromJson(jsonStr,Array<Person>::class.java)
            binding.tv.text = people.size.toString()
    }




}