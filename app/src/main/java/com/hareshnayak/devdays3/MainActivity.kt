package com.hareshnayak.devdays3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import com.hareshnayak.devdays3.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the array lists and the adapter
        val itemList = arrayListOf<String>()
        val adapter =ArrayAdapter(this,
            android.R.layout.simple_list_item_multiple_choice
            , itemList)
        // Adding the items to the list when the add button is pressed
        binding.add.setOnClickListener {

            itemList.add(binding.editText.text.toString())
            binding.listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            // This is because every time when you add the item the input space or the edit text space will be cleared
            binding.editText.text.clear()
        }
        // Clearing all the items in the list when the clear button is pressed
        binding.clear.setOnClickListener {

            itemList.clear()
            adapter.notifyDataSetChanged()
        }
        // Adding the toast message to the list when an item on the list is pressed
        binding.listView.setOnItemClickListener { _, _, i, _ ->
            android.widget.Toast.makeText(this, "You Selected the item --> "+itemList[i], android.widget.Toast.LENGTH_SHORT).show()
        }
        // Selecting and Deleting the items from the list when the delete button is pressed
        binding.delete.setOnClickListener {
            val position: SparseBooleanArray = binding.listView.checkedItemPositions
            val count = binding.listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemList[item])
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}