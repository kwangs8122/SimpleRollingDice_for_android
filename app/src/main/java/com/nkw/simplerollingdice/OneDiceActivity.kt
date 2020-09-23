package com.nkw.simplerollingdice

import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.nkw.simplerollingdice.common.BaseActivity
import kotlinx.android.synthetic.main.activity_one_dice.*
import java.lang.Thread.sleep
import java.util.*

class OneDiceActivity : BaseActivity(), View.OnClickListener, SensorEventListener {

    var arrayDiceImages: ArrayList<Int> = ArrayList<Int>()
    var mSensorManager: SensorManager? = null
    var mAccelerometer: Sensor? = null

    var mShakeTime: Long = 0
    val SHAKE_SKIP_TIME: Int = 800
    val SHAKE_THRESHOLD_GRAVITY: Float = 80.7F

    var mShakeCount: Int = 0

    var lastX: Float = 0.0F
    var lastY: Float = 0.0F
    var lastZ: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_dice)

        arrayDiceImages.add(R.drawable.one)
        arrayDiceImages.add(R.drawable.two)
        arrayDiceImages.add(R.drawable.three)
        arrayDiceImages.add(R.drawable.four)
        arrayDiceImages.add(R.drawable.five)
        arrayDiceImages.add(R.drawable.six)

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager?
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        btnStart.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onStop() {
        super.onStop()
        mSensorManager!!.unregisterListener(this)
    }

    override fun onClick(v: View?) {
//        TODO("Not yet implemented")

        when (v!!.id) {
            btnStart.id -> {
                setDice()
            }
            else -> {}
        }

    }

    fun setDice() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibe!!.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibe!!.vibrate(500)

            vibe!!.vibrate(longArrayOf(500, 1000, 500, 1000), -1)
        }
        val random: Random = Random()

        val diceNum: Int = random.nextInt(5) + 1

        imgView.setImageResource(arrayDiceImages[diceNum])
        tvTotal.text = (diceNum + 1).toString()
    }

    override fun onSensorChanged(e: SensorEvent?) {
//        TODO("Not yet implemented")
        if (e?.sensor!!.type == Sensor.TYPE_ACCELEROMETER) {
            var currentTime = System.currentTimeMillis()
            var gabOfTime = (currentTime - mShakeTime);
            if (gabOfTime > SHAKE_SKIP_TIME) {
                mShakeTime = currentTime;
                var x = e.values[0];
                var y = e.values[1];
                var z = e.values[2];

                var speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD_GRAVITY) {
                    mShakeCount++

                    Log.d(this.javaClass.name, "mShakeCount = " + mShakeCount)

                    if (mShakeCount % 2 == 0) {
                        // 이벤트발생!!
                        Log.d(this.javaClass.name, "Shake 발생 [ " + mShakeCount + " ]")
                        setDice()
                    }
                } else {
                    mShakeCount = 0
                }

                lastX = e.values[0];
                lastY = e.values[1];
                lastZ = e.values[2];
            }
        }
    }

    override fun onAccuracyChanged(e: Sensor?, v: Int) {
//        TODO("Not yet implemented")
    }
}