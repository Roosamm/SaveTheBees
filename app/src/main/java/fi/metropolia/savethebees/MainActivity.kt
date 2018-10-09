package fi.metropolia.savethebees

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.exitTitle)
                .setMessage(R.string.exitContent)
                .setPositiveButton(R.string.yes, { dialogInterface: DialogInterface, i: Int ->
                    finish()
        })
                .setNegativeButton(R.string.no, { dialogInterface: DialogInterface, i: Int -> })
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
