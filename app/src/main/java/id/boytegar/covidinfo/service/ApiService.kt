package id.boytegar.covidinfo.service

import id.boytegar.covidinfo.model.Global
import id.boytegar.covidinfo.model.Indonesia
import id.boytegar.covidinfo.model.Info
import id.boytegar.covidinfo.model.Provinsi
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("indonesia")
    fun getDataIdn(): Deferred<Indonesia>
    @GET("indonesia/provinsi")
    fun getDetailIdnProvinsi(): Deferred<Provinsi>
    @GET("/")
    fun getDataGlobal(): Deferred<Global>
    @GET("sembuh")
    fun getDataSembuh(): Deferred<Info>
    @GET("positif")
    fun getDataPositif(): Deferred<Info>
    @GET("meninggal")
    fun getDataMeninggal(): Deferred<Info>
}