package com.mvclopes.spacexlaunches.data.model

import com.google.gson.annotations.SerializedName

data class LinksResponse(
    @SerializedName("mission_patch_small") val missionPatch: String,
    @SerializedName("article_link") val article: String,
    @SerializedName("video_link") val video: String,
)
