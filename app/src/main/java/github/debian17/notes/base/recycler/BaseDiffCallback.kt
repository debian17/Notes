package github.debian17.notes.base.recycler

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<T>(
    protected val oldList: List<T>,
    protected val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

}