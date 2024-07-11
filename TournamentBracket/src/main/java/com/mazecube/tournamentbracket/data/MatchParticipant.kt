package com.mazecube.tournamentbracket.data

import android.graphics.Bitmap

data class MatchParticipant(
    val id: Int, //Required unique id
    val nickname: String,
    var face: Bitmap?,
    var point: String?,
    var isWinner: Boolean?
)
