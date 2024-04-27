package com.example.tictactoe

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class SoundService: Service() {

    var player: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        player = MediaPlayer.create(this, R.raw.music)
        player?.setLooping(true)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        player?.start()
        return Service.START_NOT_STICKY

    }

    override fun onDestroy() {
        player?.stop()
        player?.release()
        stopSelf()
        super.onDestroy()
    }



}