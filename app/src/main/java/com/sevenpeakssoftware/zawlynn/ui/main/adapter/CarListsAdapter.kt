package com.sevenpeakssoftware.zawlynn.ui.main.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.sevenpeakssoftware.zawlynn.ui.main.adapter.viewholder.CarsViewHolder
import com.sevenpeakssoftware.zawlynn.model.CarModel

class CarListsAdapter : ListAdapter<CarModel, CarsViewHolder>(ArticleItemCallback()) {
    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder =
        CarsViewHolder(parent)

    private class ArticleItemCallback : DiffUtil.ItemCallback<CarModel>() {

        override fun areItemsTheSame(oldItem: CarModel, newItem: CarModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CarModel, newItem: CarModel): Boolean {
            return oldItem == newItem
        }
    }



}
