package app.moviebase.androidx.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.moviebase.androidx.sample.databinding.ActivityMainBinding
import app.moviebase.androidx.widget.recyclerview.adapter.recyclerViewAdapter

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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(TextItem(1, "English"), TextItem(2, "German"), TextItem(3, "Spanish"))
        itemsAdapter.setData(items)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = itemsAdapter
        }
    }

}
