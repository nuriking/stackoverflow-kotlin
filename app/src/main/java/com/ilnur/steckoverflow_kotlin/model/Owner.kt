package com.ilnur.steckoverflow_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
        @SerializedName("reputation") val reputation: Int,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("profile_image") val profileImage: String,
        @SerializedName("display_name") val displayName: String
): Serializable