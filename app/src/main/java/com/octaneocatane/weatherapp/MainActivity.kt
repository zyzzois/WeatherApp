package com.octaneocatane.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.octaneocatane.weatherapp.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "be8f4a5e6d3d4498b73182435222306"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonGet.setOnClickListener {
            getResult("London")
        }
    }
    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no\n"

        //новая очередь запроса
        val queue = Volley.newRequestQueue(this)
        //создаем новый запрос
        val stringRequest = StringRequest(Request.Method.GET, url, //мы хотим получить поэтому используем метод GET
            {
                response->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )
        queue.add(stringRequest)
    }
}