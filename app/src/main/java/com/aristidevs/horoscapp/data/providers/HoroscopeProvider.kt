package com.aristidevs.horoscapp.data.providers
/**
 * Datos Estáticos
 *
 * Flujo: (3) Devuelve la lista de [HoroscopeInfo]
 *
 * Ofrece los horóscopos base que ya vienen "dentro" de la app.
 *
 * Está en providers porque su misión es "proveer" datos estáticos a la aplicación,
 *  sin depender de la red o una base de datos.
 */
import com.aristidevs.horoscapp.domain.model.HoroscopeInfo
import com.aristidevs.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

//Si una clase no tiene parámetros en su constructor, podemos decir que no tiene dependencias y, por lo tanto, no necesita recibir una inyección de Dagger Hilt.
//En ese caso, la instancia de esa clase puede ser inyectada en otras clases que sí la necesiten.
//Como Hilt sabe cómo crear una instancia de HoroscopeProvider sin ninguna dependencia, puede inyectar esa instancia en otras clases que sí la necesiten, como el HoroscopeViewModel.
class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf<HoroscopeInfo>(
            Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpio,
            Sagittarius,
            Capricorn,
            Aquarius,
            Pisces
        )
    }
}