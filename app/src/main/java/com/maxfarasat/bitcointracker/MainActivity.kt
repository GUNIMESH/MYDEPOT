package com.maxfarasat.bitcointracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.load -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun loadBitCoinPrice(){
        val URL = "https://api.coindesk.com/v1/bpi/currentprice.json"
        val okHttpClient = OkHttpClient.Builder()
            .build()

    }

}
