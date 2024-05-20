package com.tvr.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tvr.internetConnectionChecker.InternetConnectionChecker
import com.tvr.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val internetChecker = InternetConnectionChecker(this)
        if(internetChecker.checkNetwork()){
            Toast.makeText(this,"Status first time"+internetChecker.checkNetwork(),Toast.LENGTH_SHORT).show()
        }

        internetChecker.getStatus { it->
            Toast.makeText(this, "Status Change$it",Toast.LENGTH_SHORT).show()
        }

    }
}