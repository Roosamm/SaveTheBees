package fi.metropolia.savethebees

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class StepSensor : AppCompatActivity(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var running = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor != null) {
            mSensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        mSensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (running) {
            var mFloat = event.values[0]
            val mInt = mFloat.toInt()
            showSteps.text = "" + mInt
        }
    }

    override fun onAccuracyChanged(Sensor: Sensor, accuracy: Int) {

    }
}