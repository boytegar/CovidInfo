package id.boytegar.covidinfo.model
import com.google.gson.annotations.SerializedName


class Indonesia : ArrayList<IndonesiaItem>()

data class IndonesiaItem(
    @SerializedName("meninggal")
    var meninggal: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("positif")
    var positif: String?,
    @SerializedName("sembuh")
    var sembuh: String?
)