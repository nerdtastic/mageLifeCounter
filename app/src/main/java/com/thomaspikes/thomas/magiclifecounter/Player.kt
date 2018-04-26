package com.thomaspikes.thomas.magiclifecounter

import android.graphics.Color
import android.view.View

class Player constructor(internal val pId: Int, internal val pName: String, internal val pParentview: View, internal val pPlayerNameTextView: Int, internal val pPlayerNameChangeTextView: Int) {
    var id = this.pId
    var name = this.pName
    var parentView = this.pParentview
    var playerNameTextView = this.pPlayerNameTextView
    var playerNameChangeTextView = this.pPlayerNameChangeTextView
    var isPlaying = false

}