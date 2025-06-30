package com.aristidevs.horoscapp.domain

interface Repository {
    suspend fun getPrediction(sign:String)
}