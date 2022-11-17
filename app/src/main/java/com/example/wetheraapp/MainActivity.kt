package com.example.wetheraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wetheraapp.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "6d9ffdcd60a14fb999d93727221711"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.bGet.setOnClickListener {
            getResult("Moscow")

        }
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
        url,
            {
                    response ->
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