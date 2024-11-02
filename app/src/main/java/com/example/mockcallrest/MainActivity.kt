package com.example.mockcallrest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = retrofit.builder.getData().enqueue(object : Callback<ResponseData>{
            override fun onResponse(p0: Call<ResponseData>, p1: Response<ResponseData>) {
                Toast.makeText(context,p1.body()?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(p0: Call<ResponseData>, p1: Throwable) {
                Toast.makeText(context,p1.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}

interface APIService{
    //https://run.mocky.io/v3/843b7e2b-f351-4683-baf8-d32f7ead73a9
    @POST("/v3/843b7e2b-f351-4683-baf8-d32f7ead73a9/")
    fun getData() : Call<ResponseData>
}

data class ResponseData(
        val message: String
)

object retrofit{
    val builder = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)

}