package com.example.wd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    var currDegree : Float = 0f
    var currSteerAngle : Float = 0f
    var currSpeed : Float = 125f
    private val step : Float = 1.14f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val database = FirebaseDatabase.getInstance("https://wirin-obd-default-rtdb.asia-southeast1.firebasedatabase.app//").reference
        database.child("test").get().addOnSuccessListener { it ->
            val msg = it.value.toString()
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }

        val startSim = findViewById<Button>(R.id.startSim)
        val needle = findViewById<ImageView>(R.id.needle)
        val battery  = findViewById<TextView>(R.id.batteryVal)
        val rpm = findViewById<TextView>(R.id.rpmVal)
        val temperature = findViewById<TextView>(R.id.tempVal)
        val distance = findViewById<TextView>(R.id.distVal)
        val speedKMH = findViewById<TextView>(R.id.kmhVal)
        val speedMPH = findViewById<TextView>(R.id.mphVal)
        val steerVal = findViewById<TextView>(R.id.steerVal)
        val mtempVal = findViewById<TextView>(R.id.mtempVal)
        val btempVal = findViewById<TextView>(R.id.btempVal)
        val steering = findViewById<ImageView>(R.id.steering)

        database.child("1").get().addOnSuccessListener {
            var data = it.child("battery").value.toString(); battery.text = data
            var steerAngle = ""
            data = it.child("rpm").value.toString(); rpm.text = data
            data = it.child("temperature").value.toString(); temperature.text = data
            data = it.child("distance").value.toString(); distance.text = data
            steerAngle = it.child("steer").value.toString(); steerVal.text = "$steerAngle deg"
            data = it.child("mtemp").value.toString(); mtempVal.text = "$data C"
            data = it.child("btemp").value.toString(); btempVal.text = "$data F"
            data = it.child("speed").value.toString()
            updateMeter(data.toFloat(),needle)
            updateSteering(steerAngle.toFloat(),steering)
            var inMPH = data; data = data.toFloat().roundTo(2).toString()+" km/h";
            speedKMH.text = data
            inMPH = (inMPH.toFloat() * 0.621371f).roundTo(2).toString()
            inMPH += " mph"; speedMPH.text = inMPH
        }

        // reference : 80mph is 0 degrees
        // step : 125/110 ~ 1.14
        startSim.setOnClickListener {
            var time : Long = 800
            for(i in 2..10) {
                Timer().schedule(timerTask {
                    database.child("$i").get().addOnSuccessListener {
                        var data = it.child("battery").value.toString(); battery.text = data
                        var steerAngle = ""
                        data = it.child("rpm").value.toString(); rpm.text = data
                        data = it.child("temperature").value.toString(); temperature.text = data
                        data = it.child("distance").value.toString(); distance.text = data
                        steerAngle = it.child("steer").value.toString(); steerVal.text = "$steerAngle deg"
                        data = it.child("mtemp").value.toString(); mtempVal.text = "$data C"
                        data = it.child("btemp").value.toString(); btempVal.text = "$data F"
                        data = it.child("speed").value.toString()
                        updateMeter(data.toFloat(),needle)
                        updateSteering(steerAngle.toFloat(),steering)
                        var inMPH = data; data = data.toFloat().roundTo(2).toString()+" km/h";
                        speedKMH.text = data
                        inMPH = (inMPH.toFloat() * 0.621371f).roundTo(2).toString()
                        inMPH += " mph"; speedMPH.text = inMPH
                    }
                },time)
                time+=800
            }
            Timer().schedule(timerTask {
                database.child("1").get().addOnSuccessListener {
                    var data = it.child("battery").value.toString(); battery.text = data
                    var steerAngle = ""
                    data = it.child("rpm").value.toString(); rpm.text = data
                    data = it.child("temperature").value.toString(); temperature.text = data
                    data = it.child("distance").value.toString(); distance.text = data
                    steerAngle = it.child("steer").value.toString(); steerVal.text = "$steerAngle deg"
                    data = it.child("mtemp").value.toString(); mtempVal.text = "$data C"
                    data = it.child("btemp").value.toString(); btempVal.text = "$data F"
                    data = it.child("speed").value.toString()
                    updateMeter(data.toFloat(),needle)
                    updateSteering(steerAngle.toFloat(),steering)
                    var inMPH = data; data = data.toFloat().roundTo(2).toString()+" km/h";
                    speedKMH.text = data
                    inMPH = (inMPH.toFloat() * 0.621371f).roundTo(2).toString()
                    inMPH += " mph"; speedMPH.text = inMPH
                }
            },time)
        }
//        var sp : Float = 120f
//        button.setOnClickListener {
//            updateMeter(sp,needle)
//            sp-=30
//        }
//        button.performClick()

        val buttonClick = findViewById<Button>(R.id.button2)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Stats::class.java)
            startActivity(intent)
        }
    }

    private fun updateSteering(newAngle : Float, steering : ImageView) {
        val rotateAnim = RotateAnimation(
            currSteerAngle,
            newAngle,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f)
        currSteerAngle = newAngle
        rotateAnim.duration = 800
        rotateAnim.fillAfter = true
        steering.startAnimation(rotateAnim)
    }

    private fun updateMeter(newSpeed : Float, needle : ImageView) {
        var newDegree : Float = 0f
        newDegree = if(currSpeed>newSpeed) {
            currDegree - (currSpeed-newSpeed)/step
        } else {
            currDegree + (newSpeed-currSpeed)/step
        }
        val rotateAnim = RotateAnimation(
            currDegree,
            newDegree,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,1f)
        currDegree = newDegree
        currSpeed = newSpeed
        rotateAnim.duration = 800
        rotateAnim.fillAfter = true
        needle.startAnimation(rotateAnim)
    }

    private fun Float.roundTo(n : Int) : Float {
        return "%.${n}f".format(Locale.ENGLISH,this).toFloat()
    }
}