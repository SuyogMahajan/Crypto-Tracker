package com.focus.cryptotracker.ui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.focus.cryptotracker.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val i = Intent(this,MainActivity::class.java)

        if(checkSelfPermission(android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.INTERNET),123)
        }

        if(android.os.Build.VERSION.SDK_INT >= 28 )
        Handler.createAsync(Looper.myLooper()!!).postDelayed(
            {startActivity(i)},1500
        )
        else
            startActivity(i)

    }
}