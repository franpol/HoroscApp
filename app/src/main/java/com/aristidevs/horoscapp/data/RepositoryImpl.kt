package com.aristidevs.horoscapp.data

import com.aristidevs.horoscapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(): Repository {
    override suspend fun getPrediction(sign: String) {

    }
}