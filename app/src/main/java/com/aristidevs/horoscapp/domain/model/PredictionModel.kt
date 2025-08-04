package com.aristidevs.horoscapp.domain.model
/**
 * Modelo de datos limpio
 *
 * Son las "formas limpias" de los datos que usa la lógica de negocio.
 *
 * Están en domain/model porque representan la información esencial y agnóstica de la aplicación,
 *  sin preocuparse de si viene de la red o de otro lado.
 */
data class PredictionModel(
    val horoscope: String,
    val sign: String
)