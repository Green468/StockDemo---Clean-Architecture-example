package com.green.stockdemo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.green.stockdemo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = StockFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frm_main, fragment)
            .commit()

        findViewById<Button>(R.id.testBtn).setOnClickListener {
            val fragment = StockFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frm_main, fragment)
                .commit()
        }
    }
}