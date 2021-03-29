package app.moviebase.androidx.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.moviebase.androidx.widget.recyclerview.adapter.list.recyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemsAdapter = recyclerViewAdapter<TextItem> {
        headerViewHolder { adapter, parent -> HeaderViewHolder(adapter, parent) }
        viewHolder { adapter, parent -> TextViewHolder(adapter, parent) }

        onItemId { it.id }

        onClick {
            Toast.makeText(this@MainActivity, "Click on ${it.text}", Toast.LENGTH_SHORT).show()
        }

        onLongClick {
            Toast.makeText(this@MainActivity, "Long click on ${it.text}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(TextItem(1, "English"), TextItem(2, "German"), TextItem(3, "Spanish"))
        itemsAdapter.setData(items)

        recyclerView.apply {
            setHasFixedSize(true)
            adapter = itemsAdapter
        }
    }

}
