package co.tiagoaguiar.fitnesstracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.IllegalStateException
import kotlin.concurrent.thread

class ListCalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_calc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val type = intent?.extras?.getString("type") ?: throw IllegalStateException("type not found") ?: throw IllegalStateException("type not found")

        thread {
            val app = application as App
            val dao = app.db.calcDao()
            val response = dao.getRegisterByType((type))

            runOnUiThread {

            }
        }.start()


    }
}