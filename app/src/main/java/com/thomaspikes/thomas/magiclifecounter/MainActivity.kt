package com.thomaspikes.thomas.magiclifecounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.FloatingActionButton
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.w3c.dom.Text

/*Things I can think of
    TODO: Add pop up to gather name of player
    TODO: Include color choosing on popup
    TODO: Build game layout
    TODO: Build player edit screen for during game
    TODO: Look up how DPI and screen spacing works
*/
class MainActivity : AppCompatActivity() {

    val MAX_PLAYERS: Int = 5 //the actual max players is MAX_PLAYERS + 1
    var numberOfPlayers: Int = 2
    var players =  ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //Call parent oncreate method aka init
        super.onCreate(savedInstanceState)
        //Set the initial layout
        setContentView(R.layout.activity_main)
        //create initial players
        createPlayers()
        //set up player count actions
        var playerTotal = findViewById<EditText>(R.id.numberOfPlayersEditText)
        playerTotal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateNumberOfPlayers()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        //Action button doing things
        val action_button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        action_button.setOnClickListener {
            Toast.makeText(applicationContext, "Player Total:" + numberOfPlayers, Toast.LENGTH_SHORT).show()

        }
    }

    fun createPlayers(){
        var playerNameTextView: Int
        var playerNameChangeTextView: Int
        for (i in 0..MAX_PLAYERS){
            when(i){
                0 -> {
                    players.add(Player(i, i.toString(), findViewById(R.id.playerNameLayout), R.id.player1Title, R.id.player1ChangeName ))
                }
                1 -> {
                    players.add(Player(i, i.toString(), findViewById(R.id.playerNameLayout), R.id.player2Title, R.id.player2ChangeName ))
                }
                2 -> {
                    players.add(Player(i, i.toString(), findViewById(R.id.playerNameLayout), R.id.player3Title, R.id.player3ChangeName ))
                }
                3 -> {
                    players.add(Player(i, i.toString(), findViewById(R.id.playerNameLayout), R.id.player4Title, R.id.player4ChangeName ))
                }
                4 -> {
                    players.add(Player(i, i.toString(), findViewById(R.id.playerNameLayout), R.id.player5Title, R.id.player5ChangeName ))
                }
                5 -> {
                    players.add(Player(i, i.toString(), findViewById(R.id.playerNameLayout), R.id.player6Title, R.id.player6ChangeName ))
                }
            }
        }
        updateNumberOfPlayers()
        drawPlayers()
    }

    fun updateNumberOfPlayers() {
        //get the player count TextView
        val playerCountTextView = findViewById<EditText>(R.id.numberOfPlayersEditText)

        //sanity check for none empty string, set to 2 and recurse if empty
        if (numberOfPlayersEditText.text.isNotEmpty() ) {
            //get playerCount int
            val playerCount = playerCountTextView.text.toString().toInt()
            //sanity check for player count in bounds [2..MAX_PLAYERS]
            if (playerCount >= 2 && playerCount <= MAX_PLAYERS + 1) {
                numberOfPlayers = playerCount
            } else {
                //outside of bounds? set to 2 and yell
                numberOfPlayersEditText.setText("2")
                updateNumberOfPlayers()
            }
            //update isPlaying property of each player
            for (i in 0..MAX_PLAYERS) {
                players[i].isPlaying = ( i <= numberOfPlayers - 1 )
            }
            //update view for new player
            drawPlayers()
        }
    }

    fun showPlayer(pPlayerIndex: Int){
        var playerTextView: TextView
        playerTextView = findViewById<TextView>(players.get(pPlayerIndex).playerNameTextView)
        playerTextView.visibility = View.VISIBLE
        playerTextView = findViewById<TextView>(players.get(pPlayerIndex).playerNameChangeTextView)
        playerTextView.visibility = View.VISIBLE
    }

    fun hidePlayer(pPlayerIndex: Int){
        var playerTextView: TextView
        playerTextView = findViewById<TextView>(players.get(pPlayerIndex).playerNameTextView)
        playerTextView.visibility = View.GONE
        playerTextView = findViewById<TextView>(players.get(pPlayerIndex).playerNameChangeTextView)
        playerTextView.visibility = View.GONE
    }

    fun drawPlayers(){
        for (i in 0..MAX_PLAYERS){
            if(!players[i].isPlaying){
                hidePlayer(i)
            } else {
                showPlayer(i)
            }
        }
    }
}
