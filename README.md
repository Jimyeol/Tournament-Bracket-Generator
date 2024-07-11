# Tournament-Bracket-Generator

TournamentBracket is an Android library designed to create and display tournament brackets effortlessly. Whether you're developing a sports app, a competitive gaming platform, or any other application that requires tournament visualization, TournamentBracket provides a customizable and easy-to-use solution.

## Introduction

This library leverages Custom View to generate tournament brackets, including those with byes. Initially created for personal use, it will not receive further updates. Users are free to use, copy, modify, merge, publish, and distribute the library as needed.

## Features

- Easy integration into Android projects.
- Customizable appearance and behavior.
- Supports tournaments with byes.
- Scalable to fit various screen sizes.
- Smooth zooming and panning.

## Getting started

Clone the library and import it.

## Usage
### Add to layout
```xml
<com.mazecube.tournamentbracket.TournamentBracketView
        android:id="@+id/tournamentBracketView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/btnNextRound"
        android:layout_marginBottom="20dp"
        app:background_color="@color/tournament_background"
        app:bracket_text_size="20dp"
        app:bracket_width_padding="10dp"
        app:bracket_height_padding="20dp"
        app:bracket_round="16dp"
        app:bracket_round_gap_size="100dp"
        app:default_line_width="5"
        app:winner_line_width="10"
        app:bracket_default_color="@color/bracket_rect"
        app:bracket_image_visible="true"
        app:bracket_text_max_length="10"
        app:bracket_point_text_visible="true"
        app:bracket_point_text_size="16dp"
        app:bracket_point_text_max_length="5"
        app:bracket_bye_text=""/>
```

### Kotlin
Add participants to the tournament as a list according to the ```MatchParticipant``` data class. Initially, the participants will have ```point``` and ```isWinner``` set to null.
```kotlin
private lateinit var participants: MutableList<MatchParticipant>

participants = mutableListOf(
    // Add participants to the list matching the MatchParticipant data class
    MatchParticipant(1, "Player 1", drawableToBitmap(this, R.drawable.profile_image), null, null),
    MatchParticipant(2, "Player 2", drawableToBitmap(this, R.drawable.profile_image), null, null),
    MatchParticipant(3, "Player 3", drawableToBitmap(this, R.drawable.profile_image), null, null),
    MatchParticipant(4, "Player 4", drawableToBitmap(this, R.drawable.profile_image), null, null),
    MatchParticipant(5, "Player 5", drawableToBitmap(this, R.drawable.profile_image), null, null)
)
```

Generate a tournament bracket based on the participants. If ```isShuffle``` is true, the participants will be placed randomly; if false, they will be placed in order. This function returns a ```List<MatchData>```. For more details, please refer to the comments in the data class.
```kotlin
val roundOne = tournamentBracketView.initParticipant(
            participants,
            isShuffle = false
        )
```

You can receive the current list of matches using the ```getCurrentMatchListData()``` function.
```kotlin
println(tournamentBracketView.getCurrentMatchListData())
```

As the matches progress, update the ```isWinner``` and ```point``` fields in the ```List<MatchParticipant>``` to reflect who won and who lost. When you call the ```updateMatchData()``` function, the view will automatically be redrawn with the updated data.
```kotlin
tournamentBracketView.updateMatchData(participants)
```

For more detailed code, refer to the example.


## XML Attribute

* ```background_color``` - The background color.
* ```default_line_color``` - The default color of the lines in the bracket.
* ```default_line_width``` - The default width of the lines in the bracket.
* ```winner_line_color``` - The color of the lines for the winning participants.
* ```winner_line_width``` - The width of the lines for the winning participants.
* ```bracket_image_visible``` - Visibility of the bracket image.
* ```bracket_default_color``` - The default color of the bracket.
* ```bracket_winner_color``` - The color of the bracket for winners.
* ```bracket_loser_color``` - The color of the bracket for losers.
* ```bracket_width_padding``` - The width padding for the bracket.
* ```bracket_height_padding``` - The height padding for the bracket.
* ```bracket_round``` - The round corners of the bracket.
* ```bracket_text_size``` - The size of the text in the bracket.
* ```bracket_round_gap_size``` - The gap size between rounds in the bracket.
* ```bracket_text_max_length``` - The maximum length of text in the bracket.
* ```bracket_point_text_visible``` - Visibility of the point text in the bracket.
* ```bracket_point_text_size``` - The size of the point text in the bracket.
* ```bracket_point_text_max_length``` - The maximum length of the point text in the bracket.
* ```bracket_bye_text``` - The text to display for a bye in the bracket.

```xml
<declare-styleable name="TournamentBracket">
    <attr name="background_color" format="color"/>
    <attr name="default_line_color" format="color"/>
    <attr name="default_line_width" format="float"/>
    <attr name="winner_line_color" format="color"/>
    <attr name="winner_line_width" format="float"/>
    <attr name="bracket_image_visible" format="boolean"/>
    <attr name="bracket_default_color" format="color"/>
    <attr name="bracket_winner_color" format="color"/>
    <attr name="bracket_loser_color" format="color"/>
    <attr name="bracket_width_padding" format="dimension"/>
    <attr name="bracket_height_padding" format="dimension"/>
    <attr name="bracket_round" format="dimension"/>
    <attr name="bracket_text_size" format="dimension"/>
    <attr name="bracket_round_gap_size" format="dimension"/>
    <attr name="bracket_text_max_length" format="integer"/>
    <attr name="bracket_point_text_visible" format="boolean"/>
    <attr name="bracket_point_text_size" format="dimension"/>
    <attr name="bracket_point_text_max_length" format="integer"/>
    <attr name="bracket_bye_text" format="string"/>
</declare-styleable>

