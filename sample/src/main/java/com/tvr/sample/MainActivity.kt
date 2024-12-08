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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var  internetChecker: InternetConnectionChecker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         internetChecker = InternetConnectionChecker(this)


        binding.checkConnectivityBt.setOnClickListener {
            checkConnectivity()
        }

        binding.checkCapabilityBt.setOnClickListener {
            checkConnectivity()
        }

        checkConnectivity()

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
                    Toast.makeText(this@MainActivity,getString(R.string.connected),Toast.LENGTH_SHORT).show()

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
                    Toast.makeText(this@MainActivity,getString(R.string.connected_and_able_to_access_internet),Toast.LENGTH_SHORT).show()

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
                    Toast.makeText(this@MainActivity,getString(R.string.connected_but_not_able_to_access_internet),Toast.LENGTH_SHORT).show()

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
                    Toast.makeText(this@MainActivity,getString(R.string.connectivity_lost),Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}