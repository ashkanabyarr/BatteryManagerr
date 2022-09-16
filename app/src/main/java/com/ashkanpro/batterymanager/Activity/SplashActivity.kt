package com.ashkanpro.batterymanager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashkanpro.batterymanager.databinding.ActivitySplashBinding
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


            var textArray = arrayOf("Make Your Battery powerful",
                "Make Your Battery Safe",
                "Make Your Battery Faster",
                "Notify when your phone is full Charge")
        for (i in 1..4){
            helptextGenerator((i*1000).toLong(),textArray[i-1])
        }

        Timer().schedule(timerTask {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            },5000)
    }

   private fun helptextGenerator(delayTime:Long,helpText:String){
       Timer().schedule(timerTask {
           runOnUiThread(timerTask {
               binding.helpText.text = helpText
           })
       },delayTime)
   }
}