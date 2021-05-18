package data.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class TrainingResponse(@SerializedName("trainingId") val trainingId: String?,
                        @SerializedName("date") val date: Calendar?,
                        @SerializedName("label") val label: String?) : Parcelable




