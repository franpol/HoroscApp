package com.aristidevs.horoscapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.horoscapp.databinding.ItemHoroscopeBinding
import com.aristidevs.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo) {
        //busca las referencias de recursos dentro de los recursos de imagenes y string
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        val context = binding.tvTitle.context
        binding.tvTitle.text = context.getString(horoscopeInfo.name)
    }
}