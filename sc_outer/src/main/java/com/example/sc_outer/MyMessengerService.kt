package com.example.sc_outer

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*

class MyMessengerService : Service() {

    lateinit var messenger: Messenger   //데이터를 전달받는 메신저
    lateinit var replyMessenger: Messenger  //데이터를 전달하는 메신저
    lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    // 액티비티로 부터 메시지가 전달되었을 때
    // 메시지를 받았을 때!
    inner class IncomingHandler(
        context: Context,
        val applicationContext: Context = context.applicationContext
    ): Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                10 -> {
                    replyMessenger = msg.replyTo
                    if(!player.isPlaying){
                        player = MediaPlayer.create(this@MyMessengerService, R.raw.music)
                        try {
                            val replyMsg = Message()
                            replyMsg.what = 10

                            val replyBundle = Bundle()
                            replyBundle.putInt("duration", player.duration)

                            replyMsg.obj = replyBundle
                            replyMessenger.send(replyMsg)

                            player.start()  //음악 재생

                        } catch (e: Exception){
                            e.printStackTrace()
                        }

                    }
                }
                20 -> {
                    if(player.isPlaying){
                        player.stop()
                    }
                }
                else ->{
                    super.handleMessage(msg)
                }
            }
        }
    }


    override fun onBind(intent: Intent): IBinder {
        messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }

}