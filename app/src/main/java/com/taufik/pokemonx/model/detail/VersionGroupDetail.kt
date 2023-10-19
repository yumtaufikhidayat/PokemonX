package com.taufik.pokemonx.model.detail


import com.google.gson.annotations.SerializedName

data class VersionGroupDetail(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int = 0, // 0
    @SerializedName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod = MoveLearnMethod(),
    @SerializedName("version_group")
    val versionGroup: VersionGroup = VersionGroup()
)