package com.amo.motherfinancetest.di

import com.amo.motherfinancetest.datasource.DataSource
import com.amo.motherfinancetest.datasource.pref.Pref
import com.amo.motherfinancetest.datasource.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideDataSource(apiService: ApiService, pref: Pref) = DataSource(apiService, pref)

}