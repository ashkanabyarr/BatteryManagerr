package com.ashkanpro.batterymanager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashkanpro.batterymanager.Model.BatteryModel
import com.ashkanpro.batterymanager.R
import com.ashkanpro.batterymanager.adapter.BatteryUsageAdapter
import com.ashkanpro.batterymanager.databinding.ActivityMainBinding
import com.ashkanpro.batterymanager.databinding.ActivityUsageBatteryBinding
import com.ashkanpro.batterymanager.utils.BatteryUsage
import kotlin.math.roundToInt

class UsageBatteryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsageBatteryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsageBatteryBinding.inflate(layoutInflater)
        val view = binding.root

        binding.bottomNavigationView.setOnNavigationItemReselectedListener {

            if (it.itemId == R.id.icon_home){

                startActivity(Intent(this@UsageBatteryActivity, MainActivity::class.java))
            }

        }
        setContentView(view)

        val batteryUsage = BatteryUsage(this).getUsageStateList()

        val batteryPercentArray :MutableList<BatteryModel> = ArrayList()
        for (item in batteryUsage){
            if (item.totalTimeInForeground >0){
                val bm = BatteryModel()
                bm.packegeName = item.packageName
                bm.personUsage = (item.totalTimeInForeground.toFloat() / BatteryUsage(this).getTotalTime().toFloat()*100).toInt()
                batteryPercentArray += bm
            }
        }
        val adapter = BatteryUsageAdapter(this,batteryPercentArray,BatteryUsage(this).getTotalTime())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter



    }
}