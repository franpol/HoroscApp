package com.aristidevs.horoscapp.data
/**
 * Implementación Real del Repositorio
 *
 * Flujo:
 *  (6) llama a [HoroscopeApiService]
 *      (Nota: NetworkModule es quien le "dice" a Dagger Hilt cómo crear HoroscopeApiService para que
 *       RepositoryImpl lo reciba inyectado.)
 *  (9) devuelve [PredictionModel] al [GetPredictionUseCase]
 * devuelve)--> PredictionModel (al GetPredictionUseCase)
 *
 * Es quien realmente va y busca los datos, ya sea de la API o de otra fuente concreta.
 * Devuelve un PredictionModel al GetPredictionUseCase
 *
 * Se ubica en data porque es la implementación específica de cómo se manejan los datos a nivel técnico.
 *
 */
import android.util.Log
import com.aristidevs.horoscapp.data.network.HoroscopeApiService
import com.aristidevs.horoscapp.data.network.response.PredictionResponse
import com.aristidevs.horoscapp.domain.Repository
import com.aristidevs.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService): Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("franpol","Ha ocurrido un error ${it.message}") }
        return null
    }
}