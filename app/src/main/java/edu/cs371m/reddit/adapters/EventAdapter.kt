package edu.cs371m.reddit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.cs371m.reddit.api.YoutubeVideo
import edu.cs371m.reddit.databinding.RowEventBinding
import edu.cs371m.reddit.model.Event
import edu.cs371m.reddit.ui.MainViewModel

/**
 * Created by witchel on 8/25/2019
 */

// https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter
// Slick adapter that provides submitList, so you don't worry about how to update
// the list, you just submit a new one when you want to change the list and the
// Diff class computes the smallest set of changes that need to happen.
// NB: Both the old and new lists must both be in memory at the same time.
// So you can copy the old list, change it into a new list, then submit the new list.
//
// You can call adapterPosition to get the index of the selected item
class EventAdapter(private val viewModel: MainViewModel)
    : ListAdapter<Event, EventAdapter.VH>(ListDiff()) {

    class ListDiff : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.eventsList == newItem.eventsList
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.getName() == newItem.getName()
                    && oldItem.getDate() == newItem.getDate()

        }
    }

    inner class VH(val rowPostBinding: RowEventBinding)
        : RecyclerView.ViewHolder(rowPostBinding.root){
        init {
            rowPostBinding.root.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val rowBinding = RowEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(rowBinding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val binding = holder.rowPostBinding
        binding.eventNameTV.text = viewModel.getEvent(position).getName()
    }
}

