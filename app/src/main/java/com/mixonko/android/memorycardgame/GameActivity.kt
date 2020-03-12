package com.mixonko.android.memorycardgame

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.os.Vibrator
import android.animation.ObjectAnimator
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.preference.PreferenceManager
import android.view.View.TRANSLATION_Y
import android.widget.LinearLayout
import android.view.animation.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import java.lang.Exception
import java.util.ArrayList
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



var backgroundStyle = 1

var APP_PREFERENCES = "com.mixonko.android.memorycardgame.APP_PREFERENCES"
var FIRST_PLAYER = "com.mixonko.android.memorycardgame.FIRST_PLAYER"
var SECOND_PLAYER = "com.mixonko.android.memorycardgame.SECOND_PLAYER"
var TURN = "com.mixonko.android.memorycardgame.TURN"

class MainActivity : AppCompatActivity() {

    val cardBack = R.drawable.card_back

    val image11 = R.drawable.ic_image11
    val image12 = R.drawable.ic_image12
    val image13 = R.drawable.ic_image13
    val image14 = R.drawable.ic_image14
    val image15 = R.drawable.ic_image15
    val image16 = R.drawable.ic_image16
    val image17 = R.drawable.ic_image17
    val image18 = R.drawable.ic_image18
    val image21 = R.drawable.ic_image21
    val image22 = R.drawable.ic_image22
    val image23 = R.drawable.ic_image23
    val image24 = R.drawable.ic_image24
    val image25 = R.drawable.ic_image25
    val image26 = R.drawable.ic_image26
    val image27 = R.drawable.ic_image27
    val image28 = R.drawable.ic_image28

    val background1 = R.drawable.background1
    val background2 = R.drawable.background2
    val background3 = R.drawable.background3
    val background4 = R.drawable.background4

    lateinit var imageView11: ImageView
    lateinit var imageView12: ImageView
    lateinit var imageView13: ImageView
    lateinit var imageView14: ImageView
    lateinit var imageView21: ImageView
    lateinit var imageView22: ImageView
    lateinit var imageView23: ImageView
    lateinit var imageView24: ImageView
    lateinit var imageView31: ImageView
    lateinit var imageView32: ImageView
    lateinit var imageView33: ImageView
    lateinit var imageView34: ImageView
    lateinit var imageView41: ImageView
    lateinit var imageView42: ImageView
    lateinit var imageView43: ImageView
    lateinit var imageView44: ImageView

    lateinit var background: ImageView

    lateinit var line1: LinearLayout
    lateinit var line2: LinearLayout
    lateinit var line3: LinearLayout
    lateinit var line4: LinearLayout

    lateinit var firstPointsTextView: TextView
    lateinit var secondPointsTextView: TextView
    lateinit var firstGlobalPointsTextView: TextView
    lateinit var secondGlobalPointsTextView: TextView

    var firstCard: Int? = 0
    var secondCard: Int? = 0

    var clickedFirst: Int? = 0
    var clickedSecond: Int? = 0

    var cardNumber: Int = 1

    var turn: Int = 1
    var firstPlayerPoints: Int = 0
    var secondPlayerPoints: Int = 0
    var firstPlayerGlobalPoints: Int = 0
    var secondPlayerGlobalPoints: Int = 0

    var checkEndGame = 0

    val cardsArray =
        mutableListOf(11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28)

    lateinit var sp: SharedPreferences
    lateinit var settings: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    lateinit var mediaPlayer: MediaPlayer
    lateinit var soundPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        sp = PreferenceManager.getDefaultSharedPreferences(this)

        findViewById()

        startAnimation()

        firstPointsTextView.setTextColor(Color.GREEN)
        secondPointsTextView.setTextColor(Color.GRAY)

        imageView11.setTag(0)
        imageView12.setTag(1)
        imageView13.setTag(2)
        imageView14.setTag(3)
        imageView21.setTag(4)
        imageView22.setTag(5)
        imageView23.setTag(6)
        imageView24.setTag(7)
        imageView31.setTag(8)
        imageView32.setTag(9)
        imageView33.setTag(10)
        imageView34.setTag(11)
        imageView41.setTag(12)
        imageView42.setTag(13)
        imageView43.setTag(14)
        imageView44.setTag(15)

//        Collections.shuffle(cardsArray)

        setOnClickListener()
    }

    private fun startAnimation() {
        val bounceInterpolator = BounceInterpolator()
        val objectAnimator1 = ObjectAnimator.ofFloat(line1, TRANSLATION_Y, -850f, 0f)
        objectAnimator1.setInterpolator(bounceInterpolator)
        objectAnimator1.setDuration(800).start()

        val objectAnimator2 = ObjectAnimator.ofFloat(line2, TRANSLATION_Y, -500f, 0f)
        objectAnimator2.setInterpolator(bounceInterpolator)
        objectAnimator2.setDuration(800).start()

        val objectAnimator3 = ObjectAnimator.ofFloat(line3, TRANSLATION_Y, 500f, 0f)
        objectAnimator3.setInterpolator(bounceInterpolator)
        objectAnimator3.setDuration(800).start()

        val objectAnimator4 = ObjectAnimator.ofFloat(line4, TRANSLATION_Y, 850f, 0f)
        objectAnimator4.setInterpolator(bounceInterpolator)
        objectAnimator4.setDuration(800).start()
    }

    private fun findViewById() {
        imageView11 = findViewById(R.id.imageView11)
        imageView12 = findViewById(R.id.imageView12)
        imageView13 = findViewById(R.id.imageView13)
        imageView14 = findViewById(R.id.imageView14)
        imageView21 = findViewById(R.id.imageView21)
        imageView22 = findViewById(R.id.imageView22)
        imageView23 = findViewById(R.id.imageView23)
        imageView24 = findViewById(R.id.imageView24)
        imageView31 = findViewById(R.id.imageView31)
        imageView32 = findViewById(R.id.imageView32)
        imageView33 = findViewById(R.id.imageView33)
        imageView34 = findViewById(R.id.imageView34)
        imageView41 = findViewById(R.id.imageView41)
        imageView42 = findViewById(R.id.imageView42)
        imageView43 = findViewById(R.id.imageView43)
        imageView44 = findViewById(R.id.imageView44)

        background = findViewById(R.id.background)
        when (backgroundStyle) {
            1 -> background.setImageResource(background1)
            2 -> background.setImageResource(background2)
            3 -> background.setImageResource(background3)
            4 -> background.setImageResource(background4)
        }

        firstPointsTextView = findViewById(R.id.points)
        secondPointsTextView = findViewById(R.id.topPoints)
        firstGlobalPointsTextView = findViewById(R.id.first_global_points_tv)
        secondGlobalPointsTextView = findViewById(R.id.second_global_points_tv)

        line1 = findViewById(R.id.line_1)
        line2 = findViewById(R.id.line_2)
        line3 = findViewById(R.id.line_3)
        line4 = findViewById(R.id.line_4)
    }

    private fun setOnClickListener() {
        imageView11.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView11, theCard)
        }
        imageView12.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView12, theCard)
        }
        imageView13.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView13, theCard)
        }
        imageView14.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView14, theCard)
        }
        imageView21.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView21, theCard)
        }
        imageView22.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView22, theCard)
        }
        imageView23.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView23, theCard)
        }
        imageView24.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView24, theCard)
        }
        imageView31.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView31, theCard)
        }
        imageView32.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView32, theCard)
        }
        imageView33.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView33, theCard)
        }
        imageView34.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView34, theCard)
        }
        imageView41.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView41, theCard)
        }
        imageView42.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView42, theCard)
        }
        imageView43.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView43, theCard)
        }
        imageView44.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            doStuff(imageView44, theCard)

        }

    }

    private fun doStuff(imageView: ImageView, card: Int) {
        if (sp.getBoolean("vibration", true)) {
            val vibe = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(40)
        }

        when (cardsArray[card]) {
            11 -> imageView.setImageResource(image11)
            12 -> imageView.setImageResource(image12)
            13 -> imageView.setImageResource(image13)
            14 -> imageView.setImageResource(image14)
            15 -> imageView.setImageResource(image15)
            16 -> imageView.setImageResource(image16)
            17 -> imageView.setImageResource(image17)
            18 -> imageView.setImageResource(image18)
            21 -> imageView.setImageResource(image21)
            22 -> imageView.setImageResource(image22)
            23 -> imageView.setImageResource(image23)
            24 -> imageView.setImageResource(image24)
            25 -> imageView.setImageResource(image25)
            26 -> imageView.setImageResource(image26)
            27 -> imageView.setImageResource(image27)
            28 -> imageView.setImageResource(image28)

        }

        if (cardNumber == 1) {
            firstCard = cardsArray[card]
            if (firstCard!! > 20) {
                firstCard = firstCard!! - 10
            }

            cardNumber = 2
            clickedFirst = card

            imageView.isEnabled = false

        } else if (cardNumber == 2) {
            secondCard = cardsArray[card]
            if (secondCard!! > 20) {
                secondCard = secondCard!! - 10
            }
            cardNumber = 1
            clickedSecond = card

            imageViewIsEnabled(false)

            val handler = Handler()
            handler.postDelayed(Runnable {
                calculate()
            }, 500)

        }

    }

    private fun calculate() {
        if (firstCard == secondCard) {
            if (sp.getBoolean("music", true)) {
                var soundPlayer = MediaPlayer.create(this, R.raw.done)
                soundPlayer.start()
            }
            checkEndGame++
            when (clickedFirst) {
                0 -> imageView11.visibility = View.INVISIBLE
                1 -> imageView12.visibility = View.INVISIBLE
                2 -> imageView13.visibility = View.INVISIBLE
                3 -> imageView14.visibility = View.INVISIBLE
                4 -> imageView21.visibility = View.INVISIBLE
                5 -> imageView22.visibility = View.INVISIBLE
                6 -> imageView23.visibility = View.INVISIBLE
                7 -> imageView24.visibility = View.INVISIBLE
                8 -> imageView31.visibility = View.INVISIBLE
                9 -> imageView32.visibility = View.INVISIBLE
                10 -> imageView33.visibility = View.INVISIBLE
                11 -> imageView34.visibility = View.INVISIBLE
                12 -> imageView41.visibility = View.INVISIBLE
                13 -> imageView42.visibility = View.INVISIBLE
                14 -> imageView43.visibility = View.INVISIBLE
                15 -> imageView44.visibility = View.INVISIBLE
            }

            when (clickedSecond) {
                0 -> imageView11.visibility = View.INVISIBLE
                1 -> imageView12.visibility = View.INVISIBLE
                2 -> imageView13.visibility = View.INVISIBLE
                3 -> imageView14.visibility = View.INVISIBLE
                4 -> imageView21.visibility = View.INVISIBLE
                5 -> imageView22.visibility = View.INVISIBLE
                6 -> imageView23.visibility = View.INVISIBLE
                7 -> imageView24.visibility = View.INVISIBLE
                8 -> imageView31.visibility = View.INVISIBLE
                9 -> imageView32.visibility = View.INVISIBLE
                10 -> imageView33.visibility = View.INVISIBLE
                11 -> imageView34.visibility = View.INVISIBLE
                12 -> imageView41.visibility = View.INVISIBLE
                13 -> imageView42.visibility = View.INVISIBLE
                14 -> imageView43.visibility = View.INVISIBLE
                15 -> imageView44.visibility = View.INVISIBLE
            }



            if (turn == 1) {
                firstPlayerPoints++
                firstPointsTextView.setText("$firstPlayerPoints")
            } else if (turn == 2) {
                secondPlayerPoints++
                secondPointsTextView.setText("$secondPlayerPoints")
            }
        } else {
            if (sp.getBoolean("music", true)) {
                var soundPlayer = MediaPlayer.create(this, R.raw.not)
                soundPlayer.start()
            }
            imageView11.setImageResource(cardBack)
            imageView12.setImageResource(cardBack)
            imageView13.setImageResource(cardBack)
            imageView14.setImageResource(cardBack)
            imageView21.setImageResource(cardBack)
            imageView22.setImageResource(cardBack)
            imageView23.setImageResource(cardBack)
            imageView24.setImageResource(cardBack)
            imageView31.setImageResource(cardBack)
            imageView32.setImageResource(cardBack)
            imageView33.setImageResource(cardBack)
            imageView34.setImageResource(cardBack)
            imageView41.setImageResource(cardBack)
            imageView42.setImageResource(cardBack)
            imageView43.setImageResource(cardBack)
            imageView44.setImageResource(cardBack)

            if (turn == 1) {
                turn = 2
                firstPointsTextView.setTextColor(Color.GRAY)
                secondPointsTextView.setTextColor(Color.GREEN)
            } else if (turn == 2) {
                turn = 1
                firstPointsTextView.setTextColor(Color.GREEN)
                secondPointsTextView.setTextColor(Color.GRAY)
            }
        }
        imageViewIsEnabled(true)
        checkEndGame()

    }

    private fun imageViewIsEnabled(b: Boolean) {
        imageView11.isEnabled = b
        imageView12.isEnabled = b
        imageView13.isEnabled = b
        imageView14.isEnabled = b
        imageView21.isEnabled = b
        imageView22.isEnabled = b
        imageView23.isEnabled = b
        imageView24.isEnabled = b
        imageView31.isEnabled = b
        imageView32.isEnabled = b
        imageView33.isEnabled = b
        imageView34.isEnabled = b
        imageView41.isEnabled = b
        imageView42.isEnabled = b
        imageView43.isEnabled = b
        imageView44.isEnabled = b
    }

    private fun checkEndGame() {
        if (checkEndGame == 4) {
            var message: String = ""
            if (firstPlayerPoints == secondPlayerPoints) {
                message = "It's a draw!"
                firstPlayerGlobalPoints++
                secondPlayerGlobalPoints++
                firstGlobalPointsTextView.setText("$firstPlayerGlobalPoints")
                secondGlobalPointsTextView.setText("$secondPlayerGlobalPoints")
            }
            if (firstPlayerPoints > secondPlayerPoints) {
                message = "First Player Win!"
                firstPlayerGlobalPoints++
                firstGlobalPointsTextView.setText("$firstPlayerGlobalPoints")
            }

            if (firstPlayerPoints < secondPlayerPoints) {
                message = "Second Player Win!"
                secondPlayerGlobalPoints++
                secondGlobalPointsTextView.setText("$secondPlayerGlobalPoints")
            }

            AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.game_over)
                .setMessage(message)
                .setPositiveButton(
                    R.string.continue_game,
                    DialogInterface.OnClickListener { dialog, which ->
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        intent.putExtra(LOAD_GAME, 1)
                        if (backgroundStyle == 4) {
                            backgroundStyle = 1
                        } else backgroundStyle++
                        startActivity(intent)
                        finish()

                    })
                .setNegativeButton(R.string.exit, DialogInterface.OnClickListener { dialog, which ->
                    finish()
                }).show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (sp.getBoolean("music", true)) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music2)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        } else try {
            mediaPlayer.stop()
        } catch (e: Exception) {
        }

        val intent = getIntent()
        val load = intent.getIntExtra(LOAD_GAME, 0)
        if (load != 0) {
            try {
                firstGlobalPointsTextView.setText(settings.getInt(FIRST_PLAYER, 0).toString())
                secondGlobalPointsTextView.setText(settings.getInt(SECOND_PLAYER, 0).toString())
                firstPlayerGlobalPoints = settings.getInt(FIRST_PLAYER, 0)
                secondPlayerGlobalPoints = settings.getInt(SECOND_PLAYER, 0)
                turn = settings.getInt(TURN, 1)
                if (turn == 2) {
                    firstPointsTextView.setTextColor(Color.GRAY)
                    secondPointsTextView.setTextColor(Color.GREEN)
                }
            } catch (e: Exception) {
                Toast.makeText(this, "LOADING ERROR", Toast.LENGTH_LONG).show()
            }
        } else {
            firstPointsTextView.setText("0")
            secondPointsTextView.setText("0")
        }


    }


    override fun onPause() {
        super.onPause()
        editor = settings.edit()
        editor.putInt(FIRST_PLAYER, firstPlayerGlobalPoints)
        editor.putInt(SECOND_PLAYER, secondPlayerGlobalPoints)
        editor.putInt(TURN, turn)
                
        editor.apply()
        try {
            mediaPlayer.stop()
            soundPlayer.stop()
        } catch (e: Exception) {
        }
    }

}