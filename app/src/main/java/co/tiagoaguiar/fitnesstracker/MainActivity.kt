package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

//    private lateinit var btnImc: LinearLayout
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter()
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)





//        btnImc = findViewById(R.id.btn_imc)
//
//        btnImc.setOnClickListener {
//            val i = Intent(this, ImcActivity::class.java)
//            startActivity(i)
//        }


    }
    private inner class MainAdapter: RecyclerView.Adapter<MainViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return  MainViewHolder(view)

        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        }

    }

    private class MainViewHolder (view: View): RecyclerView.ViewHolder(view) {

    }
}


