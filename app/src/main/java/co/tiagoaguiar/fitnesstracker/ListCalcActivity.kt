package co.tiagoaguiar.fitnesstracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.fitnesstracker.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.concurrent.thread

class ListCalcActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private val result = mutableListOf<Calc>()
    private lateinit var adapter: ListCalcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_calc)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rv = findViewById(R.id.rv_list)
        adapter = ListCalcAdapter(result)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        val type = intent?.getStringExtra("type") ?: throw IllegalStateException("type not found")

        thread {
            val app = application as App
            val dao = app.db.calcDao()
            val response = dao.getRegisterByType(type)

            runOnUiThread {
                result.clear()
                result.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private inner class ListCalcAdapter(
        private val listCalc: List<Calc>
    ) : RecyclerView.Adapter<ListCalcAdapter.ListCalcViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCalcViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ListCalcViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListCalcViewHolder, position: Int) {
            val item = listCalc[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = listCalc.size

        inner class ListCalcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: Calc) {
                val tv = itemView as TextView
                val sdf = SimpleDateFormat("dd/MM/yy HH:mm", Locale("pt", "BR"))
                val data = sdf.format(item.createdDate)
                val res = item.res

                tv.text = getString(R.string.list_response, res, data)
            }
        }
    }
}
