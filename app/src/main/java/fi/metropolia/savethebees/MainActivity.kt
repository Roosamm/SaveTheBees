package fi.metropolia.savethebees

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Alert dialog when user presses phones back button
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
        setContentView(R.layout.activity_main)

        startBtn.setOnClickListener{
            val intent = Intent(this, ArGame::class.java)
            startActivity(intent)
        }

        creditsBtn.setOnClickListener{
            val intent = Intent(this, Credits::class.java)
            startActivity(intent)
        }

        highBtn.setOnClickListener{
            val intent = Intent(this, HighScore::class.java)
            startActivity(intent)
        }

        howToBtn.setOnClickListener{
            val intent = Intent(this, HowToPlay::class.java)
            startActivity(intent)
        }

        optionsBtn.setOnClickListener{
            val intent = Intent(this, Options::class.java)
            startActivity(intent)
        }
    }
}
