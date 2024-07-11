package com.mazecube.tournamentbracketsample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.mazecube.tournamentbracket.TournamentBracketView
import com.mazecube.tournamentbracket.data.MatchData
import com.mazecube.tournamentbracket.data.MatchParticipant

class MainActivity : AppCompatActivity() {

    private lateinit var tournamentBracketView: TournamentBracketView
    private lateinit var participants: MutableList<MatchParticipant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tournamentBracketView = findViewById(R.id.tournamentBracketView)
        val nextRoundButton: Button = findViewById(R.id.btnNextRound)

        participants = mutableListOf(
            // Add participants to the list matching the MatchParticipant data class
            MatchParticipant(1, "Player 1", drawableToBitmap(this, R.drawable.profile_image), null, null),
            MatchParticipant(2, "Player 2", drawableToBitmap(this, R.drawable.profile_image), null, null),
            MatchParticipant(3, "Player 3", drawableToBitmap(this, R.drawable.profile_image), null, null),
            MatchParticipant(4, "Player 4", drawableToBitmap(this, R.drawable.profile_image), null, null),
            MatchParticipant(5, "Player 5", drawableToBitmap(this, R.drawable.profile_image), null, null)
        )

        // Initialize the tournament bracket view with participants
        // If isShuffle is true, participants are added randomly; if false, they are added in order
        // Default value for isShuffle is false
        val roundOne = tournamentBracketView.initParticipant(
            participants,
            isShuffle = false
        )

        //print
        for(match in roundOne) {
            val playerA = match.playerA.nickname
            val playerB = if(match.playerB == null) "Bye" else match.playerB!!.nickname
            println("$playerA vs $playerB")
        }

        println(tournamentBracketView.getCurrentMatchListData())

        val updatedParticipants = processMatchesAndUpdateParticipants(roundOne)

        nextRoundButton.setOnClickListener {
            if(processNextRound(updatedParticipants).isEmpty()) {
                println("Ended tournament.")
            }
        }
    }

    // Function to process the next round
    private fun processNextRound(participants: List<MatchParticipant>): List<MatchParticipant> {
        return processMatchesAndUpdateParticipants(tournamentBracketView.updateMatchData(participants))
    }

    // Function to process matches and update participants
    private fun processMatchesAndUpdateParticipants(matchData: List<MatchData>): List<MatchParticipant> {
        matchData.forEach { match ->
            match.playerB?.let {
                // Generate random points for both players
                val playerAPoint = (10..99).random().toString()
                val playerBPoint = (10..99).random().toString()

                match.playerA.point = playerAPoint
                it.point = playerBPoint

                // Determine winner
                if (playerAPoint.toInt() > playerBPoint.toInt()) {
                    match.playerA.isWinner = true
                    it.isWinner = false
                } else {
                    match.playerA.isWinner = false
                    it.isWinner = true
                }
            } ?: run {
                // If playerB is null, playerA wins by default (bye)
                match.playerA.point = "B"
                match.playerA.isWinner = true
            }
        }

        return matchData.flatMap { listOfNotNull(it.playerA, it.playerB) }
    }

    private fun drawableToBitmap(context: Context, drawableId: Int): Bitmap {
        val drawable: Drawable = ContextCompat.getDrawable(context, drawableId)
            ?: throw IllegalArgumentException("Drawable with id $drawableId not found")

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}