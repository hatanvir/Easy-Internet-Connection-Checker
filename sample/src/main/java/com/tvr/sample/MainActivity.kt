package com.tvr.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tvr.internetConnectionChecker.InternetConnectionChecker
import com.tvr.internetConnectionChecker.listeners.NetworkCapabilityListener
import com.tvr.internetConnectionChecker.listeners.NetworkConnectivityListener
import com.tvr.internetConnectionChecker.listeners.NetworkStatusListener
import com.tvr.sample.databinding.ActivityMainBinding
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Semaphore
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var  internetChecker: InternetConnectionChecker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         internetChecker = InternetConnectionChecker(this)


        binding.checkConnectivityBt.setOnClickListener {
            val connectivity = internetChecker.isConnected()
            Log.d("STATUS","CONNECTIVITY $connectivity")
            Toast.makeText(this,"CONNECTIVITY $connectivity",Toast.LENGTH_SHORT).show()
        }

        binding.checkCapabilityBt.setOnClickListener {
            val capability = internetChecker.isCapable()
            checkCapability()
            Log.d("STATUS","CAPABILITY $capability")
            Toast.makeText(this,"CAPABILITY $capability",Toast.LENGTH_SHORT).show()
        }

        checkConnectivity()

    }

    private fun checkCapability(): Boolean {
        val semaphore = Semaphore(0)
        var status = false

        thread {
            try {
                Socket().use { socket ->
                    socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)//googles public DNS
                    Log.d("CONNNNN","connected")
                    status = true
                }
            } catch (_: Exception) {
                status = false
            }
            semaphore.release()
        }

        semaphore.acquire()
        return status

    }

    private fun checkConnectivity() {
        internetChecker.getStatus(object : NetworkStatusListener {
            override fun connected() {
                Log.d("STATUS", "CONNECTED")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.teal_700
                        )
                    )
                    binding.networkStatusTv.text = getString(R.string.connected)
                }
            }

            override fun capable() {
                Log.d("STATUS", "CONNECTED_AND_CAPABLE")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.teal_700
                        )
                    )
                    binding.networkStatusTv.text =
                        getString(R.string.connected_and_able_to_access_internet)

                }
            }

            override fun notCapable() {
                Log.d("STATUS", "NOT_CAPABLE")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.red
                        )
                    )
                    binding.networkStatusTv.text =
                        getString(R.string.connected_but_not_able_to_access_internet)

                }
            }

            override fun notConnected() {
                Log.d("STATUS", "NOT_CONNECTED")
                runOnUiThread {
                    binding.wifiIm.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.red
                        )
                    )
                    binding.networkStatusTv.text = getString(R.string.connectivity_lost)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        internetChecker.unRegister()
    }

}