package fi.metropolia.savethebees

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.ar_fragment.*

class ArGame: AppCompatActivity(), SensorEventListener {

    lateinit var beeRenderable: ModelRenderable
    lateinit var arFragment: ArFragment
    var bees: Int = 0
    var points: Int = 0

    //Step Sensor
    private var mSensorManager: SensorManager? = null
    private var running = false

    //3D object to random position
    private fun rndPosition(): Float{
        val rnd = (-2..2).shuffled().first()
        return rnd.toFloat()
    }

    //Adds 3D object
    private fun  addBeeObject(){

        for(i in 1..6) {
            val x = rndPosition()
            val y = rndPosition()
            val z = rndPosition()
            val frame = arFragment.arSceneView.arFrame
            val rp = rndPosition()
            val hits: List<HitResult>
            if (frame != null) {
                hits = frame.hitTest(rp, rp)
                for (hit in hits) {
                    val trackable = hit.trackable
                    if (trackable is Plane && bees == 0) {
                        val anchor = arFragment.arSceneView.session.createAnchor(arFragment.arSceneView.arFrame.camera.displayOrientedPose)
                        val anchorNode = AnchorNode(anchor)
                        anchorNode.setParent(arFragment.arSceneView.scene)
                        val mNode = TransformableNode(arFragment.transformationSystem)
                        mNode.select()
                        mNode.setParent(anchorNode)
                        mNode.localPosition = Vector3(x, y, z)
                        mNode.renderable = beeRenderable
                        mNode.select()
                        Toast.makeText(this, R.string.moreBees, Toast.LENGTH_SHORT).show()

                        //Remove 3D object when user taps it
                        mNode.setOnTapListener { _, _ ->
                            anchorNode.removeChild(mNode)
                            bees--
                            showPoints.text = "" + (points++) * 5
                            if (bees == 0) {
                                Toast.makeText(this, R.string.searchArea, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }


    //Ask user if they want to exit the app when they press phones back button
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
        builder.setView(dialogView)
                .setPositiveButton(R.string.yes, { _, _ ->
                    super.onBackPressed()
        })
                .setNegativeButton(R.string.no, { _, _ -> })
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ar_fragment)

        // quit button in AR screens top left corner
        quitBtn.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
            builder.setView(dialogView)
                    .setPositiveButton(R.string.yes, { _, _ ->
                        super.onBackPressed()
                    })
                    .setNegativeButton(R.string.no, { _, _ -> })
                    .show()
        }

        //Step Sensor
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        //Adds 3D object after finding flat surface
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
    }

    //Step sensor
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
            showPoints.text = "" + (points++)*2
        }
    }

    override fun onAccuracyChanged(Sensor: Sensor, accuracy: Int) {
    }
}
