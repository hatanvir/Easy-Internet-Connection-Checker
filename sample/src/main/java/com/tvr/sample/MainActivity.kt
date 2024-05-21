package com.tvr.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tvr.internetConnectionChecker.InternetConnectionChecker
import com.tvr.internetConnectionChecker.NetWorkStatusListeners
import com.tvr.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val internetChecker = InternetConnectionChecker(this)

        Log.d("STATUS","CONNECTED_NET_SUCCESS_INITIAL"+internetChecker.checkNetwork())

        Log.d("STATUS","CONNECTED_NET_CAPABLE_SUCCESS_INITIAL"+internetChecker.checkCapableNetwork())

        internetChecker.getStatus(object : NetWorkStatusListeners {
            override fun onConnectionNetSuccess() {
                Log.d("STATUS","CONNECTED_NET_SUCCESS")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(ContextCompat.getColor(this@MainActivity,R.color.teal_700))
                    binding.networkStatusTv.text = "Connected"
                }
            }

            override fun onConnectionCapableNetSuccess() {
                Log.d("STATUS","CONNECTED_CAPABLE_NET_SUCCESS")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(ContextCompat.getColor(this@MainActivity,R.color.teal_700))
                    binding.networkStatusTv.text = "Connected and able to access internet"
                }
            }

            override fun onConnectionCapableNetFailed() {
                Log.d("STATUS","CONNECTED_CAPABLE_NET_FAILED")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(ContextCompat.getColor(this@MainActivity,R.color.red))
                    binding.networkStatusTv.text = "Connected but not able to access internet"
                }
            }

            override fun OnConnectionNetFailed() {
                Log.d("STATUS","CONNECTED_NET_FAILED")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(ContextCompat.getColor(this@MainActivity,R.color.red))
                    binding.networkStatusTv.text = "Connectivity Lost"
                }
            }
        })

    }

}