package com.example.testthu.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testthu.R

class ManagementAdapter(
    private val itemList: MutableList<Item>,
    private val onEditClick: (Item) -> Unit,
    private val onDeleteClick: (Item) -> Unit
) : RecyclerView.Adapter<ManagementAdapter.ManagementViewHolder>() {

    class ManagementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textStt: TextView = itemView.findViewById(R.id.text_stt)
        val textCategory: TextView = itemView.findViewById(R.id.text_category)
        val textTitle: TextView = itemView.findViewById(R.id.text_title)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val buttonEdit: Button = itemView.findViewById(R.id.button_edit)
        val buttonDelete: Button = itemView.findViewById(R.id.button_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_management, parent, false)
        return ManagementViewHolder(view)
    }

    override fun onBindViewHolder(holder: ManagementViewHolder, position: Int) {
        val item = itemList[position]
        holder.textStt.text = (position + 1).toString()
        holder.textCategory.text = item.category
        holder.textTitle.text = item.title
        holder.imageView.setImageResource(item.imageRes)

        holder.buttonEdit.setOnClickListener { onEditClick(item) }
        holder.buttonDelete.setOnClickListener { onDeleteClick(item) }
    }

    override fun getItemCount(): Int = itemList.size
}