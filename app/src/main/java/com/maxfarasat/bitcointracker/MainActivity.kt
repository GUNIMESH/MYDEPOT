package com.maxfarasat.bitcointracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.GsonBuilder
import com.maxfarasat.bitcointracker.DataClasses.Entities
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadBitCoinPrice()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.load -> {
                loadBitCoinPrice()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun loadBitCoinPrice(){
        val URL = "https://api.coindesk.com/v1/bpi/currentprice.json"
        val okHttpClient = OkHttpClient.Builder()
            .build()
        progressBar.visibility = View.VISIBLE
        val request: Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {}
            override fun onResponse(call: Call?, response: Response?){
                val json = response?.body()?.string()
                val gson =  GsonBuilder().create()
                val BitCoinData = gson.fromJson(json, Entities::class.java)
                val rateUSD = BitCoinData.bpi.uSD.rateFloat.toString()
                val rateEURO = BitCoinData.bpi.eUR.rateFloat.toString()
                val ratePOUNDS = BitCoinData.bpi.gBP.rateFloat.toString()
                val latestTime = BitCoinData.time.updateduk
                val disClaimer = BitCoinData.disclaimer
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    bitcoinValues.text = "\n$$rateUSD\n€$rateEURO\n£$ratePOUNDS"
                    updatedTime.text = "BitCoin Price Updated on \n$latestTime"
                    message.text = "*Disclaimer: $disClaimer"
                }
            }
        })

    }

}
