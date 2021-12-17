package com.example.memesland

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loader()
    }

    private fun loader()
    {var image1=findViewById<ImageView>(R.id.imgmeme)
        var pb=findViewById<ProgressBar>(R.id.pb)
        pb.visibility= View.VISIBLE
// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                pb.visibility= View.GONE
             val url=response.getString("url")
                Glide.with(this).load(url).into(image1)
            },
            Response.ErrorListener {
                Toast.makeText(this,"Check Internet Connection!",Toast.LENGTH_SHORT).show()
            })
// Add the request to the RequestQueue.
        queue.add((jsonObjectRequest))
    }

    fun next(view: android.view.View) {
        loader()
    }
    fun share(view: android.view.View) {
        var currentFocus="https://i.redd.it/6ui8h33d9s581.gif"
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hey Checkout this meme, I got from reddit $currentFocus")
        val chooser = Intent.createChooser(intent,"Share this meme using...")
        startActivity(chooser)
    }
}