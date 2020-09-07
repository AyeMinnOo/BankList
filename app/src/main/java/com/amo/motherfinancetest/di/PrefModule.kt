package com.amo.motherfinancetest.di

import com.amo.motherfinancetest.datasource.pref.MyPref
import com.amo.motherfinancetest.datasource.pref.Pref
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class PrefModule {

    @Singleton
    @Binds
    abstract fun providePref(pref: MyPref): Pref

}