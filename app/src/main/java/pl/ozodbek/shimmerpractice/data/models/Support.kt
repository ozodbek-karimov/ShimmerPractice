package pl.ozodbek.shimmerpractice.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Support(
    @SerialName("text")
    val text: String,
    @SerialName("url")
    val url: String
)