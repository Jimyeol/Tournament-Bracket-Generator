package com.mazecube.tournamentbracket.data

/**
 * A data class that holds the information for a match between two participants, A and B.
 * If playerB is null, playerA wins by default (bye).
 *
 * @property playerA The first participant in the match.
 * @property playerB The second participant in the match, which can be null.
 */
data class MatchData(
    var playerA: MatchParticipant,
    var playerB: MatchParticipant?
)
