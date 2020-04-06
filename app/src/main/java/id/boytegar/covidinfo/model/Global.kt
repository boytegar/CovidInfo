package id.boytegar.covidinfo.model
import com.google.gson.annotations.SerializedName


class Global : ArrayList<GlobalItem>()

data class GlobalItem(
    @SerializedName("attributes")
    var attributes: Attributed?
)

data class Attributed(
    @SerializedName("Active")
    var active: Int?,
    @SerializedName("Confirmed")
    var confirmed: Int?,
    @SerializedName("Country_Region")
    var countryRegion: String?,
    @SerializedName("Deaths")
    var deaths: Int?,
    @SerializedName("Last_Update")
    var lastUpdate: Long?,
    @SerializedName("Lat")
    var lat: Double?,
    @SerializedName("Long_")
    var long: Double?,
    @SerializedName("OBJECTID")
    var oBJECTID: Int?,
    @SerializedName("Recovered")
    var recovered: Int?
)