package com.aristidevs.horoscapp.domain
/**
 * Interfaz/Contrato de Datos
 *
 * Flujo: (5) es implementado por [RepositoryImpl]
 *
 * Es el "contrato" que dice qué se puede pedir sobre los datos, sin importar cómo se obtienen.
 *
 * Está directamente en la capa domain (no en usecase) porque es una abstracción fundamental
 *  de los datos que aplica a t/odo el dominio, y los casos de uso (GetPredictionUseCase) y
 *   otras partes de la capa domain la necesitan para operar sin conocer los detalles de la capa data.
 */
import com.aristidevs.horoscapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}