package com.amo.motherfinancetest.datasource

import com.amo.motherfinancetest.datasource.pref.Pref
import com.amo.motherfinancetest.datasource.remote.ApiService

class DataSource(private val apiService: ApiService, private val pref: Pref) : Pref by pref,
    ApiService by apiService