package com.aristidevs.horoscapp.domain.usecase
/**
 * Regla de Negocio
 *
 * Flujo:
 *  (4) solicita datos a [Repository] (interfaz)
 *  (10) devuelve [PredictionModel] al [HoroscopeDetailViewModel]
 *
 * Contiene la "regla de negocio" para obtener una predicción específica.
 * Devuelve el PredictionModel al HoroscopeDetailViewModel
 *
 * Se ubica en usecase porque cada archivo aquí representa una "acción" o "caso de uso"
 *  que la aplicación puede realizar.
 */
import com.aristidevs.horoscapp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(sign: String) = repository.getPrediction(sign)
}