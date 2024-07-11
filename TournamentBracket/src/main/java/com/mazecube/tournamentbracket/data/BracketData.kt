package com.mazecube.tournamentbracket.data

import android.graphics.RectF

data class BracketData(
    var participant: MatchParticipant?,
    val isBye: Boolean = false,
    val bitmapSize: Int? = null,
    val bitmapStartX: Float? = null,
    val bitmapStartY: Float? = null,
    val textX: Float? = null,
    val textY: Float? = null,
    val rect: RectF? = null,
    val width: Float? = null,
    val height: Float? = null,
    val widthPadding: Float? = null,
    val heightPadding: Float? = null,
    val round: Float? = null
)
