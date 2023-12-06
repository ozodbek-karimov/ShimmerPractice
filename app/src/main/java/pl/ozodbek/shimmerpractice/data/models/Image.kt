package pl.ozodbek.shimmerpractice.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pl.ozodbek.mastededittextpractice.util.Constants.Companion.BASE_URL

@Parcelize
@Serializable
data class Image(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("id")
    val id: Int,
    @SerialName("imagePath")
    val imagePath: String,
    @SerialName("postId")
    val postId: Int,
    @SerialName("updatedAt")
    val updatedAt: String
):Parcelable{

    val imagePathModified: String
        get() = BASE_URL + imagePath
}