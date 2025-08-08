package com.aristidevs.horoscapp.ui.detail

import com.aristidevs.horoscapp.domain.model.HoroscopeModel

/**
 * Estados de la Pantalla
 *
 * Define los distintos "estados" en los que puede estar la pantalla de detalle
 *  (cargando, error, datos listos).
 *
 * Va en ui/detail porque describe el estado específico de esta interfaz de usuario.
 */
sealed class HoroscopeDetailState {
    data object Loading: HoroscopeDetailState()
    data class Error(val error: String): HoroscopeDetailState()
    data class Success(val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState()
}