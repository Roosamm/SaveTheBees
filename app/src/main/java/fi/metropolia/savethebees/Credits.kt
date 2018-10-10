package fi.metropolia.savethebees

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Credits: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credits)

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
