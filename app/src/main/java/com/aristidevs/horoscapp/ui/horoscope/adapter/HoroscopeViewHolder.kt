package com.aristidevs.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.horoscapp.databinding.ItemHoroscopeBinding
import com.aristidevs.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        //busca las referencias de recursos dentro de los recursos de imagenes y string
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        val context = binding.tvTitle.context
        binding.tvTitle.text = context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, delayLambda = {onItemSelected(horoscopeInfo)})
        }
    }

    fun startRotationAnimation(view: View, delayLambda:()->Unit) {
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction { delayLambda() }
            start()
        }
    }
}