package id.boytegar.covidinfo.model
import com.google.gson.annotations.SerializedName


class Provinsi : ArrayList<ProvinsiItem>()

data class ProvinsiItem(
    @SerializedName("attributes")
    var attributes: Attributes?
)

data class Attributes(
    @SerializedName("FID")
    var fID: Int?,
    @SerializedName("Kasus_Meni")
    var kasusMeni: Int?,
    @SerializedName("Kasus_Posi")
    var kasusPosi: Int?,
    @SerializedName("Kasus_Semb")
    var kasusSemb: Int?,
    @SerializedName("Kode_Provi")
    var kodeProvi: Int?,
    @SerializedName("Provinsi")
    var provinsi: String?
)