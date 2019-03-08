package com.sevenpeakssoftware.zawlynn.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.sevenpeakssoftware.zawlynn.adapter.viewholder.CarsViewHolder
import com.sevenpeakssoftware.zawlynn.model.CarModel

class CarListsAdapter : PagedListAdapter<CarModel, CarsViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder = CarsViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CarModel>() {
            override fun areItemsTheSame(oldItem: CarModel, newItem: CarModel): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: CarModel, newItem: CarModel): Boolean =
                oldItem == newItem
        }
    }
}
