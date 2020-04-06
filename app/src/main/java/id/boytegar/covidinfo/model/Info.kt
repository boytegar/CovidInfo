package id.boytegar.covidinfo.model
import com.google.gson.annotations.SerializedName


data class Info(
    @SerializedName("name")
    var name: String?,
    @SerializedName("value")
    var value: String?
)