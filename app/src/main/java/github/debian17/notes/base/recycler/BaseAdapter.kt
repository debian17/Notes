package github.debian17.notes.base.recycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {

    private val items = ArrayList<T>()

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(item: T) {
        val position = items.size
        items.add(item)
        notifyItemInserted(position)
    }

    fun replaceItems(items: List<T>, diffUtil: DiffUtil.Callback) {
        val result = DiffUtil.calculateDiff(diffUtil)

        this.items.clear()
        this.items.addAll(items)

        result.dispatchUpdatesTo(this)
    }

    fun getItems(): List<T> {
        return items
    }

}