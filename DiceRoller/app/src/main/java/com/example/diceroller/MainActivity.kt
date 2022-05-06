package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/******************************************************************************
 * This code lets the user roll a dice and then returns that value to them on
 * the screen
 * ****************************************************************************/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)

        //create a listener to listen to when the button is clicked and return result
        rollButton.setOnClickListener { rollDice() }
    }

    /**********************************************************************
     * Roll the dice and return value to user
     * ********************************************************************/
    private fun rollDice() {
        // create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)

        // calls the setImageResource to set a image
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // update the content description of the image
        diceImage.contentDescription = diceRoll.toString()
    }
}

/*******************************************************************************
 * Class Dice to be used as a blueprint for creating dice objects to be used
 * in the MainActivity
 *******************************************************************************/

class Dice(private val numSides: Int) {

    //Dice method to be used to randomly generate a number
    fun roll(): Int {
        return (1..numSides).random()
    }
}