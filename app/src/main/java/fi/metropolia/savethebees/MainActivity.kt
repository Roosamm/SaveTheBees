package fi.metropolia.savethebees

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity() {

    lateinit var testRenderable: ModelRenderable
    lateinit var arFragment: ArFragment
//    lateinit var modelUri:Uri
    lateinit var startButton: Button

    private fun getScreenCenter():android.graphics.Point{
        val vw = findViewById<View>(android.R.id.content)
        return android.graphics.Point(vw.width / 2, vw.height / 2)
    }

    private fun  addObject(){
        val frame = arFragment.arSceneView.arFrame
        val pt = getScreenCenter()
        val hits: List<HitResult>
        if (frame != null && testRenderable != null){
            hits = frame.hitTest(pt.x.toFloat(), pt.y.toFloat())
            for (hit in hits) {
                val trackable = hit.trackable
                if (trackable is Plane) {
                    val anchor = hit!!.createAnchor()
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(arFragment.arSceneView.scene)
                    val mNode = TransformableNode(arFragment.transformationSystem)
                    mNode.setParent(anchorNode)
                    mNode.renderable = testRenderable
                    mNode.select()
                    break
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startBtn)
        startButton.setOnClickListener{ view -> addObject() }
        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment

        val bee = ModelRenderable.builder()
                .setSource(this, Uri.parse("Mesh_Bumblebee.sfb"))
                .build()
        bee.thenAccept {it -> testRenderable = it}

//        val spider = ModelRenderable.builder()
//                .setSource(this, Uri.parse("Spider_01.sfb"))
//                .build()
//        spider.thenAccept {it -> testRenderable = it}

//        val honey = ModelRenderable.builder()
//                .setSource(this, Uri.parse("model.sfb"))
//                .build()
//        honey.thenAccept {it -> testRenderable = it}
    }
}
