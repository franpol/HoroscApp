package com.aristidevs.horoscapp.domain.model
/**
 * Modelo de datos limpio
 *
 * Son las "formas limpias" de los datos que usa la lógica de negocio.
 *
 * Están en domain/model porque representan la información esencial y agnóstica de la aplicación,
 *  sin preocuparse de si viene de la red o de otro lado.
 */
enum class HoroscopeModel {
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
}