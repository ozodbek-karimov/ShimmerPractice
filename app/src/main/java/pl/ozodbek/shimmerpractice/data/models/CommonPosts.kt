package pl.ozodbek.shimmerpractice.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CommonPosts(
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("categoryName")
    val categoryName: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("description")
    val description: String,
    @SerialName("district")
    val district: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("images")
    val images: @RawValue List<Image>,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("price")
    val price: Int,
    @SerialName("region")
    val region: String,
    @SerialName("title")
    val title: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("userId")
    val userId: Int,
) : Parcelable {

    val imagePathModified: String
        get() = images.firstOrNull()?.imagePathModified.toString()

    val imageUpdateData: String?
        get() = images.firstOrNull()?.updatedAt

}