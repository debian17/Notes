package github.debian17.notes.ui.notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import github.debian17.notes.R
import github.debian17.notes.base.recycler.BaseAdapter
import github.debian17.notes.model.Note

class NotesAdapter(context: Context, private val notesListener: NotesListener) :
    BaseAdapter<Note, NoteViewHolder>() {

    interface NotesListener {
        fun onNoteClick(note: Note)
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = inflater.inflate(R.layout.item_note, parent, false)
        val viewHolder = NoteViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val note = getItems()[position]
            notesListener.onNoteClick(note)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItems()[position]
        holder.bind(note)
    }

}