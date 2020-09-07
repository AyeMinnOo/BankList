package com.amo.motherfinancetest.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.amo.motherfinancetest.datasource.DataSource
import com.amo.motherfinancetest.model.Banks
import com.amo.motherfinancetest.model.Result
import com.amo.motherfinancetest.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    dataSource: DataSource
) : BaseViewModel(dataSource) {

    val responseBanks: MutableLiveData<Result<Banks>> = MutableLiveData()

    init {
        getBanks()
    }

    fun getBanks() {
        launch {
            responseBanks.value = Result.Loading("loading")
            val request = dataSource.getBanks().await()
            if (request.isSuccessful) {
                val response = request.body()
                responseBanks.value = Result.Done(response)
            } else responseBanks.value = Result.Error(Exception("something went wrong"))
        }
    }


}