package com.ashkanpro.batterymanager.Activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashkanpro.batterymanager.databinding.ActivityMainBinding
import com.ashkanpro.batterymanager.R


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root


        binding.bottomNavigationView.setOnNavigationItemReselectedListener {

            if (it.itemId == R.id.list_app){

                startActivity(Intent(this@MainActivity, UsageBatteryActivity::class.java))
            }

            true
        }
        setContentView(view)

        registerReceiver(batteryInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

    }

    var batteryInfoReceiver : BroadcastReceiver = object : BroadcastReceiver(){
                @SuppressLint("SetTextI18n")
                override fun onReceive(context: Context, intent: Intent) {

                   val batteryLevel =   intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)


                    if (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0)==0){
                        binding.txtPlug.text = "Plug-out"

                    }else{
                        binding.txtPlug.text ="Plug-in"
                    }
                    binding.txtTemperature.text = (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0)/10).toString()+"°C"
                    binding.txtVoltage.text =   (intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0)/1000).toString()+" Volt"
                    binding.txtTechnology.text = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)


                    binding.circularProgressBar.progressMax = 100f
                    binding.circularProgressBar. setProgressWithAnimation(batteryLevel.toFloat())
                    binding.txtCharge.text = batteryLevel.toString()+"%"
        }
    }
}