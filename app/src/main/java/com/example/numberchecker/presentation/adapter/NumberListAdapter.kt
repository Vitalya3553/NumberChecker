package com.example.numberchecker.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.numberchecker.R
import com.example.numberchecker.domain.model.Contact

class NumberListAdapter(private val dataSet: List<Contact>) :
    RecyclerView.Adapter<NumberListAdapter.ViewHolder>() {

    var onItemClick : ((Contact) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView
        val number: TextView

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.listItemName)
            number = view.findViewById(R.id.listItemNumber)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_number_list_style, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val contact = dataSet[position]
        viewHolder.name.text = contact.name
        viewHolder.number.text = contact.number

        viewHolder.itemView.setOnClickListener{
            onItemClick?.invoke(contact)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}