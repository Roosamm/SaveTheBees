package fi.metropolia.savethebees

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.options.*

class Options : AppCompatActivity() {

    val beeUrl = "https://en.wikipedia.org/wiki/Bee"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.options)

        beeInfo.setOnClickListener{

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(beeUrl)
            startActivity(intent)
        }

        val backBtn = supportActionBar
        backBtn!!.title = "Options"

        backBtn.setDisplayHomeAsUpEnabled(true)
        backBtn.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}