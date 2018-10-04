package fi.metropolia.savethebees

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.ar_fragment.*

class ArGame: AppCompatActivity(), SensorEventListener {

    lateinit var beeRenderable: ModelRenderable
    lateinit var arFragment: ArFragment
    //    lateinit var modelUri:Uri
    lateinit var startButton: Button

    //Step Sensor
    private var mSensorManager: SensorManager? = null
    private var running = false

    private fun getScreenCenter():android.graphics.Point{
        val vw = findViewById<View>(android.R.id.content)
        return android.graphics.Point(vw.width / 2, vw.height / 2)
    }

    private fun  addBeeObject(){
        val frame = arFragment.arSceneView.arFrame
        val pt = getScreenCenter()
        val hits: List<HitResult>
        if (frame != null && beeRenderable != null){
            hits = frame.hitTest(pt.x.toFloat(), pt.y.toFloat())
            for (hit in hits) {
                val trackable = hit.trackable
                if (trackable is Plane) {
                    val anchor = hit!!.createAnchor()
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(arFragment.arSceneView.scene)
                    val mNode = TransformableNode(arFragment.transformationSystem)
                    mNode.setParent(anchorNode)
                    mNode.renderable = beeRenderable
                    mNode.select()
                    break
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ar_fragment)

        //Step Sensor
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        startButton = findViewById(R.id.testBtn)
        startButton.setOnClickListener{ view -> addBeeObject() }
        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment

        val bee = ModelRenderable.builder()
                .setSource(this, Uri.parse("Mesh_Bumblebee.sfb"))
                .build()
        bee.thenAccept {it -> beeRenderable = it}

//        val spider = ModelRenderable.builder()
//                .setSource(this, Uri.parse("Spider_01.sfb"))
//                .build()
//        spider.thenAccept {it -> beeRenderable = it}

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
        }
    }

    override fun onAccuracyChanged(Sensor: Sensor, accuracy: Int) {

    }

}
