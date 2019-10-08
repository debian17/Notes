package github.debian17.notes.ui.notes.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.debian17.domain.model.Note
import github.debian17.notes.R

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val tvContent: TextView = itemView.findViewById(R.id.tvContent)

    fun bind(note: Note) {
        tvTitle.text = note.title
        tvContent.text = note.content
    }

}