package fi.metropolia.savethebees

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.ar_fragment.*
import java.util.*

class ArGame: AppCompatActivity(), SensorEventListener {

    lateinit var beeRenderable: ModelRenderable
    lateinit var arFragment: ArFragment
    //    lateinit var modelUri:Uri
    var num: Int = 0
    var points: Int = 0

    //Step Sensor
    private var mSensorManager: SensorManager? = null
    private var running = false

    private fun rndPosition(): Float{
        val rnd = Math.random() * 1
        return rnd.toFloat()
    }


    private fun  addBeeObject(){
        val frame = arFragment.arSceneView.arFrame
        val pt = rndPosition()
        val hits: List<HitResult>
        if (frame != null){
            hits = frame.hitTest(pt, pt)
            for (hit in hits) {
                val trackable = hit.trackable
                if (trackable is Plane) {
                    val anchor = hit!!.createAnchor()
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(arFragment.arSceneView.scene)
                    val mNode = TransformableNode(arFragment.transformationSystem)
                    if (num <= 6) {
                        mNode.setParent(anchorNode)
                        mNode.renderable = beeRenderable
                        mNode.select()
                        num++
                        Toast.makeText(this, "MORE BEES", Toast.LENGTH_SHORT).show()
                    }
                    mNode.setOnTapListener { _, _ ->
                        anchorNode.removeChild(mNode)
                        num--
                        showPoints.text = "" + points++
                    }
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ar_fragment)

        //Step Sensor
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        //Ar stuff
        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment
        arFragment.arSceneView.scene.addOnUpdateListener{
            if (arFragment.arSceneView.arFrame.camera.trackingState == TrackingState.TRACKING){
                addBeeObject()
            }
        }

        val bee = ModelRenderable.builder()
                .setSource(this, Uri.parse("Mesh_Bumblebee.sfb"))
                .build()
        bee.thenAccept {it -> beeRenderable = it}


//        val spider = ModelRenderable.builder()
//                .setSource(this, Uri.parse("Spider_01.sfb"))
//                .build()
//        spider.thenAccept {it -> spiderRenderable = it}

//        val honey = ModelRenderable.builder()
//                .setSource(this, Uri.parse("model.sfb"))
//                .build()
//        honey.thenAccept {it -> beeRenderable = it}
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
            val mFloat = event.values[0]
            val mInt = mFloat.toInt()
            showSteps.text = "" + mInt
            showPoints.text = "" + points++
        }
    }

    override fun onAccuracyChanged(Sensor: Sensor, accuracy: Int) {

    }

}
