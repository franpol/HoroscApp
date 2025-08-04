package com.aristidevs.horoscapp.data.network
/**
 * Cliente de API
 *
 * Flujo: (7) devuelve [PredictionResponse]
 *
 * Es la "puerta" para hablar con la API de horóscopos.
 *
 * Va en network porque es la interfaz que define las operaciones de red.
 */
import com.aristidevs.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {
    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String) : PredictionResponse
}