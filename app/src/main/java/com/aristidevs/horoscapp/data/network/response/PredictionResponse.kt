package com.aristidevs.horoscapp.data.network.response
/**
 * Modelo de Datos de la API
 *
 * Flujo: (8) se convierte en [PredictionModel] (usando toDomain())
 * (Este mapeo ocurre dentro o es orquestado por RepositoryImpl)
 *
 * Es el "molde" para los datos que llegan directamente desde la API.
 *
 * Se ubica aquí porque es la representación cruda de la información que recibimos del exterior.
 */
import com.aristidevs.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String
) {
    //Mapper data a domain
    //Recibe la "información cruda" del servidor (PredictionResponse).
    //La "limpia" y la adapta a la forma que tu lógica de negocio espera (PredictionModel).
    //Descarta cualquier dato de la respuesta que no sea relevante para el dominio
    // (en este caso, parece que date no es parte de PredictionModel).
    fun toDomain(): PredictionModel {
        return PredictionModel(
            horoscope = horoscope,
            sign = sign)
    }
}