package id.boytegar.covidinfo.repo

import id.boytegar.covidinfo.helper.UseCaseResult
import id.boytegar.covidinfo.model.Global
import id.boytegar.covidinfo.model.Indonesia
import id.boytegar.covidinfo.model.Info
import id.boytegar.covidinfo.model.Provinsi
import id.boytegar.covidinfo.service.ApiService
import id.boytegar.covidinfo.service.RetrofitServices
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.lang.Exception

class InfoRepository {

    val data = RetrofitServices.cteateService(ApiService::class.java)

    suspend fun getDataGlobal():UseCaseResult<Global>{
        return try {
            UseCaseResult.Progress(true)
            delay(3000L)
            val result = data.getDataGlobal().await()
             UseCaseResult.Success(result)
        }catch (ex: Exception){
             UseCaseResult.Error(ex)
        }
    }

    suspend fun getDataIndonesia():UseCaseResult<Indonesia>{
        return try {
            val result = data.getDataIdn().await()
             UseCaseResult.Success(result)
        }catch (ex: Exception){
             UseCaseResult.Error(ex)
        }
    }

    suspend fun getDataProvinsiIdn():UseCaseResult<Provinsi>{
        return try {
            val result = data.getDetailIdnProvinsi().await()
             UseCaseResult.Success(result)
        }catch (ex: Exception){
             UseCaseResult.Error(ex)
        }
    }

    suspend fun getDataPositif():UseCaseResult<Info> {
        return try {
            UseCaseResult.Progress(true)
            val result = data.getDataPositif().await()
             UseCaseResult.Success(result)
        }catch (ex: Exception){
             UseCaseResult.Error(ex)
        }
    }
    suspend fun getDataMeninggal():UseCaseResult<Info>{

        return try {
            val result = data.getDataMeninggal().await()
             UseCaseResult.Success(result)
        }catch (ex: Exception){
             UseCaseResult.Error(ex)
        }
    }
    suspend fun getDataSembuh():UseCaseResult<Info>{
        return try {
            val result = data.getDataSembuh().await()
             UseCaseResult.Success(result)
        }catch (ex: Exception){
             UseCaseResult.Error(ex)
        }
    }

}

