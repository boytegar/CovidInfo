package id.boytegar.covidinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.boytegar.covidinfo.helper.UseCaseResult
import id.boytegar.covidinfo.model.Global
import id.boytegar.covidinfo.model.Info
import id.boytegar.covidinfo.repo.InfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoViewModel : ViewModel(){
    val infoRepository = InfoRepository()
    val list_global = MutableLiveData<UseCaseResult<Global>>()
    val data_sembuh = MutableLiveData<Info>()
    val data_positif = MutableLiveData<Info>()
    val data_meninggal = MutableLiveData<Info>()

    init {
        getDataSembuh()
        getDataMeninggal()
        getDataPositif()
        getDataGlobal()
    }

    fun getDataGlobal() = viewModelScope.launch(Dispatchers.IO) {
            val resultGlobal = infoRepository.getDataGlobal()
            withContext(Dispatchers.Main){
                list_global.value = resultGlobal
            }
        }

    fun getDataSembuh() = viewModelScope.launch(Dispatchers.Main) {
        val result = infoRepository.getDataSembuh()
        when(result){
           is UseCaseResult.Success<Info> ->{
               data_sembuh.value = result.data
            }
            is UseCaseResult.Error ->{
                data_sembuh.value = null
            }
        }
    }

    fun getDataPositif() = viewModelScope.launch(Dispatchers.Main) {
        val result = infoRepository.getDataPositif()
        when(result){
            is UseCaseResult.Success<Info> ->{
                data_positif.value = result.data
            }
            is UseCaseResult.Error ->{
                data_positif.value = null
            }
        }
    }

    fun getDataMeninggal() = viewModelScope.launch(Dispatchers.Main) {
        val result = infoRepository.getDataMeninggal()
        when(result){
            is UseCaseResult.Success<Info> ->{
                data_meninggal.value = result.data
            }
            is UseCaseResult.Error ->{
                data_meninggal.value = null
            }
        }
    }

}