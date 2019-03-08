/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sevenpeakssoftware.zawlynn.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sevenpeakssoftware.zawlynn.R
import com.sevenpeakssoftware.zawlynn.model.CarModel
import com.sevenpeakssoftware.zawlynn.util.CommonUtility
import com.sevenpeakssoftware.zawlynn.util.GlideApp
import kotlinx.android.synthetic.main.car_item.view.*

class CarsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)) {
    private val imgThumb = itemView.imgThumb
    private val txtDesc = itemView.txtDesc
    private val txtHeader = itemView.txtHeader
    private val txtDate = itemView.txtDate
    var car: CarModel? = null
    val context=parent.context
    fun bindTo(_car: CarModel?) {
        this.car = _car
        car?.let {
            it.title?.let {
                txtHeader.text = it
            }
            it.dateTime?.let {
                txtDate.text=CommonUtility.getInstance().getReleasedDate(it,context)
            }
            it.ingress?.let {
                txtDesc.text = it
            }

            GlideApp.with(context)
                .load(it.image)
                .into(imgThumb)
        }
    }
}