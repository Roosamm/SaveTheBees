package fi.metropolia.savethebees

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
