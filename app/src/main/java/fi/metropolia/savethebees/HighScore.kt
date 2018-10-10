package fi.metropolia.savethebees

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.highscore.*

class HighScore : AppCompatActivity() {

    lateinit var playerName: EditText
    lateinit var myScore: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.highscore)

        playerName = findViewById(R.id.playerName)
        myScore = findViewById(R.id.myScore)

        retrieveData()

        findViewById<Button>(R.id.saveBtn).setOnClickListener {
            saveData()
        }

        //back button
        val backBtn = supportActionBar
        backBtn!!.title = ""

        backBtn.setDisplayHomeAsUpEnabled(true)
        backBtn.setDisplayHomeAsUpEnabled(true)
    }

    private fun retrieveData() {
        val myPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val name = myPref.getString("name", "")
        val score = myPref.getString("score", "")

        showName.setText(name)
        showScore.setText(score)
    }

    private fun saveData() {
        if (playerName.text.isEmpty()) {
            Toast.makeText(this, R.string.noName, Toast.LENGTH_SHORT).show()
            return
        }

        if (myScore.text.isEmpty()) {
            Toast.makeText(this, R.string.noScore, Toast.LENGTH_SHORT).show()
            return
        }

        val myPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = myPref.edit()

        editor.putString("name", playerName.text.toString())
        editor.putString("score", myScore.text.toString())

        editor.apply()
        Toast.makeText(this, R.string.dataSaved, Toast.LENGTH_SHORT).show()
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}