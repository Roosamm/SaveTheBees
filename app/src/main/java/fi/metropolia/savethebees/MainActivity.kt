package fi.metropolia.savethebees

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    lateinit var beeRenderable: ModelRenderable
//    lateinit var arFragment: ArFragment
////    lateinit var modelUri:Uri
//    lateinit var startButton: Button
//
//    private fun getScreenCenter():android.graphics.Point{
//        val vw = findViewById<View>(android.R.id.content)
//        return android.graphics.Point(vw.width / 2, vw.height / 2)
//    }
//
//    private fun  addBeeObject(){
//        val frame = arFragment.arSceneView.arFrame
//        val pt = getScreenCenter()
//        val hits: List<HitResult>
//        if (frame != null && beeRenderable != null){
//            hits = frame.hitTest(pt.x.toFloat(), pt.y.toFloat())
//            for (hit in hits) {
//                val trackable = hit.trackable
//                if (trackable is Plane) {
//                    val anchor = hit!!.createAnchor()
//                    val anchorNode = AnchorNode(anchor)
//                    anchorNode.setParent(arFragment.arSceneView.scene)
//                    val mNode = TransformableNode(arFragment.transformationSystem)
//                    mNode.setParent(anchorNode)
//                    mNode.renderable = beeRenderable
//                    mNode.select()
//                    break
//                }
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn.setOnClickListener{
            val intent = Intent(this, ArGame::class.java)
            startActivity(intent)
        }


//        StepSensor()
//
//        startButton = findViewById(R.id.startBtn)
//        startButton.setOnClickListener{ view -> addBeeObject() }
//        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment
//
//        val bee = ModelRenderable.builder()
//                .setSource(this, Uri.parse("Mesh_Bumblebee.sfb"))
//                .build()
//        bee.thenAccept {it -> beeRenderable = it}

//        val spider = ModelRenderable.builder()
//                .setSource(this, Uri.parse("Spider_01.sfb"))
//                .build()
//        spider.thenAccept {it -> beeRenderable = it}

//        val honey = ModelRenderable.builder()
//                .setSource(this, Uri.parse("model.sfb"))
//                .build()
//        honey.thenAccept {it -> beeRenderable = it}
    }
}
