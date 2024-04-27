package com.example.tictactoe

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TableLayout

class MainActivity : AppCompatActivity() {

    // enum classes
    enum class PLAYINGPLAYER {

        FIRST_PLAYER,
        SECOND_PLAYER
    }

    enum class WINNER_OF_GAME {

        PLAYER_ONE,
        PLAYER_TWO,
        NO_ONE
    }

    // instance variables

    private var playingPlayer: PLAYINGPLAYER? = null
    private var winnerOfGame: WINNER_OF_GAME? = null

    private var player1Options: ArrayList<Int> = ArrayList()
    private var player2Options: ArrayList<Int> = ArrayList()
    private var allDisabledImages: ArrayList<ImageButton?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            startService(
                Intent(this@MainActivity,
                SoundService::class.java)
            )

        } catch (e: Exception) {

            e.printStackTrace()
        }

        playingPlayer = PLAYINGPLAYER.FIRST_PLAYER
        winnerOfGame = WINNER_OF_GAME.NO_ONE

    }

    fun imageButtonTapped(view: View) {

        val selectedImageButton: ImageButton = view as ImageButton
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

        when ((Math.random() * 9 + 1).toInt()) {

            1 -> tableLayout.setBackgroundColor(Color.YELLOW)
            2 -> tableLayout.setBackgroundColor(Color.DKGRAY)
            3 -> tableLayout.setBackgroundColor(Color.GREEN)
            4 -> tableLayout.setBackgroundColor(Color.LTGRAY)
            5 -> tableLayout.setBackgroundColor(Color.CYAN)
            6 -> tableLayout.setBackgroundColor(Color.MAGENTA)
            7 -> tableLayout.setBackgroundColor(Color.RED)
            8 -> tableLayout.setBackgroundColor(Color.BLUE)
            9 -> tableLayout.setBackgroundColor(Color.WHITE)

        }

        var optionNumber = 0

        when (selectedImageButton.id) {

            R.id.imgButton1 -> optionNumber = 1
            R.id.imgButton2 -> optionNumber = 2
            R.id.imgButton3 -> optionNumber = 3
            R.id.imgButton4 -> optionNumber = 4
            R.id.imgButton5 -> optionNumber = 5
            R.id.imgButton6 -> optionNumber = 6
            R.id.imgButton7 -> optionNumber = 7
            R.id.imgButton8 -> optionNumber = 8
            R.id.imgButton9 -> optionNumber = 9
        }
        action(optionNumber, selectedImageButton)

    } // end of imageButtonTapped function

    private fun action(optionNumber: Int, _selectedImageButton: ImageButton) {

        var selectedImageButton = _selectedImageButton

        if (playingPlayer == PLAYINGPLAYER.FIRST_PLAYER) {

            selectedImageButton.setImageResource(R.drawable.x_letter)

            player1Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            playingPlayer = PLAYINGPLAYER.SECOND_PLAYER

        } else if (playingPlayer == PLAYINGPLAYER.SECOND_PLAYER) {

            // algorithm for playing by yourself
//            selectedImageButton.setImageResource(R.drawable.o_letter)
//
//            player2Options.add(optionNumber)
//            selectedImageButton.isEnabled = false
//            allDisabledImages.add(selectedImageButton)
//            playingPlayer = PLAYINGPLAYER.SECOND_PLAYER

            val notSelectedImageNumbers = ArrayList<Int>()

            for (imageNumber in 1..9) {

                if (!(player1Options.contains(imageNumber))){

                    if (!player2Options.contains(imageNumber)) {

                        // notSelectedImageNumbers is created in order to hold
                        // the image numbers of the image buttons that are not selected
                        notSelectedImageNumbers.add(imageNumber)

                    } // end of inner if block

                } // end of outer if block

            } // end of for loop

            // ids
            val imgButton1 = findViewById<ImageButton>(R.id.imgButton1)
            val imgButton2 = findViewById<ImageButton>(R.id.imgButton2)
            val imgButton3 = findViewById<ImageButton>(R.id.imgButton3)
            val imgButton4 = findViewById<ImageButton>(R.id.imgButton4)
            val imgButton5 = findViewById<ImageButton>(R.id.imgButton5)
            val imgButton6 = findViewById<ImageButton>(R.id.imgButton6)
            val imgButton7 = findViewById<ImageButton>(R.id.imgButton7)
            val imgButton8 = findViewById<ImageButton>(R.id.imgButton8)
            val imgButton9 = findViewById<ImageButton>(R.id.imgButton9)

            try {

                var randomNumber = ((Math.random() * notSelectedImageNumbers.size)).toInt()
                var imageNumber = notSelectedImageNumbers[randomNumber]
                when (imageNumber) {

                    1 -> selectedImageButton = imgButton1
                    2 -> selectedImageButton = imgButton2
                    3 -> selectedImageButton = imgButton3
                    4 -> selectedImageButton = imgButton4
                    5 -> selectedImageButton = imgButton5
                    6 -> selectedImageButton = imgButton6
                    7 -> selectedImageButton = imgButton7
                    8 -> selectedImageButton = imgButton8
                    9 -> selectedImageButton = imgButton9

                }

                selectedImageButton.setImageResource(R.drawable.o_letter)
                player2Options.add(imageNumber)
                selectedImageButton.isEnabled = false
                allDisabledImages.add(selectedImageButton)
                playingPlayer = PLAYINGPLAYER.FIRST_PLAYER

            } catch(e: Exception) {

                e.printStackTrace()

            } // end of catch block

        }
        specifyTheWinnerOfTheGame()

    } // closing of action method

    private fun specifyTheWinnerOfTheGame() {

        if (player1Options.contains(1)
            && player1Options.contains(2)
            && player1Options.contains(3)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(1)
            && player2Options.contains(2)
            && player2Options.contains(3)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(4)
            && player1Options.contains(5)
            && player1Options.contains(6)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(4)
            && player2Options.contains(5)
            && player2Options.contains(6)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }  else if (player1Options.contains(7)
            && player1Options.contains(8)
            && player1Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(7)
            && player2Options.contains(8)
            && player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(1)
            && player1Options.contains(4)
            && player1Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(1)
            && player2Options.contains(4)
            && player2Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(2)
            && player1Options.contains(5)
            && player1Options.contains(8)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(2)
            && player2Options.contains(5)
            && player2Options.contains(8)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(3)
            && player1Options.contains(6)
            && player1Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(3)
            && player2Options.contains(6)
            && player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(1)
            && player1Options.contains(5)
            && player1Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(1)
            && player2Options.contains(5)
            && player2Options.contains(9)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } else if (player1Options.contains(3)
            && player1Options.contains(5)
            && player1Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        } else if (player2Options.contains(3)
            && player2Options.contains(5)
            && player2Options.contains(7)) {

            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        } // end of else if statement

        if (winnerOfGame == WINNER_OF_GAME.PLAYER_ONE) {

            createAlert("Player One Wins",
                "Congratulations to Player 1",
                AlertDialog.BUTTON_POSITIVE, "OK", false)
            return

        } else if (winnerOfGame == WINNER_OF_GAME.PLAYER_TWO) {

            createAlert("Player Two Wins",
                "Congratulations to Player 2",
                AlertDialog.BUTTON_POSITIVE, "OK", false)
            return
        } // end of else if statement

        checkDrawState()

    } // end of function specifyTheWinnerOfTheGame

    private fun createAlert(title: String, message: String, whichButton: Int,
                            buttonText: String, cancelable: Boolean) {

        val alertDialog: AlertDialog =
            AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setButton(whichButton, buttonText) {
                _: DialogInterface?, _: Int ->

            resetGame()
        } // end of alertDialog

        alertDialog.setCancelable(cancelable)

        alertDialog.show()

    } // end of createAlert function

    private fun resetGame() {
        val imgButton1 = findViewById<ImageButton>(R.id.imgButton1)
        val imgButton2 = findViewById<ImageButton>(R.id.imgButton2)
        val imgButton3 = findViewById<ImageButton>(R.id.imgButton3)
        val imgButton4 = findViewById<ImageButton>(R.id.imgButton4)
        val imgButton5 = findViewById<ImageButton>(R.id.imgButton5)
        val imgButton6 = findViewById<ImageButton>(R.id.imgButton6)
        val imgButton7 = findViewById<ImageButton>(R.id.imgButton7)
        val imgButton8 = findViewById<ImageButton>(R.id.imgButton8)
        val imgButton9 = findViewById<ImageButton>(R.id.imgButton9)

        player1Options.clear()
        player2Options.clear()
        allDisabledImages.clear()
        winnerOfGame = WINNER_OF_GAME.NO_ONE
        playingPlayer = PLAYINGPLAYER.FIRST_PLAYER

        imgButton1.setImageResource(R.drawable.place_holder)
        imgButton2.setImageResource(R.drawable.place_holder)
        imgButton3.setImageResource(R.drawable.place_holder)
        imgButton4.setImageResource(R.drawable.place_holder)
        imgButton5.setImageResource(R.drawable.place_holder)
        imgButton6.setImageResource(R.drawable.place_holder)
        imgButton7.setImageResource(R.drawable.place_holder)
        imgButton8.setImageResource(R.drawable.place_holder)
        imgButton9.setImageResource(R.drawable.place_holder)

        imgButton1.isEnabled = true
        imgButton2.isEnabled = true
        imgButton3.isEnabled = true
        imgButton4.isEnabled = true
        imgButton5.isEnabled = true
        imgButton6.isEnabled = true
        imgButton7.isEnabled = true
        imgButton8.isEnabled = true
        imgButton9.isEnabled = true

    } // end of resetGame function

    private fun checkDrawState() {

        if (allDisabledImages.size == 9 && winnerOfGame != WINNER_OF_GAME.PLAYER_ONE &&
            winnerOfGame != WINNER_OF_GAME.PLAYER_TWO) {

            createAlert("DRAW!!!", "No one won the game!",
                AlertDialog.BUTTON_POSITIVE, "OK", false)

        } // end of if statement

    } // end of checkDrawState function

    // release all the sound resources after the activity is destroyed
    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, SoundService::class.java))
    }

} // end of mainActivity class