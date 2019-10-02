package github.debian17.notes.ui.notes.adapter

import github.debian17.notes.base.recycler.BaseDiffCallback
import github.debian17.notes.model.Note

class NoteDiffCallback(oldList: List<Note>, newList: List<Note>) :
    BaseDiffCallback<Note>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.title == newItem.title && oldItem.content == newItem.content
    }

}