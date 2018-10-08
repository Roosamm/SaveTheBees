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
        builder.setTitle("Exit game?")
        builder.setMessage("Do you want to exit game?")
        builder.setPositiveButton("Yes", { dialogInterface: DialogInterface, i: Int ->
            finish()
        })
        builder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
        builder.show()
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
