package com.mindorks.framework.mvvm.repository

import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

//@RunWith(RobolectricTestRunner::class)
fun main() {
    val url = "localhost:3000/trainings"

    val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
        Response.Listener { response ->
           // textView.text = "Response: %s".format(response.toString())
            print("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr\n")
        },
        Response.ErrorListener { error ->
            print("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee\n")
        }
    )

// Access the RequestQueue through your singleton class.
   // MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

}