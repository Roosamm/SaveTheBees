package fi.metropolia.savethebees

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class HowToPlay: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.how_to_play)



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