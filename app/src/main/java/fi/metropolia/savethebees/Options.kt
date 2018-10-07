package fi.metropolia.savethebees

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.options.*

class Options : AppCompatActivity() {

    val beeUrl = "https://en.wikipedia.org/wiki/Bee"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.options)

        Toast.makeText(this, "Tap the bee to Wikipedia", Toast.LENGTH_SHORT).show()

        //link to Wikipedia
        clickBee.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(beeUrl)
            startActivity(intent)
        }

        //change language
        val mLanguage : Spinner



        //back to previous page
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