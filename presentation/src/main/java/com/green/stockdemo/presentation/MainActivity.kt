package com.green.stockdemo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.green.domain.entity.SubscribeSource
import com.green.stockdemo.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var subscribeSource: SubscribeSource

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = StockFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frm_main, fragment)
            .commit()

        findViewById<Button>(R.id.testBtn).setOnClickListener {
//            val fragment = StockFragment.newInstance()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frm_main, fragment)
//                .commit()
        }

        compositeDisposable.add(subscribeSource.buyStatusPublish.subscribe {
            Log.d("Green", "Green test next page 2")
            val fragment = PortfolioFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frm_main, fragment)
                .commit()
        })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}