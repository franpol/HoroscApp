package com.aristidevs.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/* la notacion drawableres y stringres indican que la referencia debe existir en dentro de drawables o strings
* Esto ayuda a evitar errores de compilacion*/
data class LuckyModel(
    @DrawableRes val image:Int,
    @StringRes val text:Int
)
