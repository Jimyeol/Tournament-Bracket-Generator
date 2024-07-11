package com.mazecube.tournamentbracket.data

import android.graphics.Bitmap

data class MatchParticipant(
    val id: Int, //Required unique id
    val nickname: String, //nick name
    var face: Bitmap?, //profile image
    var point: String?,
    var isWinner: Boolean?
)
