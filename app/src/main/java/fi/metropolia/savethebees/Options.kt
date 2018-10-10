package fi.metropolia.savethebees

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.options.*

class Options : AppCompatActivity() {

    private val beeUrl = "https://en.wikipedia.org/wiki/Bee"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.options)

        Toast.makeText(this, R.string.tapBee, Toast.LENGTH_SHORT).show()

        //link to Wikipedia
        clickBee.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(beeUrl)
            startActivity(intent)
        }

        //back button
        val backBtn = supportActionBar
        backBtn!!.title = ""

        backBtn.setDisplayHomeAsUpEnabled(true)
        backBtn.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}